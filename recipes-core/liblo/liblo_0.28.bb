SUMMARY = "Lightweight OSC implementation"

DESCRIPTION = "liblo is an implementation of the Open Sound Control protocol for POSIX \
systems, started by Steve Harris and now maintained by Stephen Sinclair. It is released \
under the GNU Lesser General Public Licence version 2.1 or greater. This means that if \
it is included in closed-source systems, it must be dynamically linked such that the \
LibLO code remains freely modifiable."

HOMEPAGE = "http://liblo.sourceforge.net/"
BUGTRACKER = "http://sourceforge.net/p/liblo/bugs/"
SECTION = "libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

SRC_URI = "http://downloads.sourceforge.net/liblo/liblo-${PV}.tar.gz"
SRC_URI[md5sum] = "e2a4391a08b49bb316c03e2034e06fa2"
SRC_URI[sha256sum] = "da94a9b67b93625354dd89ff7fe31e5297fc9400b6eaf7378c82ee1caf7db909"

PR = "1"

#DEPENDS = "libxml2 glibmm"

inherit autotools gettext

EXTRA_OECONF = "'CFLAGS=-O2'"

#do_compile_ptest() {
#  oe_runmake -C examples buildtest
#}

#FILES_${PN}-doc += "${datadir}/devhelp"
#FILES_${PN}-dev += "${libdir}/libxml++-2.6/include/libxml++config.h"

#RDEPENDS_${PN}-ptest += "make"
