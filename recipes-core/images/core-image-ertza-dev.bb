require recipes-core/images/core-image-ertza.bb

LICENSE = "MIT"

IMAGE_INSTALL += "\
    git \
    vim \
"

export IMAGE_BASENAME = "core-image-ertza-dev"
