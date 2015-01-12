DESCRIPTION = "Python Interface for libmodbus written with CFFI"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a5b6cd5362278a24fe0a59eff3333677"
HOMEPAGE = "https://github.com/stephane/pylibmodbus"
DEPENDS = "python3 libffi"
SRCNAME = "pylibmodbus"

SRCREV = "d084dd18d1399afa8624c7c294aae779c9e0f48d"

PR = "r1"
PV = "0.0.2+git${SRCPV}"

SRC_URI = "git://github.com/stephane/pylibmodbus.git"

S = "${WORKDIR}/git"

#S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit distutils
