SUMMARY = "A Modbus library"
DESCRIPTION = "libmodbus is a C library designed to provide a fast and robust \
implementation of the Modbus protocol. It runs on Linux, Mac OS X, FreeBSD, \
QNX and Windows."
HOMEPAGE = "http://www.libmodbus.org/"
SECTION = "libs"

LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=4fbd65380cdd255951079008b364516c"

SRCREV = "41cc47398e191c9fb0abee8cd90f7642d0375582"

PR = "r1"
PV = "3.1.2+git${SRCPV}"

SRC_URI = "git://github.com/stephane/libmodbus.git"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

#SRC_URI[md5sum] = "18860375a7aa0ca9cba7d6ece54ab666"
#SRC_URI[sha256sum] = "19aad5d55fa315602d6e836a858a3802f1608f9d824afba05fa12a58a1b1e656"
