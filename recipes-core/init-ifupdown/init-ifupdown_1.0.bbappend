# Copyright (C) 2015 Benoit Rapidel <benoit.rapidel@exmachina.fr>
# Released under the MIT license (see COPYING.MIT for the terms)

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += " \
    file://interfaces-armaz \
"

do_install_append() {
    install -m 0644 ${WORKDIR}/interfaces-armaz ${D}${sysconfdir}/network/interfaces
}

CONFFILES_${PN} += " \
    ${sysconfdir}/network/interfaces \
"
