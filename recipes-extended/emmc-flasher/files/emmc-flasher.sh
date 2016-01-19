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

echo "timer" > /sys/devices/platform/leds/leds/beaglebone\:green\:heartbeat/trigger
echo "none" > /sys/devices/platform/leds/leds/beaglebone\:green\:mmc0/trigger
echo "none" > /sys/devices/platform/leds/leds/beaglebone\:green\:usr2/trigger
echo "none" > /sys/devices/platform/leds/leds/beaglebone\:green\:usr3/trigger

if [ "x$1" != "xNO_DOWNLOAD" ]; then
    if [ -f $IMG ]; then
        echo "Removing $IMG"
        rm $IMG
    fi
    if [ -f $MD5 ]; then
        echo "Removing $MD5"
        rm $MD5
    fi

    echo "timer" > /sys/devices/platform/leds/leds/beaglebone\:green\:heartbeat/trigger


    wget "$SRC/$MD5" || exit 1
    wget "$SRC/$IMG" || exit 1
else
    if [ -f $IMG ]; then
        echo "Found $IMG"
    else
        echo "No image file found"
        exit 1
    fi
    if [ -f $MD5 ]; then
        echo "Found $MD5"
    else
        echo "No MD5SUM file found"
        exit 1
    fi
fi

echo "timer" > /sys/devices/platform/leds/leds/beaglebone\:green\:heartbeat/trigger

echo -n "Checking image file agains MD5 checksum…"
md5sum -cs $MD5 || exit 1 && echo " Done."

echo "default-on" > /sys/devices/platform/leds/leds/beaglebone\:green\:heartbeat/trigger

BOOT_SECT=$(fdisk -l -o Start,Sectors,Id $IMG | sed '1,8d' | head -n1 | sed 's/^[ \t]*//' | sed 's/ \+/,/g')
ROOT_SECT=$(fdisk -l -o Start,Sectors,Id $IMG | sed '1,8d' | tail -n1 | sed 's/^[ \t]*//' | sed 's/ \+/,/g')

BOOT_TYPE=$(echo $BOOT_SECT | cut -d, -f3)
BOOT_START=$(echo $BOOT_SECT | cut -d, -f1)
BOOT_SIZE=$(echo $BOOT_SECT | cut -d, -f2)
ROOT_START=$(echo $ROOT_SECT | cut -d, -f1)
ROOT_SIZE=$(echo $ROOT_SECT | cut -d, -f2)

BOOT_PART="${TARGET_MMC}p1"
ROOT_PART="${TARGET_MMC}p2"

echo "Boot sector found in image: $BOOT_START $BOOT_SIZE $BOOT_TYPE"
echo "Root sector found in image: $ROOT_START $ROOT_SIZE"

echo
echo " ##### WARNING ##### WARNING ##### WARNING ##### "
echo " #                                             # "
echo " #       I'm about to wipe _all_ data on       # "
echo -n " # "
echo -n $TARGET_MMC | sed  -e :a -e 's/^.\{1,41\}$/ & /;ta'
echo " #"
echo " #                    and burn                 # "
echo -n " # "
echo -n $IMG | sed  -e :a -e 's/^.\{1,41\}$/ & /;ta'
echo " #"
echo " #              image data to it.              # "
echo " #                                             # "
echo " ##### WARNING ##### WARNING ##### WARNING ##### "
echo -e -n "\nAre you sure? [y/N]"

echo "timer" > /sys/devices/platform/leds/leds/beaglebone\:green\:mmc0/trigger
echo "timer" > /sys/devices/platform/leds/leds/beaglebone\:green\:usr2/trigger
echo "timer" > /sys/devices/platform/leds/leds/beaglebone\:green\:usr3/trigger

read confirm
if [[ "x$confirm" != "xy" ]]; then
    echo "Aborting"
    exit 2
fi

echo "timer" > /sys/devices/platform/leds/leds/beaglebone\:green\:mmc0/trigger
echo "none" > /sys/devices/platform/leds/leds/beaglebone\:green\:usr2/trigger
echo "none" > /sys/devices/platform/leds/leds/beaglebone\:green\:usr3/trigger

echo -e "Wiping partition table on ${TARGET_MMC}…"

dd if=/dev/zero of=$TARGET_MMC bs=1M count=1 || exit 2

echo -e "Partitionning $TARGET_MMC…"

sfdisk $TARGET_MMC << EOF
,$BOOT_SIZE,$BOOT_TYPE,*
,,,-
EOF

sleep 2

sync
sync

echo -e "Formating ${BOOT_PART}…"

mkfs.fat -F 32 -n"BOOT" "${BOOT_PART}" || exit 3

echo -e "Formating ${ROOT_PART}…"

mkfs.ext4 -j -L"ROOT" "${ROOT_PART}" || exit 3

sync

echo "default-on" > /sys/devices/platform/leds/leds/beaglebone\:green\:mmc0/trigger
echo "timer" > /sys/devices/platform/leds/leds/beaglebone\:green\:usr2/trigger

echo -e "Writing $IMG to $TARGET_MMC…\n"
echo "Writing boot partition to $BOOT_PART…"
dd if="./$IMG" skip=$(echo $BOOT_START) of=$BOOT_PART bs=512 count=$(echo $BOOT_SIZE) || exit 4
echo "Writing root partition to $ROOT_PART…"
dd if="./$IMG" skip=$(echo $ROOT_START) of=$ROOT_PART bs=512 || exit 4
echo "Writing $IMG to $TARGET_MMC… Done." || exit 3

sync

echo "default-on" > /sys/devices/platform/leds/leds/beaglebone\:green\:usr2/trigger
echo "default-on" > /sys/devices/platform/leds/leds/beaglebone\:green\:usr3/trigger

echo
echo "Press [Enter] to reboot. Please remove sdcard after poweroff."
read reboot
reboot
