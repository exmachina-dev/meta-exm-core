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
"

IMAGE_INSTALL_remove = " packagegroup-base-extended psplash-default"

IMAGE_FEATURES = "package-management"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"

DISTRO_FEATURES_remove = "alsa x11 nfs nfc bluetooth wifi opengl wayland pulseaudio 3g irda"

DISTRO_FEATURES_append = " serial"

IPK_FEED_URIS = "http://pkg.exmachina.fr/beaglebone"

ROOTFS_POSTPROCESS_COMMAND += "set_root_passwd;"

set_root_passwd() {
   sed 's%^root:[^:]*:%root:$6$Vwwla/7I2eD0$nYDVg6q5A.3gm7RBBysNuRJwsrbw6NXNiIU6Al/Trr3qKSpb98dslQtOQD6gc8z4fQLFx094i4vhqqaR4VZG5/:%' \
       < ${IMAGE_ROOTFS}/etc/shadow \
       > ${IMAGE_ROOTFS}/etc/shadow.new;
   mv ${IMAGE_ROOTFS}/etc/shadow.new ${IMAGE_ROOTFS}/etc/shadow ;
}
