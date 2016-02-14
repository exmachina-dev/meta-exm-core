# COPYRIGHT (C) 2016 Benoit Rapidel <benoit.rapidel@exmachina.fr>
# Released under the MIT license

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LICENSE = "MIT"

SRC_URI += " \
    file://ntp.conf \
"

do_install_append() {
    install -m 0644 ${WORKDIR}/ntp.conf ${D}${sysconfdir}/ntp.conf
}

FILES_${PN}_append = " \
    ${sysconfdir}/ntp.conf \
"
