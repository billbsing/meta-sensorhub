DSCRIPTION = "A fully functional image to be placed on SD card"

IMAGE_LINGUAS = " "

LICENSE = "GPLv2"

inherit core-image

IMAGE_FEATURES += "package-management"
IMAGE_FEATURES += "ssh-server-openssh"

IMAGE_INSTALL = "packagegroup-core-boot"
IMAGE_INSTALL += "${ROOTFS_PKGMANAGE_BOOTSTRAP}"
IMAGE_INSTALL += "${CORE_IMAGE_EXTRA_INSTALL}"
IMAGE_INSTALL += "kernel-modules"
IMAGE_INSTALL += "ethtool"
IMAGE_INSTALL += "pciutils"
IMAGE_INSTALL += "wireless-tools"
IMAGE_INSTALL += "wpa-supplicant"
IMAGE_INTSALL += "bluez4"
IMAGE_INSTALL += "ppp"
IMAGE_INSTALL += "openssh"
IMAGE_INSTALL += "util-linux-mkfs"
IMAGE_INSTALL += "usbutils"
IMAGE_INSTALL += "util-linux"
IMAGE_INSTALL += "makedevs"
IMAGE_INSTALL += "e2fsprogs"

IMAGE_INSTALL += "sensorhub"


DISTRO_FEATURES += "bluetooth usbgadget usbhost wifi zeroconf pci 3g"

# NOISO = "1"
# NOHDD = "1"
# IMAGE_ROOTFS_SIZE = "307200"
# EXTRA_IMAGECMD_append_ext2 = " -N 2000"
# IMAGE_FSTYPES = "ext3 live"

# IMAGE_INSTALL += "linux-firmware"
# IMAGE_INSTALL += "strace"
# IMAGE_INSTALL += "linuxptp"
# IMAGE_INSTALL += "libstdc++"
# IMAGE_INSTALL += "dmidecode"
# IMAGE_INSTALL += "opencv nodejs"
# IMAGE_INSTALL += "python python-modules python-numpy python-opencv"
# IMAGE_INSTALL += "alsa-lib alsa-utils alsa-tools"

