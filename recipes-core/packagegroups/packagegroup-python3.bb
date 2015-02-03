# Copyright (C) 2015 Benoit Rapidel <benoit.rapidel@exmachina.fr>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Python3"
DESCRIPTION = "Packages required to run full version of python3"
PR = "r1"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-python3 \
    "

RDEPENDS_packagegroup-python3 = "\
    python3 \
    python3-compile \
    python3-compression \
    python3-core \
    python3-curses \
    python3-datetime \
    python3-difflib \
    python3-distutils \
    python3-elementtree \
    python3-email \
    python3-fcntl \
    python3-importlib \
    python3-json \
    python3-logging \
    python3-misc \
    python3-mmap \
    python3-multiprocessing \
    python3-netclient \
    python3-netserver \
    python3-pickle \
    python3-pkgutil \
    python3-pprint \
    python3-re \
    python3-shell \
    python3-sqlite3 \
    python3-subprocess \
    python3-textutils \
    python3-unittest \
    python3-unixadmin \
    python3-xmlrpc \
    "
