#!/usr/bin/env bash

if ! id | grep -q root; then
    echo "$0 must be run as root"
    exit
fi

PLATFORM="beaglebone"
SRC="http://pkg.exmachina.fr/pkgs/images/${PLATFORM}/auto-update"
IMG="@TARGET_IMG@-latest.img"
MD5="@TARGET_IMG@-latest.md5sum"

TARGET_MMC="/dev/mmcblk1"

wget "$SRC/$MD5" || exit 1
wget "$SRC/$IMG" || exit 1
md5sum -cs $MD5 || exit 1

echo
echo " ##### WARNING ##### "
echo -n "I'm about to erase all data on ${TARGET_MMC} and burn it with ${IMG}. Are you sure? [y/N]"
read confirm
if [[ "x$confirm" != "xy" ]]; then
    echo "Aborting"
    exit 2
fi

echo "Writing $IMG to $TARGET_MMC…"
dd if="./$IMG" of="$TARGET_MMC" bs=16M && \
echo "Writing $IMG to $TARGET_MMC… Done." || exit 3

echo "Press any key to reboot. Please remove sdcard."
read reboot
reboot
