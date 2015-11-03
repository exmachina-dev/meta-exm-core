require recipes-core/images/core-image-minimal.bb

SUMMARY = "A console-only image that fully supports the target device \
hardware."

LICENSE = "MIT"

IMAGE_INSTALL += "\
    useradd-ertza \
    ertza \
    opkg \
"
###     ertza

#IMAGE_INSTALL_remove = " packagegroup-base-extended psplash-default"

IMAGE_FEATURES_append = " package-management"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_EXTRA_SPACE = "202048"

CORE_IMAGE_EXTRA_INSTALL += "kernel-modules kernel-devicetree"

DISTRO_FEATURES_remove = "alsa x11 nfs bluetooth wifi opengl pulseaudio 3g irda tk"
#MACHINE_FEATURES_remove = "alsa x11 nfs nfc bluetooth wifi opengl wayland pulseaudio 3g irda"

#DISTRO_FEATURES_append = " serial usbnet"
DISTRO_FEATURES_append = "tsp65217"

CONMANPKGS_remove = "connman-plugin-wifi"

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
        > ${IMAGE_ROOTFS}/etc/passwd.new;
    mv ${IMAGE_ROOTFS}/etc/passwd.new ${IMAGE_ROOTFS}/etc/passwd ;
}

export IMAGE_BASENAME = "core-image-ertza"
