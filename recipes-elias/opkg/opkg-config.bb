SUMMARY = "Opkg config files"
DESCRIPTION = "Base configuration files for opkg"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "\
    file://src-dev.conf \
"

DEPENDS_${PN} = "opkg"
RDEPENDS_${PN} = "opkg"

do_install_append() {
    install -d ${D}${sysconfdir}/opkg/
    install -m 0644 ${WORKDIR}/src-dev.conf ${D}${sysconfdir}/opkg/
}

FILES_${PN} = "\
    ${sysconfdir}/opkg/src-dev.conf \
"
