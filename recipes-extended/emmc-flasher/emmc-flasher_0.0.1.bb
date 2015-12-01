# Copyright (C) 2015 Benoit Rapidel <benoit.rapidel@exmachina.fr>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Flasher tool to update emmc to latest image"
HOMEPAGE = "https://www.github.com/exmachina-dev/meta-exm-core.git"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
DEPENDS = ""
PR = "r0"

SRC_URI = "\
    file://emmc-flasher.sh \
"

RDEPENDS_${PN} = "bash"

TARGET_IMG = "emmc-image-ertza"

do_install() {
    install -d ${D}${bindir}
    sed  -e "s/@TARGET_IMG@/${TARGET_IMG}/" \
        ${WORKDIR}/emmc-flasher.sh > ${D}${bindir}/emmc-flasher
    chmod 777 ${D}${bindir}/emmc-flasher
}

FILE_${PN} = "\
    ${bindir}/emmc-flasher \
"
