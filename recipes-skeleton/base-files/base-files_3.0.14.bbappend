# Copyright (C) 2015 Benoit Rapidel <benoit.rapidel@exmachina.fr>
# Released under the MIT license (see COPYING.MIT for the terms)

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += " \
    file://hostname \
    file://fstab \
    file://hosts \
"

PROVIDES += "base-files-armaz"

DEPENDS_${PN}-armaz = "\
    useradd-ertza \
"

RDEPENDS_${PN}-armaz = "\
    useradd-ertza \
"

do_install_append() {
    install -m 0644 ${WORKDIR}/hostname ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/fstab ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/hosts ${D}${sysconfdir}
}

FILES_${PN}-armaz = " \
    ${sysconfdir}/hostname \
    ${sysconfdir}/fstab \
    ${sysconfdir}/hosts \
"
