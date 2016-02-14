DESCRIPTION = "A full modbus protocol written in python "
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://doc/LICENSE;md5=2c2223d66c7e674b40527b5a4c35bd76"
HOMEPAGE = "https://github.com/exmachina-dev/pymodbus.git"
RDEPENDS_${PN} = "python3 python3-pyserial"
SRCNAME = "pymodbus"

SRCREV = "52f25307589632beee823992868915533b0b14e6"

PR = "r1"
PV_append = "+git${SRCPV}"

SRC_URI = "git://github.com/exmachina-dev/pymodbus.git;branch=python3"

S = "${WORKDIR}/git"

#S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools3
