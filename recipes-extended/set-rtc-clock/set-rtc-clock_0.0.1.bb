# Copyright (C) 2016 Benoit Rapidel <benoit.rapidel@exmachina.fr>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "A utility to set clock from internet and store it to RTC"
HOMEPAGE = ""

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SECTION = "devel/clock"
PR = "r0"

RDEPENDS_${PN} = " \
    ntp \
"

SRC_URI = " \
    file://set-rtc-clock \
"

do_install() {
    install -d ${D}${bindir}
    install -m 0744 ${WORKDIR}/set-rtc-clock ${D}${bindir}
}

FILES_${PN} = " \
    ${bindir}/set-rtc-clock \
"
