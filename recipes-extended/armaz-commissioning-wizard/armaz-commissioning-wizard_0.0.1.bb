# Copyright (C) 2016 Benoit Rapidel <benoit.rapidel@exmachina.fr>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Commissioning wizard for Armaz motor"
HOMEPAGE = ""

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RDEPENDS_${PN} = " \
    ertza-eeprom \
    set-rtc-clock \
"
PR = "r0"

SRC_URI = " \
    file://commissioning-wizard \
"

do_install() {
    install -d ${D}${bindir}
    install -m 0744 ${WORKDIR}/commissioning-wizard ${D}${bindir}
w
}

FILES_${PN} = " \
    ${bindir}/commissioning-wizard \
"
