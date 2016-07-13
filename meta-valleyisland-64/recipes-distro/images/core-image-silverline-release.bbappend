


# IMAGE_INSTALL += "rtlwifi"
# IMAGE_INSTALL += "rtlbtusb"
IMAGE_INSTALL += "usb-modeswitch"

EFI_PROVIDER = "gummiboot"
GUMMIBOOT_TIMEOUT = "4"


ROOTFS_POSTPROCESS_COMMAND_append_valleyisland-64 += " install_silverline_repo; "

install_silverline_repo() {
   PACKAGE_PATH="http://silverline-support.dyndns.org/deploy/release/jethro/valleyisland-64/ipk"
   install -d ${IMAGE_ROOTFS}/etc/opkg

   echo "src/gz all ${PACKAGE_PATH}/all" > ${IMAGE_ROOTFS}/etc/opkg/base-feeds.conf
   echo "src/gz corei7-64  ${PACKAGE_PATH}/corei7-64" >> ${IMAGE_ROOTFS}/etc/opkg/base-feeds.conf
   echo "src/gz corei7-64-intel-common  ${PACKAGE_PATH}/corei7-64-intel-common" >> ${IMAGE_ROOTFS}/etc/opkg/base-feeds.conf
   echo "src/gz valleyisland-64 ${PACKAGE_PATH}/valleyisland_64" >> ${IMAGE_ROOTFS}/etc/opkg/base-feeds.conf
}



