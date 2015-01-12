require recipes-core/images/core-image-minimal.bb

SUMMARY = "A console-only image that fully supports the target device \
hardware."

LICENSE = "MIT"

IMAGE_INSTALL += "\
python3 \
python3-pyliblo \
python3-pylibmodbus \
"
CORE_IMAGE_EXTRA_INSTALL += "\
dropbear \
"
