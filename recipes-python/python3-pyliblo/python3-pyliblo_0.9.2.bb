DESCRIPTION = "Python bindings for the liblo OSC library"
SECTION = "devel/python"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"
HOMEPAGE = "http://das.nasophon.de/pyliblo/"
DEPENDS = "python3 liblo"
SRCNAME = "pyliblo"

SRC_URI = "http://das.nasophon.de/download/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "4ff670f2ab724245e45b17601fa64c99"
SRC_URI[sha256sum] = "382ee7360aa00aeebf1b955eef65f8491366657a626254574c647521b36e0eb0"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools3
