SUMMARY = "Library to implement a well-behaved Unix daemon process"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.ASF-2;md5=3b83ef96387f14655fc854ddc3c6bd57"
SRCNAME = "python-daemon"
PR = "r1"

SRC_URI = "https://pypi.python.org/packages/source/p/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools3

RDEPENDS_${PN} = "\
    python3-lockfile \
    python3-resource \
"

SRC_URI[md5sum] = "72e2acf2c3d69c7fa75a6625d06adfd0"
SRC_URI[sha256sum] = "58a8c187ee37c3a28913bef00f83240c9ecd4a59dce09a24d92f5c941606689f"
