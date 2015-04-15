SUMMARY = "Armaz device tree"
DESCRIPTION = "Device tree for Armaz."
HOMEPAGE = "http://www.exmachina.fr/"
SECTION = "libs"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRCREV = "d211f69cd36497053e8f4b3cc25286bb1825e0d3"

PR = "r1"
PV = "0.0.1"

SRC_URI = "\
    file://BB-armaz-00A0.dts \
    file://init \
    file://armaz-dto.service \
"

inherit update-rc.d systemd
INITSCRIPT_NAME = "armaz-dto"
INITSCRIPT_PARAMS = "defaults 10"

SYSTEMD_SERVICE_${PN} = "armaz-dto"

do_install_append() {
    install -d ${D}${sysconfdir} \
            ${D}${sysconfdir}/ertza \
            ${D}${sysconfdir}/init.d \

    install -m 0755 ${WORKDIR}/BB-armaz-00A0.dts ${D}${sysconfdir}/ertza/

    sed -e 's,/etc,${sysconfdir},g' \
            -e 's,/usr/sbin,${sbindir},g' \
            -e 's,/var,${localstatedir},g' \
            -e 's,/usr/bin,${bindir},g' \
            -e 's,/usr,${prefix},g' ${WORKDIR}/init > ${D}${sysconfdir}/init.d/armaz-dto
    chmod 755 ${D}${sysconfdir}/init.d/armaz-dto

    # deal with systemd unit files
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/armaz-dto.service ${D}${systemd_unitdir}/system
}

FILES_${PN} = "\
    ${sysconfdir}/init.d/armaz-dto \
    ${sysconfdir}/ertza/BB-armaz-00A0.dts \
    ${systemd_unitdir}/system/armaz-dto.service \
"
