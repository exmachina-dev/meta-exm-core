SUMMARY = "Simple construction, analysis and modification of binary data for Python"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SRCNAME = "bitstring"
PR = "r1"

SRC_URI = "https://pypi.python.org/packages/source/b/${SRCNAME}/${SRCNAME}-${PV}.zip"
S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools3

RDEPENDS_${PN} = "\
    python3 \
"

SRC_URI[md5sum] = "1db5d54ee269f6b54d50c3eb257eea4b"
SRC_URI[sha256sum] = "2d00f66655567cc241af5f6f5c155727daccdb03ddfe58f3814cedaad179f5fa"
