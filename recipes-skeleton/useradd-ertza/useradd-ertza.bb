SUMMARY = "Ertza user package"

DESCRIPTION = "Add ertza user and create home dir."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://bashrc \
           file://authorized_keys \
"

_USER = "ertza"
_GROUP = "ertza"
_HOMEDIR = "/home/ertza"
_SHELL = "/bin/bash"
_PASSWD = "exmachina"

S = "${WORKDIR}"

inherit useradd

USERADD_PACKAGES = "${PN}"

USERADD_PARAM_${PN} = "-u 1200 -d ${_HOMEDIR} -r -s ${_SHELL} -P '${_PASSWD}' ${_USER} "


#GROUPADD_PARAM_${PN} = "-g 880 ${_GROUP}"


do_install () {
        install -d -m 755 ${D}${_HOMEDIR}
        install -d -m 755 ${D}${_HOMEDIR}/.ssh

        install -p -m 644 bashrc ${D}${_HOMEDIR}/.bashrc
        install -p -m 644 authorized_keys ${D}${_HOMEDIR}/.ssh/authorized_keys

        chown -R ${_USER} ${D}${_HOMEDIR}

        chgrp -R ${_GROUP} ${D}${_HOMEDIR}
}

FILES_${PN} = "${_HOMEDIR}/.bashrc \
               ${_HOMEDIR}/.ssh/authorized_keys \
"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
