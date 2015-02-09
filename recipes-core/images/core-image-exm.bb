require recipes-core/images/core-image-base.bb

SUMMARY = "A console-only image that fully supports the target device \
hardware."

LICENSE = "MIT"

IMAGE_INSTALL += "\
    ${ROOTFS_PKGMANAGE_BOOTSTRAP} \
    base-files-armaz-conf \
    useradd-ertza \
    ertza \
    opkg \
    distro-feed-configs \
"

IMAGE_INSTALL_remove = "\
    packagegroup-base-alsa \
    packagegroup-base-bluetooth \
    packagegroup-base-3g \
    packagegroup-base-wifi \
"

IMAGE_FEATURES_remove = "splash"
IMAGE_FEATURES += "package-management"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"

DISTRO_FEATURES_remove = "alsa x11 nfs nfc bluetooth wifi opengl wayland pulseaudio 3g irda"

DISTRO_FEATURES_append = " systemd serial"
VIRTUAL-RUNTIME_init_manager = "systemd"

DISTRO_FEATURES_BACKFILL_CONSIDERED = "sysvinit"
VIRTUAL-RUNTIME_initscripts = ""
