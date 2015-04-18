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
    file://BB-BONE-ARMAZ-00A0.dtbo \
    file://BB-BONE-ARMAZ-00A0.dts \
    file://init \
    file://armaz-dto.sh \
    file://armaz-dto.service \
"

inherit update-rc.d systemd
INITSCRIPT_NAME = "armaz-dto"
INITSCRIPT_PARAMS = "defaults 10"

SYSTEMD_SERVICE_${PN} = "armaz-dto.service"

do_install_append() {
    install -d \
            ${D}/lib/firmware \
            ${D}${bindir} \
            ${D}${sysconfdir}/init.d \
            ${D}${systemd_unitdir}/system

    install -m 0644 ${WORKDIR}/BB-BONE-ARMAZ-00A0.dtbo ${D}/lib/firmware/
    install -m 0644 ${WORKDIR}/BB-BONE-ARMAZ-00A0.dts ${D}/lib/firmware/

    chown xuser:xuser ${D}/lib/firmware/BB-BONE-ARMAZ-00A0.dtbo
    chown xuser:xuser ${D}/lib/firmware/BB-BONE-ARMAZ-00A0.dts

    install -m 0755 ${WORKDIR}/armaz-dto.sh ${D}${bindir}

    sed -e 's,/etc,${sysconfdir},g' \
            -e 's,/usr/sbin,${sbindir},g' \
            -e 's,/var,${localstatedir},g' \
            -e 's,/usr/bin,${bindir},g' \
            -e 's,/usr,${prefix},g' ${WORKDIR}/init > ${D}${sysconfdir}/init.d/armaz-dto
    chmod 755 ${D}${sysconfdir}/init.d/armaz-dto

    # deal with systemd unit files
    install -m 0644 ${WORKDIR}/armaz-dto.service ${D}${systemd_unitdir}/system
}

FILES_${PN} = "\
    /lib/firmware/BB-BONE-ARMAZ-00A0.dtbo \
    /lib/firmware/BB-BONE-ARMAZ-00A0.dts \
    ${bindir}/armaz-dto.sh \
    ${sysconfdir}/init.d/armaz-dto \
    ${systemd_unitdir}/system/armaz-dto.service \
"
