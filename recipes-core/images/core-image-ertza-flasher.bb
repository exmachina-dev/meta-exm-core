require recipes-core/images/core-image-minimal.bb

SUMMARY = "A flasher image to burn latest emmc-image."

LICENSE = "MIT"

IMAGE_INSTALL += "\
    useradd-ertza \
    opkg \
    emmc-flasher \
"
###     ertza

IMAGE_FEATURES_append = " package-management"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

IMAGE_ROOTFS_SIZE ?= "2097152"

CORE_IMAGE_EXTRA_INSTALL += "kernel-modules kernel-devicetree dropbear"

DISTRO_FEATURES_remove = "alsa x11 nfs bluetooth wifi opengl pulseaudio 3g irda tk"
#MACHINE_FEATURES_remove = "alsa x11 nfs nfc bluetooth wifi opengl wayland pulseaudio 3g irda"

CONMANPKGS_remove = "connman-plugin-wifi"

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

export IMAGE_BASENAME = "core-image-ertza-flasher"
