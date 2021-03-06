SUMMARY = "Library to enable your code run as a daemon process on Unix-like systems"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=13e982bf1b7b164b9d6d1665dac83873"
SRCNAME = "daemonize"
PR = "r1"

SRC_URI = "https://pypi.python.org/packages/source/d/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools3

SRC_URI[md5sum] = "bb5b341b94abc15d6788002cafaa9da4"
SRC_URI[sha256sum] = "c21a94cd676d3fbb718af62e6f22209afc7000a9588169b6fb779d62b04e2378"
