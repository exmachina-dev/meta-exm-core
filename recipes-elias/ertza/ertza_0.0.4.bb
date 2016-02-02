SUMMARY = "Armaz main software"
DESCRIPTION = "Ertza is a Python program designed to provide functionalities\
for Armaz."
HOMEPAGE = "http://www.exmachina.fr/"
SECTION = "libs"

LICENSE = "none"
LIC_FILES_CHKSUM = "file://COPYING;md5=d41d8cd98f00b204e9800998ecf8427e"

SRCREV = "cad4e5063a3a9a5f3edc8e59ae82ebc22ca00714"

PR = "r1"
PV = "0.0.1+git${SRCPV}"

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
    python3-pymodbus \
    python3-pyserial \
    python3-readline \
    python3-smbus \
    python3-subprocess \
    python3-threading \
    python3-daemon \
"

SRC_URI = "\
    git://github.com/exmachina-dev/ertza.git;protocol=https;branch=dev \
    file://ertza.service \
    file://10-eth0.network \
"

S = "${WORKDIR}/git"

inherit setuptools3 systemd

SYSTEMD_SERVICE_${PN} = "ertza.service"

BINCOMMANDS = "ertza eeprom_writer.py"

# need to export these variables for python-config to work
export BUILD_SYS
export HOST_SYS
export STAGING_INCDIR
export STAGING_LIBDIR

do_install_append() {
    install -d ${D}${sysconfdir} \
            ${D}${sysconfdir}/ertza \
            ${D}${sysconfdir}/ertza/variants \
            ${D}${bindir} \

    install -m 0755 ${S}/bin/ertza ${D}${bindir}/
    install -m 0755 ${S}/tools/eeprom_writer.py ${D}${bindir}/

    install -m 0755 ${S}/conf/default.conf ${D}${sysconfdir}/ertza/default.conf
    install -m 0755 ${S}/conf/machine.conf ${D}${sysconfdir}/ertza/machine.conf
    install -m 0755 ${S}/conf/variants/armaz.heavy.conf ${D}${sysconfdir}/ertza/variants/armaz.heavy.conf
    install -m 0755 ${S}/conf/variants/armaz.flat.conf ${D}${sysconfdir}/ertza/variants/armaz.flat.conf
    install -m 0755 ${S}/conf/variants/armaz.fast.conf ${D}${sysconfdir}/ertza/variants/armaz.fast.conf
    sed -i 's#@LOGPATH@#/home/ertza/.ertza#' ${D}${sysconfdir}/ertza/default.conf

    # deal with systemd unit files
    install -d ${D}${systemd_unitdir}/system
    install -d ${D}${sysconfdir}/systemd/network

    sed -e 's,/etc,${sysconfdir},g' \
            -e 's,/usr/sbin,${sbindir},g' \
            -e 's,/var,${localstatedir},g' \
            -e 's,/usr/bin,${bindir},g' \
            -e 's,/usr,${prefix},g' ${WORKDIR}/ertza.service > ${D}${systemd_unitdir}/system/ertza.service
    chmod 644 ${D}${systemd_unitdir}/system/ertza.service

    install -m 0644 ${WORKDIR}/10-eth0.network ${D}${sysconfdir}/systemd/network/
}

FILES_${PN} = "\
    ${sysconfdir}/ertza/* \
    ${sysconfdir}/ertza/* \
    ${systemd_unitdir}/system/ertza.service \
    ${sysconfdir}/systemd/network/10-eth0.network \
    ${bindir}/ertza \
    ${bindir}/eeprom_writer.py \
    ${PYTHON_SITEPACKAGES_DIR}/ertza* \
    ${PYTHON_SITEPACKAGES_DIR}/Ertza* \
"

CONFFILES_${PN} ="\
    ${sysconfdir}/ertza/default.conf \
    ${sysconfdir}/ertza/machine.conf \
"
