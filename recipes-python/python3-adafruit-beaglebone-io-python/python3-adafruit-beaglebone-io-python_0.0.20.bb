DESCRIPTION = "A module to control BeagleBone IO channels"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
HOMEPAGE = "https://github.com/exmachina-dev/adafruit-beaglebone-io-python"
RDEPENDS_${PN} = "python3 python3-pyserial"
SRCNAME = "pymodbus"

SRCREV = "470ff8288190a600370ad419d6d8755d3d549a51"

PR = "r1"
PV = "0.0.20+git${SRCPV}"

SRC_URI = "git://github.com/exmachina-dev/adafruit-beaglebone-io-python.git"

S = "${WORKDIR}/git"

#S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools3
