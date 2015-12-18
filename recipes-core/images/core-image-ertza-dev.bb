require recipes-core/images/core-image-ertza.bb

LICENSE = "MIT"

IMAGE_INSTALL += "\
    opkg-config \
    git \
    vim \
    python3-pip \
    gcc \
    util-linux \
    htop \
"

export IMAGE_BASENAME = "core-image-ertza-dev"
