SUMMARY = "NumPy: array processing for numbers, strings, records, and objects."
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD;md5=3775480a712fc46a69647678acb234cb"
SRCNAME = "numpy"
PR = "r1"

SRC_URI = "https://pypi.python.org/packages/source/n/${SRCNAME}/${SRCNAME}-${PV}.zip"
S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools3

RDEPENDS_${PN} = "\
    python3 \
"

SRC_URI[md5sum] = "e80c19d2fb25af576460bb7dac31c59a"
SRC_URI[sha256sum] = "e37805754f4ebb575c434d134f6bebb8b857d9843c393f6943c7be71ef57311c"
