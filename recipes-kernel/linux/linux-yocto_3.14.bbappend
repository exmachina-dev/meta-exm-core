# Copyright (C) 2015 Benoit Rapidel <benoit.rapidel@exmachina.fr>
# Released under the MIT license (see COPYING.MIT for the terms)

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-yocto-3.14:"

SRC_URI += " \
    file://uart/0001-enable-uart4.patch \
    file://uart/0002-enable-uart5.patch \
"
