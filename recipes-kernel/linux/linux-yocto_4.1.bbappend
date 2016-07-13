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
    file://usbnet.cfg \
    file://tps65217.cfg \
    file://wifi.cfg \
\
    file://uart/0001-enable-uart4.patch \
    file://uart/0002-enable-uart5.patch \
    file://hdmi/0001-hdmi-Removed-hdmi-device.patch \
    file://gpio/0001-gpio_keys-added-armazcape-switch-pins.patch \
    file://i2c/0001-i2c-add-BBB-eeprom-to-devicetree.patch \
    file://i2c/0001-i2c-add-i2c2-bus.patch \
    file://i2c/0002-i2c-add-ArmazCape-eeprom-to-devicetree.patch \
    file://i2c/0003-i2c-add-rtc-to-devicetree.patch \
    file://i2c/0004-i2c-add-pwm-module-to-devicetree.patch \
    file://leds/0001-leds-defaut-usr3-led-to-timer-trigger.patch \
    file://spi/0001-spi1-added-spi1-pins.patch \
    file://spi/0002-added-ks8851-pins-in-devicetree.patch \
    file://adc/0001-adc-added-thermistors-pins.patch \
    file://tps65217/0001-devicetree-Add-TPS65217-charger-binding.patch \
    file://tps65217/0001-power_supply-Add-support-for-tps65217-charger.patch \
    file://tps65217/0001-tps65217-Enable-KEY_POWER-press-on-AC-loss-PWR_BUT.patch \
    file://tps65217/0001-add-tps65217-charger-to-device-tree.patch \
"
