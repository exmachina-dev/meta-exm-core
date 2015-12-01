# Copyright (C) 2015 Benoit Rapidel <benoit.rapidel@exmachina.fr>
# Released under the MIT license (see COPYING.MIT for the terms)

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += " \
    file://hosts \
"

do_install_append() {
    install -m 0644 ${WORKDIR}/hosts ${D}${sysconfdir}
}

FILES_${PN} += " \
    ${sysconfdir}/hosts \
"
