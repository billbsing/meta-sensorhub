
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"


EFI_PROVIDER = "gummiboot"
GUMMIBOOT_TIMEOUT = "4"

MACHINE_FEATURES += "intel-ucode"


ROOTFS_POSTPROCESS_COMMAND_append_intel-quark += " install_silverline_repo; "

install_silverline_repo() {
   PACKAGE_PATH="http://silverline-support.dyndns.org/deploy/test/jethro/intel-quark/ipk"
   install -d ${IMAGE_ROOTFS}/etc/opkg

   echo "src/gz all ${PACKAGE_PATH}/all" > ${IMAGE_ROOTFS}/etc/opkg/base-feeds.conf
   echo "src/gz i586-nlp-32  ${PACKAGE_PATH}/i586-nlp-32" >> ${IMAGE_ROOTFS}/etc/opkg/base-feeds.conf
   echo "src/gz i586-nlp-32-intel-common  ${PACKAGE_PATH}/i586-nlp-32-intel-common" >> ${IMAGE_ROOTFS}/etc/opkg/base-feeds.conf
   echo "src/gz intel-quark ${PACKAGE_PATH}/intel_quark" >> ${IMAGE_ROOTFS}/etc/opkg/base-feeds.conf
}



