# Copyright (C) 2015 Benoit Rapidel <benoit.rapidel@exmachina.fr>
# Released under the MIT license (see COPYING.MIT for the terms)

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:${THISDIR}/files:"

SRC_URI += " \
    file://name.cfg \
    file://i2c.cfg \
    file://adc.cfg \
    file://rtc.cfg \
    file://serial.cfg \
    file://spi.cfg \
    file://tty.cfg \
    file://pwm.cfg \
    file://leds.cfg \
\
    file://uart/0001-enable-uart4.patch \
    file://uart/0002-enable-uart5.patch \
    file://hdmi/0001-hdmi-Removed-hdmi-device.patch \
    file://gpio/0001-gpio_keys-added-armazcape-switch-pins.patch \
    file://i2c/0001-i2c-added-i2c2-bus-and-devices.patch \
    file://leds/0001-leds-defaut-usr3-led-to-timer-trigger.patch \
    file://spi/0001-spi1-added-spi1-pins-and-enc424j6-device.patch \
    file://adc/0001-adc-added-thermistors-pins.patch \
    file://tps65217/0008-tps65217-Enable-KEY_POWER-press-on-AC-loss-PWR_BUT.patch \
"
