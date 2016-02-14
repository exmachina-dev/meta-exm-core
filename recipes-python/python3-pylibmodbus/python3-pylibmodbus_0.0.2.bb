DESCRIPTION = "Python Interface for libmodbus written with CFFI"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a5b6cd5362278a24fe0a59eff3333677"
HOMEPAGE = "https://github.com/stephane/pylibmodbus"
SRCNAME = "pylibmodbus"

DEPENDS = "python3 python3-ctypes"
RDEPENDS_${PN} = "python3-ctypes libmodbus"

SRCREV = "42b2d1619a6f1ffbfafcde9807ed008af50e044d"

PR = "r1"
PV = "0.0.2+git${SRCPV}"

BRANCH = "python3"
SRC_URI = " \
    git:///home/willykaze/repos/pylibmodbus;protocol=file;branch=${BRANCH} \
    file://0001-Fix-libmodbus-path.patch \
"

S = "${WORKDIR}/git"

#S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools3
