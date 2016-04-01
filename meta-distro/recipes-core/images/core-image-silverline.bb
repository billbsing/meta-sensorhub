DSCRIPTION = "A fully functional image to be placed on SD card"

DISTRO_NAME = "Silverline Linux"


IMAGE_LINGUAS = ""

LICENSE = "GPLv2"

inherit core-image


IMAGE_FSTYPES = "ext4 iso hddimg live"

DISTRO_FEATURES += "bluetooth usbgadget usbhost wifi zeroconf pci 3g"

IMAGE_FEATURES += "package-management"
IMAGE_FEATURES += "ssh-server-openssh"

IMAGE_INSTALL = "packagegroup-core-boot"
IMAGE_INSTALL += "${ROOTFS_PKGMANAGE_BOOTSTRAP}"
IMAGE_INSTALL += "${CORE_IMAGE_EXTRA_INSTALL}"
IMAGE_INSTALL += "kernel-modules"
IMAGE_INSTALL += "linux-firmware"
IMAGE_INSTALL += "ethtool"
IMAGE_INSTALL += "pciutils"
IMAGE_INSTALL += "wireless-tools"
IMAGE_INSTALL += "wpa-supplicant"
IMAGE_INSTALL += "ppp"
IMAGE_INSTALL += "openssh"
IMAGE_INSTALL += "usbutils"
IMAGE_INSTALL += "util-linux"
IMAGE_INSTALL += "makedevs"
IMAGE_INSTALL += "tzdata"
IMAGE_INSTALL += "cronie"
IMAGE_INSTALL += "logrotate"
IMAGE_INSTALL += "rsync"
IMAGE_INSTALL += "avahi-daemon"
IMAGE_INTSALL += "usb-modeswitch"
IMAGE_INSTALL += "redis"
IMAGE_INSTALL += "lighttpd"
IMAGE_INSTALL += "sudo"
IMAGE_INSTALL += "sensorhub"


ROOTFS_POSTPROCESS_COMMAND_append_valleyisland-64 += " install_silverline_repo; "

install_silverline_repo() {
   PACKAGE_PATH="http://silverline-support.dyndns.org/deploy/test/fido/valleyisland-64/ipk"
   echo "src/gz all ${PACKAGE_PATH}/all" > ${IMAGE_ROOTFS}/etc/opkg/base-feeds.conf
   echo "src/gz i586  ${PACKAGE_PATH}/corei7-64" >> ${IMAGE_ROOTFS}/etc/opkg/base-feeds.conf
   echo "src/gz quark ${PACKAGE_PATH}/valleyisland_64" >> ${IMAGE_ROOTFS}/etc/opkg/base-feeds.conf
}



# IMAGE_INSTALL += " connman"
# IMAGE_INSTALL += "util-linux-mkfs"
# IMAGE_INSTALL += "e2fsprogs"
# IMAGE_INSTALL += "parted"
# IMAGE_INSTALL += " ntp-tickadj "
# IMAGE_INSTALL += " ntp"
# IMAGE_INSTALL += " ntpdate"
# IMAGE_INSTALL += " ntp-utils"
# IMAGE_INSTALL += " rsync"
# IMAGE_INSTALL += " iw"
# IMAGE_INSTALL += " avahi"
# IMAGE_INSTALL += " avahi-daemon"
# IMAGE_INSTALL += " picocom"
# IMAGE_INSTALL += " mosquitto autossh"





# NOISO = "1"
# NOHDD = "1"
# IMAGE_ROOTFS_SIZE = "307200"
# EXTRA_IMAGECMD_append_ext2 = " -N 2000"
# IMAGE_FSTYPES = "ext3 live"

# IMAGE_INSTALL_append += "linux-firmware"
# IMAGE_INSTALL_append += "strace"
# IMAGE_INSTALL_append += "linuxptp"
# IMAGE_INSTALL_append += "libstdc++"
# IMAGE_INSTALL_append += "dmidecode"
# IMAGE_INSTALL_append += "opencv nodejs"
# IMAGE_INSTALL_append += "python python-modules python-numpy python-opencv"
# IMAGE_INSTALL_append += "alsa-lib alsa-utils alsa-tools"

