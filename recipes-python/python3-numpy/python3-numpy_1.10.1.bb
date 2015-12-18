SUMMARY = "A sophisticated Numeric Processing Package for Python"
SECTION = "devel/python3"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9f4e88b5748e8313caaf33d081ce65a3"

SRC_URI = "${SOURCEFORGE_MIRROR}/numpy/numpy-${PV}.tar.gz \
           file://0001-Don-t-search-usr-and-so-on-for-libraries-by-default-.patch \
           ${CONFIGFILESURI} "

CONFIGFILESURI ?= ""

CONFIGFILESURI_aarch64 = " \
    file://config.h \
    file://_numpyconfig.h \
"
CONFIGFILESURI_arm = " \
    file://config.h \
    file://numpyconfig.h \
"
CONFIGFILESURI_armeb = " \
    file://config.h \
    file://numpyconfig.h \
"
CONFIGFILESURI_mipsel = " \
    file://config.h \
    file://numpyconfig.h \
"
CONFIGFILESURI_x86 = " \
    file://config.h \
    file://numpyconfig.h \
"
CONFIGFILESURI_x86-64 = " \
    file://config.h \
    file://_numpyconfig.h \
"
CONFIGFILESURI_mips = " \
    file://config.h \
    file://_numpyconfig.h \
"
CONFIGFILESURI_powerpc = " \
    file://config.h \
    file://_numpyconfig.h \
"
CONFIGFILESURI_powerpc64 = " \
    file://config.h \
    file://_numpyconfig.h \
"
CONFIGFILESURI_mips64 = " \
    file://config.h \
    file://_numpyconfig.h \
"
CONFIGFILESURI_mips64n32 = " \
    file://config.h \
    file://_numpyconfig.h \
"

S = "${WORKDIR}/numpy-${PV}"

inherit distutils3

# Make the build fail and replace *config.h with proper one
# This is a ugly, ugly hack - Koen
do_compile_prepend_class-target() {
    BUILD_SYS=${BUILD_SYS} HOST_SYS=${HOST_SYS} \
    ${STAGING_BINDIR_NATIVE}/python3-native/python3 setup.py build ${DISTUTILS_BUILD_ARGS} || \
    true
    cp ${WORKDIR}/*config.h ${S}/build/$(ls ${S}/build | grep src | grep "3.")/numpy/core/include/numpy/
}

FILES_${PN}-staticdev += "${PYTHON_SITEPACKAGES_DIR}/numpy/core/lib/*.a"

SRC_URI[md5sum] = "3fed2b50906bc19018cec5fa26168aa5"
SRC_URI[sha256sum] = "8b9f453f29ce96a14e625100d3dcf8926301d36c5f622623bf8820e748510858"

# install what is needed for numpy.test()
RDEPENDS_${PN} = "python3-unittest \
                  python3-difflib \
                  python3-pprint \
                  python3-pickle \
                  python3-shell \
                  python3-nose \
                  python3-doctest \
                  python3-datetime \
                  python3-distutils \
                  python3-misc \
                  python3-mmap \
                  python3-netclient \
                  python3-numbers \
                  python3-pydoc \
                  python3-pkgutil \
                  python3-email \
                  python3-subprocess \
                  python3-compression \
"

RDEPENDS_${PN}_class-native = ""

