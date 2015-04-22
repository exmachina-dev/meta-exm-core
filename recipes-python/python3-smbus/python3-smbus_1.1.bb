# Copyright (C) 2015 Benoit Rapidel <benoit.rapidel@exmachina.fr>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Python bindings for Linux SMBus access through i2c-dev"
HOMEPAGE = "https://github.com/perillaseed/py3-smbus"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
SECTION = "devel/python"
DEPENDS = "python3 libffi"

SRCREV = "05081466569b7986e29b4f9a5dd2edba909d70f1"
PR = "r0"

SRC_URI = "git://github.com/perillaseed/py3-smbus.git"

S = "${WORKDIR}/git"

inherit setuptools3
