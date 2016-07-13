SUMMARY = "Armaz main software"
DESCRIPTION = "Ertza is a Python program designed to provide functionalities\
for Armaz."
HOMEPAGE = "http://www.exmachina.fr/"
SECTION = "libs"

LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

SRCREV = "c8b2e10b1b91aeddedce5740cff42fdb585641ef"

PR = "r1"
PV_append = "+git${SRCPV}"
BRANCH = "dev"

RDEPENDS_${PN} = "\
    libgcc \
    python3 \
    packagegroup-python3 \
    python3-adafruit-beaglebone-io-python \
    python3-importlib \
    python3-lang \
    python3-logging \
    python3-multiprocessing \
    python3-pyliblo \
    python3-pylibmodbus \
    python3-pyserial \
    python3-readline \
    python3-smbus \
    python3-subprocess \
    python3-threading \
"

SRC_URI = "\
    git:///home/willykaze/repos/ertza;protocol=file;branch=${BRANCH} \
    file://ertza.service \
    file://init \
    file://10-eth0.network \
"

S = "${WORKDIR}/git"

inherit setuptools3 systemd update-rc.d

INITSCRIPT_NAME = "ertza"
INITSCRIPT_PARAMS = "defaults 10"


SYSTEMD_SERVICE_${PN} = "ertza.service"

PROVIDES += "${PN}-eeprom"

PACKAGES += "${PN}-eeprom"

BINCOMMANDS = "ertza ertzad eeprom_writer.py"

# need to export these variables for python-config to work
export BUILD_SYS
export HOST_SYS
export STAGING_INCDIR
export STAGING_LIBDIR

do_install_append() {
    install -d ${D}${sysconfdir} \
            ${D}${sysconfdir}/ertza \
            ${D}${sysconfdir}/ertza/variants \
            ${D}${sysconfdir}/ertza/profiles \
            ${D}${bindir} \

    install -m 0755 ${S}/bin/ertza ${D}${bindir}/
    install -m 0755 ${S}/bin/ertzad ${D}${bindir}/
    install -m 0755 ${S}/tools/eeprom_writer.py ${D}${bindir}/

    install -m 0755 ${S}/conf/default.conf ${D}${sysconfdir}/ertza/default.conf
    install -m 0755 ${S}/conf/machine.conf ${D}${sysconfdir}/ertza/machine.conf
    install -m 0755 ${S}/conf/variants/armaz.heavy.conf ${D}${sysconfdir}/ertza/variants/armaz.heavy.conf
    install -m 0755 ${S}/conf/variants/armaz.flat.conf ${D}${sysconfdir}/ertza/variants/armaz.flat.conf
    install -m 0755 ${S}/conf/variants/armaz.fast.conf ${D}${sysconfdir}/ertza/variants/armaz.fast.conf
    sed -i 's#@LOGPATH@#/home/ertza/.ertza#' ${D}${sysconfdir}/ertza/default.conf

    # deal with systemd unit files
    install -d ${D}${systemd_unitdir}/system

    install -d ${D}${sysconfdir}/init.d
    install -m 744 ${WORKDIR}/init ${D}${sysconfdir}/init.d/ertza

    sed -e 's,/etc,${sysconfdir},g' \
            -e 's,/usr/sbin,${sbindir},g' \
            -e 's,/var,${localstatedir},g' \
            -e 's,/usr/bin,${bindir},g' \
            -e 's,/usr,${prefix},g' ${WORKDIR}/ertza.service > ${D}${systemd_unitdir}/system/ertza.service
    chmod 644 ${D}${systemd_unitdir}/system/ertza.service

}

FILES_${PN} = "\
    ${sysconfdir}/ertza/* \
    ${sysconfdir}/ertza/* \
    ${systemd_unitdir}/system/ertza.service \
    ${sysconfdir}/init.d/ertza \
    ${bindir}/ertza \
    ${bindir}/ertzad \
    ${PYTHON_SITEPACKAGES_DIR}/ertza* \
    ${PYTHON_SITEPACKAGES_DIR}/Ertza* \
"
FILES_${PN}-eeprom = " \
    ${bindir}/eeprom_writer.py \
"

CONFFILES_${PN} ="\
    ${sysconfdir}/ertza/default.conf \
    ${sysconfdir}/ertza/machine.conf \
"
