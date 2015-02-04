# Copyright (C) 2015 Benoit Rapidel <benoit.rapidel@exmachina.fr>
# Released under the MIT license (see COPYING.MIT for the terms)

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += "file://hostname \
            file://fstab \
            file://hosts \
            "

RDEPENDS_${PN}-armaz-conf="\
    useradd-ertza \
"

do_install_append() {
    install -d -o ertza -g ertza -p -m 755 ${D}/home/data/
    install -m 0644 hostname ${D}${sysconfdir}
    install -m 0644 fstab ${D}${sysconfdir}
    install -m 0644 hosts ${D}${sysconfdir}
}

PACKAGES =+ "${PN}-armaz-conf"

FILES_${PN}-armaz-conf = " \
    ${sysconfdir}/hostname \
    ${sysconfdir}/fstab \
    ${sysconfdir}/hosts \
    /home/data \
"
