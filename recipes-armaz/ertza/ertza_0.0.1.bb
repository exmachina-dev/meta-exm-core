SUMMARY = "Armaz main software"
DESCRIPTION = "Ertza is a Python program designed to provide functionalities\
for Armaz."
HOMEPAGE = "http://www.exmachina.fr/"
SECTION = "libs"

LICENSE = "none"
LIC_FILES_CHKSUM = "file://COPYING;md5=d41d8cd98f00b204e9800998ecf8427e"

SRCREV = "f896a0ab7e3e5cec6ee55ec304aa8e6d98a2dc06"

PR = "r2"
PV = "0.0.1+git${SRCPV}"

RDEPENDS_${PN} = "\
    packagegroup-python3 \
    python3 \
    python3-lang \
    python3-multiprocessing \
    python3-subprocess \
    python3-threading \
    python3-readline \
    python3-logging \
    python3-pyliblo \
    python3-pylibmodbus \
"

SRC_URI = "\
    git:///home/willykaze/repos/ertza;protocol=file;branch=config \
    file://init \
    file://ertza.service \
    file://ertza@.service \
"

S = "${WORKDIR}/git"

inherit setuptools3 update-rc.d systemd
INITSCRIPT_NAME = "ertza"
INITSCRIPT_PARAMS = "defaults 10"

SYSTEMD_SERVICE_${PN} = "ertza"


BINCOMMANDS = "ertza"

# need to export these variables for python-config to work
export BUILD_SYS
export HOST_SYS
export STAGING_INCDIR
export STAGING_LIBDIR

do_install_append() {
	install -d ${D}${sysconfdir} \
		${D}${sysconfdir}/init.d \
		${D}${sysconfdir}/ertza \
		${D}${bindir} \

	install -m 0755 ${S}/bin/ertza ${D}${bindir}/

	install -m 0755 ${S}/ertza/default.conf ${D}${sysconfdir}/ertza/

	sed -e 's,/etc,${sysconfdir},g' \
		-e 's,/usr/sbin,${sbindir},g' \
		-e 's,/var,${localstatedir},g' \
		-e 's,/usr/bin,${bindir},g' \
		-e 's,/usr,${prefix},g' ${WORKDIR}/init > ${D}${sysconfdir}/init.d/ertza
	chmod 755 ${D}${sysconfdir}/init.d/ertza

	# deal with systemd unit files
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/ertza@.service ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/ertza.service ${D}${systemd_unitdir}/system
}

FILES_${PN} = "${sysconfdir}/init.d/ertza \
               ${sysconfdir}/ertza/* \
               ${systemd_unitdir}/system/ertza@.service \
               ${systemd_unitdir}/system/ertza.service \
               ${bindir}/ertza \
               ${PYTHON_SITEPACKAGES_DIR}/ertza* \
"
