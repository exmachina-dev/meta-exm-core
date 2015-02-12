DESCRIPTION = "A full modbus protocol written in python "
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://doc/LICENSE;md5=2c2223d66c7e674b40527b5a4c35bd76"
HOMEPAGE = "https://github.com/bashwork/pymodbus"
RDEPENDS_${PN} = "python3 python3-pyserial"
SRCNAME = "pymodbus"

SRCREV = "d7fc4f1cc975631e0a9011390e8017f64b612661"

PR = "r1"
PV = "1.2.0+git${SRCPV}"

SRC_URI = "git://github.com/bashwork/pymodbus.git;branch=python3"

S = "${WORKDIR}/git"

#S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools3
