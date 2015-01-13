# Copyright (C) 2015 Benoit Rapidel <benoit.rapidel@exmachina.fr>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Configuration files for base system on armaz."
SECTION = "base"
PRIORITY = "required"
PR = "r0"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://fstab \
    file://hosts "
S = "${WORKDIR}"

#
# set standard hostname, might be a candidate for a DISTRO variable? :M:
#
hostname = "armaz"

do_install () {
    #for d in ${dirs755}; do
    #    install -m 0755 -d "${D}$d"
    #done
    #for d in ${dirs1777}; do
    #    install -m 1777 -d "${D}$d"
    #done
    #for d in ${dirs2775}; do
    #    install -m 2755 -d "${D}$d"
    #done
    #for d in ${volatiles}; do
    #            if [ -d "${D}${localstatedir}/volatile/$d" ]; then
    #                    ln -sf volatile/$d "${D}/${localstatedir}/$d"
    #            fi
    #done

    echo ${hostname} > "${D}${sysconfdir}/hostname"

    install -m 0644 "${WORKDIR}/fstab" "${D}${sysconfdir}/fstab"
    install -m 0644 "${WORKDIR}/hosts" "${D}${sysconfdir}/hosts"
}

PACKAGES = "${PN}-dbg ${PN}-doc ${PN}"
FILES_${PN} = "/*"
#FILES_${PN}-doc = "${docdir} ${datadir}/common-licenses"

PACKAGE_ARCH = "${MACHINE_ARCH}"
