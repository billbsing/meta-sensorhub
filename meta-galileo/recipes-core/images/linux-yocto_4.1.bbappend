
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# PACKAGE_INSTALL = "initramfs-live-boot busybox base-passwd udev"

# PACKAGE_INSTALL_machine_intel-quark += "kernel-module-usb-storage"
# PACKAGE_INSTALL_machine_intel-quark += "kernel-module-ehci-hcd kernel-module-ehci-pci kernel-module-ohci-hcd"
# PACKAGE_INSTALL_machine_intel-quark += "kernel-module-stmmac"

# EFI_PROVIDER = "grub"

# PACKAGE_INSTALL += "iwlwifi-7260"

ROOTFS_POSTPROCESS_COMMAND_append_intel-quark += " install_silverline_repo; "

install_silverline_repo() {
   PACKAGE_PATH="http://silverline-support.dyndns.org/deploy/test/jethro/intel-quark/ipk"
   install -d ${IMAGE_ROOTFS}/etc/opkg

   echo "src/gz all ${PACKAGE_PATH}/all" > ${IMAGE_ROOTFS}/etc/opkg/base-feeds.conf
   echo "src/gz i586-nlp-32  ${PACKAGE_PATH}/i586-nlp-32" >> ${IMAGE_ROOTFS}/etc/opkg/base-feeds.conf
   echo "src/gz i586-nlp-32-intel-common  ${PACKAGE_PATH}/i586-nlp-32-intel-common" >> ${IMAGE_ROOTFS}/etc/opkg/base-feeds.conf
   echo "src/gz intel-quark ${PACKAGE_PATH}/intel-quark" >> ${IMAGE_ROOTFS}/etc/opkg/base-feeds.conf
}



