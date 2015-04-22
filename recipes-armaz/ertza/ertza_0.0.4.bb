SUMMARY = "Armaz main software"
DESCRIPTION = "Ertza is a Python program designed to provide functionalities\
for Armaz."
HOMEPAGE = "http://www.exmachina.fr/"
SECTION = "libs"

LICENSE = "none"
LIC_FILES_CHKSUM = "file://COPYING;md5=d41d8cd98f00b204e9800998ecf8427e"

SRCREV = "74b21c6e66d6a3acdb38921d9049cab7b8c32c8e"

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
    git:///home/willykaze/repos/ertza;protocol=file;branch=beaglebone \
    file://ertza.service \
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
    install -m 0755 ${S}/bin/modbus_rw ${D}${bindir}/

    install -m 0755 ${S}/ertza/default.conf ${D}${sysconfdir}/ertza/
    sed -i 's#@LOGPATH@#/home/ertza/.ertza#' ${D}${sysconfdir}/ertza/default.conf

    # deal with systemd unit files
    install -d ${D}${systemd_unitdir}/system

    sed -e 's,/etc,${sysconfdir},g' \
            -e 's,/usr/sbin,${sbindir},g' \
            -e 's,/var,${localstatedir},g' \
            -e 's,/usr/bin,${bindir},g' \
            -e 's,/usr,${prefix},g' ${WORKDIR}/ertza.service > ${D}${systemd_unitdir}/system/ertza.service
    chmod 644 ${D}${systemd_unitdir}/system/ertza.service
}

FILES_${PN} = "\
    ${sysconfdir}/ertza/* \
    ${systemd_unitdir}/system/ertza.service \
    ${bindir}/ertza \
    ${bindir}/modbus_rw \
    ${PYTHON_SITEPACKAGES_DIR}/ertza* \
"

CONFFILES_${PN} ="\
    ${sysconfdir}/ertza/default.conf \
"
