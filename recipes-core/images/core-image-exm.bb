require recipes-images/angstrom/console-base-image.bb

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

IMAGE_INSTALL_remove = " packagegroup-base-extended psplash-default connman-plugin-wifi"

IMAGE_FEATURES = "package-management"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"

DISTRO_FEATURES_remove = "alsa x11 nfs nfc bluetooth wifi opengl wayland pulseaudio 3g irda"
MACHINE_FEATURES_remove = "alsa x11 nfs nfc bluetooth wifi opengl wayland pulseaudio 3g irda"

DISTRO_FEATURES_append = " serial"

IPK_FEED_URIS = "http://pkg.exmachina.fr/beaglebone"

ROOTFS_POSTPROCESS_COMMAND += "set_root_passwd;"

set_root_passwd() {
    _passwd='$6$ORUv90Oe$3uFnMJDJFskNrkQWVbh1nv5TGB7a8xM3BVNwrJDK0i6W7WkrzO9chWxWvPkmuaUo3vZqnFnpgUPeWSH/bjChD/'
    sed "s%^root:[^:]*:%root:${_passwd}:%" \
        < ${IMAGE_ROOTFS}/etc/shadow \
        > ${IMAGE_ROOTFS}/etc/shadow.new;
    mv ${IMAGE_ROOTFS}/etc/shadow.new ${IMAGE_ROOTFS}/etc/shadow ;

    sed "s%^root:[^:]*:%root:x:%" \
        < ${IMAGE_ROOTFS}/etc/passwd \
        > ${IMAGE_ROOTFS}/etc/passwd;
    mv ${IMAGE_ROOTFS}/etc/passwd ${IMAGE_ROOTFS}/etc/passwd ;
}

export IMAGE_BASENAME = "core-image-exm"
