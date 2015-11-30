DESCRIPTION = "A sophisticated Numeric Processing Package for Python"
SECTION = "devel/python"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=f87832d854acbade6e9f5c601c8b30b1"
PR = "r1"

DEPENDS = " \
"

SRC_URI = "${SOURCEFORGE_MIRROR}/numpy/numpy-${PV}.tar.gz"
#           ${CONFIGFILESURI} "

#CONFIGFILESURI ?= ""
#
#CONFIGFILESURI_aarch64 = " \
#    file://config.h \
#    file://_numpyconfig.h \
#"
#CONFIGFILESURI_arm = " \
#    file://config.h \
#    file://numpyconfig.h \
#"
#CONFIGFILESURI_armeb = " \
#    file://config.h \
#    file://numpyconfig.h \
#"
#CONFIGFILESURI_mipsel = " \
#    file://config.h \
#    file://numpyconfig.h \
#"
#CONFIGFILESURI_i586 = " \
#    file://config.h \
#    file://numpyconfig.h \
#"
#CONFIGFILESURI_x86-64 = " \
#    file://config.h \
#    file://_numpyconfig.h \
#"

S = "${WORKDIR}/numpy-${PV}"

inherit distutils3

# Make the build fail and replace *config.h with proper one
# This is a ugly, ugly hack - Koen
#do_compile_prepend() {
#    BUILD_SYS=${BUILD_SYS} HOST_SYS=${HOST_SYS} \
#    ${STAGING_BINDIR_NATIVE}/python3-native/python3 setup.py build ${DISTUTILS_BUILD_ARGS} || \
#    true
#    cp ${WORKDIR}/*config.h ${S}/build/$(ls ${S}/build | grep src | grep 3.)/numpy/core/include/numpy/
#}

FILES_${PN}-staticdev += "${PYTHON_SITEPACKAGES_DIR}/numpy/core/lib/*.a"

SRC_URI[md5sum] = "a1ed53432dbcd256398898d35bc8e645"
SRC_URI[sha256sum] = "325e5f2b0b434ecb6e6882c7e1034cc6cdde3eeeea87dbc482575199a6aeef2a"

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

#RDEPENDS_${PN}_class-native = ""

BBCLASSEXTEND = "native nativesdk"
