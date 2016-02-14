require recipes-core/images/core-image-ertza-dev.bb

SUMMARY = "A flasher image to burn latest emmc-image."

LICENSE = "MIT"

IMAGE_INSTALL += "\
    emmc-flasher \
    armaz-commissioning-wizard \
"

export IMAGE_BASENAME = "core-image-ertza-flasher"
