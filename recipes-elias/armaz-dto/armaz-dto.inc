SUMMARY = "Armaz device tree"
DESCRIPTION = "Device tree for Armaz."
HOMEPAGE = "http://www.exmachina.fr/"
SECTION = "libs"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "\
    file://BB-BONE-ARMAZCAPE-${_DTO_REV}.dtbo \
    file://BB-BONE-ARMAZCAPE-${_DTO_REV}.dts \
    file://armaz-dto.sh \
    file://armaz-dto.service \
"

S = "${WORKDIR}"

inherit systemd

SYSTEMD_SERVICE_${PN} = "armaz-dto.service"

do_install_append() {
    install -d \
            ${D}/lib/firmware \
            ${D}${bindir} \
            ${D}${systemd_unitdir}/system

    # Curently not working
    #dtc -O dtb -o ${WORKDIR}/BB-BONE-ARMAZ-00A0.dtbo -b 0 -@ ${WORKDIR}/BB-BONE-ARMAZ-00A0.dts

    install -m 0644 ${WORKDIR}/BB-BONE-ARMAZCAPE-${_DTO_REV}.dtbo ${D}/lib/firmware/
    install -m 0644 ${WORKDIR}/BB-BONE-ARMAZCAPE-${_DTO_REV}.dts ${D}/lib/firmware/

#    chown 1000:1000 ${D}/lib/firmware/BB-BONE-ARMAZ-00A0.dtbo
#    chown 1000:1000 ${D}/lib/firmware/BB-BONE-ARMAZ-00A0.dts

    install -m 0755 ${WORKDIR}/armaz-dto.sh ${D}${bindir}
    sed -e "s,${_DTO_REV},\${revision},g" \
            ${WORKDIR}/armaz-dto.sh > ${D}${bindir}/armaz-dto.sh

    # deal with systemd unit files
    sed -e 's,/etc,${sysconfdir},g' \
            -e 's,/usr/sbin,${sbindir},g' \
            -e 's,/var,${localstatedir},g' \
            -e 's,/usr/bin,${bindir},g' \
            -e 's,/usr,${prefix},g' \
            -e "s,${_DTO_REV},\${revision},g" \
            ${WORKDIR}/armaz-dto.service > ${D}${systemd_unitdir}/system/armaz-dto.service
    chmod 644 ${D}${systemd_unitdir}/system/armaz-dto.service
}

FILES_${PN} = "\
    /lib/firmware/BB-BONE-ARMAZCAPE-${_DTO_REV}.dtbo \
    /lib/firmware/BB-BONE-ARMAZCAPE-${_DTO_REV}.dts \
    ${bindir}/armaz-dto.sh \
    ${systemd_unitdir}/system/armaz-dto.service \
"
