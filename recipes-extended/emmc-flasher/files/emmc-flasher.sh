#!/usr/bin/env bash

if ! id | grep -q root; then
    echo "$0 must be run as root"
    exit
fi

PLATFORM="beaglebone"
SRC="http://pkg.exmachina.fr/pkgs/images/${PLATFORM}/auto-update"
IMG="emmc-image-ertza-latest.img"
MD5="emmc-image-ertza-latest.md5sum"

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

BOOT_SECT=$(fdisk -l -o Start,Size,Id $IMG | sed '1,8d' | head -n1 | sed 's/^[ \t]*//' | sed 's/ \+/,/g')
ROOT_SECT=$(fdisk -l -o Start,Size,Id $IMG | sed '1,8d' | tail -n1 | sed 's/^[ \t]*//' | sed 's/ \+/,/g')
echo $BOOT_SECT

sfdisk $TARGET_MMC << EOF
$(echo $BOOT_SECT | cut -d, -f1),100M,$(echo $BOOT_SECT | cut -d, -f3),*
,,,-
EOF

sync

BOOT_PART="${TARGET_MMC}p1"
ROOT_PART="${TARGET_MMC}p2"

mkfs.fat "${BOOT_PART}"
mkfs.ext4 "${ROOT_PART}"

sync

echo "Writing $IMG to $TARGET_MMC…"
dd if="./$IMG" skip=$(echo $BOOT_SECT | cut -d, -f1) of=$BOOT_PART bs=512 count=$(echo $BOOT_SECT | cut -d, -f2 | sed 's/M/K/')
dd if="./$IMG" skip=$(echo $ROOT_SECT | cut -d, -f1) of=$ROOT_PART bs=512
echo "Writing $IMG to $TARGET_MMC… Done." || exit 3

sync

echo
echo "Press [Enter] to reboot. Please remove sdcard after poweroff."
read reboot
reboot
