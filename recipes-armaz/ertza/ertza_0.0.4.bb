SUMMARY = "Armaz main software"
DESCRIPTION = "Ertza is a Python program designed to provide functionalities\
for Armaz."
HOMEPAGE = "http://www.exmachina.fr/"
SECTION = "libs"

LICENSE = "none"
LIC_FILES_CHKSUM = "file://COPYING;md5=d41d8cd98f00b204e9800998ecf8427e"

SRCREV = "a797a4e4382d786edb7e23452256ae0a41257676"

PR = "r1"
PV = "0.0.1+git${SRCPV}"

RDEPENDS_${PN} = "\
    libgcc \
    armaz-dto \
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
"

SRC_URI = "\
    git:///home/willykaze/repos/ertza;protocol=file;branch=pel \
    file://ertza.service \
    file://10-eth0.network \
"

S = "${WORKDIR}/git"

inherit setuptools3 systemd

SYSTEMD_SERVICE_${PN} = "ertza.service"

BINCOMMANDS = "ertza"

# need to export these variables for python-config to work
export BUILD_SYS
export HOST_SYS
export STAGING_INCDIR
export STAGING_LIBDIR

do_install_append() {
    install -d ${D}${sysconfdir} \
            ${D}${sysconfdir}/ertza \
            ${D}${bindir} \

    install -m 0755 ${S}/bin/ertza ${D}${bindir}/
    install -m 0755 ${S}/bin/pedale ${D}${bindir}/
    install -m 0755 ${S}/bin/modbus_rw ${D}${bindir}/

    install -m 0755 ${S}/ertza/armazb.conf ${D}${sysconfdir}/ertza/default.conf
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
    ${systemd_unitdir}/system/ertza.service \
    ${sysconfdir}/systemd/network/10-eth0.network \
    ${bindir}/ertza \
    ${bindir}/modbus_rw \
    ${bindir}/pedale \
    ${PYTHON_SITEPACKAGES_DIR}/ertza* \
"

CONFFILES_${PN} ="\
    ${sysconfdir}/ertza/default.conf \
"
