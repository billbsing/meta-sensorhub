
# KERNEL_MODULE_AUTOLOAD_append_iot-devkit = " iwlwifi"
KERNEL_MODULE_AUTOLOAD += "btusb iwlwifi"

# swap g_serial for g_acm_ms
# KERNEL_MODULE_AUTOLOAD_append += " pch_udc g_acm_ms"

# KERNEL_MODULE_PROBECONF_append += " g_acm_ms"
# module_conf_g_acm_ms_iot-devkit = "options g_acm_ms file=/dev/mmcblk0p1 removable=1 idVendor=0x8086 idProduct=0xDEAD"

# PPP module autoload
KERNEL_MODULE_AUTOLOAD += "pppox"
KERNEL_MODULE_AUTOLOAD += "pppoe"

# find defconfig path
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# SRC_URI += "file://enable_systemd.cfg"
SRC_URI += "file://enable_mmc.cfg"
SRC_URI += "file://fuse.cfg"
SRC_URI += "file://bridge.cfg"
SRC_URI += "file://netfilter-small-3.8.cfg"
SRC_URI += "file://nfacct.cfg"
SRC_URI += "file://ipv6.cfg"
SRC_URI += "file://nfc.cfg"
SRC_URI += "file://mac80211.cfg"
SRC_URI += "file://rfkill.cfg"
SRC_URI += "file://l2tp.cfg"
SRC_URI += "file://tun-device.cfg"
SRC_URI += "file://usb-serial.cfg"
SRC_URI += "file://nokia-phonet.cfg"
SRC_URI += "file://bluetooth.cfg"
SRC_URI += "file://wlan-intel.cfg"
SRC_URI += "file://wlan-ti.cfg"
SRC_URI += "file://wlan-marwel.cfg"
SRC_URI += "file://wlan-zydas.cfg"
SRC_URI += "file://wlan-broadcom.cfg"
SRC_URI += "file://wlan-realtek.cfg"
SRC_URI += "file://wlan-ralink.cfg"
SRC_URI += "file://wlan-atheros.cfg"
SRC_URI += "file://g_acm_ms.cfg"
SRC_URI += "file://netfilter_redirect.cfg"
SRC_URI += "file://ppp.cfg"
SRC_URI += "file://ftdi-sio.cfg"
SRC_URI += "file://usb-serial.cfg"
SRC_URI += "file://usb-ohci.cfg"
SRC_URI += "file://serial.cfg"
