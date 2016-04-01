DESCRIPTION = "In this document, we introduce how to support driver btusb with Realtek device support in Linux system."

LICENSE = "Proprietary"

LIC_FILES_CHKSUM = "file://readme.txt;md5=92d2d40d9a3a843c53c3b0639d810a49"
HOMEPAGE = "https://github.com/lwfinger/rtlwifi_new"

PR = "r0"

SRCREV = "41593b6437df2d56673e0c94102ecd70ae304a9a"

SRC_URI = "git://github.com/lwfinger/rtl8723au_bt.git;branch=kernel"

SRC_URI[md5sum] = "4dac9eda2c750cd85e97385a7dc27e42"
SRC_URI[sha256sum] = "017d8db361929a1e773ebeef22aa8cf7641017dbbe37c0b49933800a4a3453f1"

inherit module

S = "${WORKDIR}/git"

do_compile () {
    oe_runmake \
	CC="${CC}" \ 
	PWD="${S}" \
	KVER="${KERNEL_VERSION}" \
	KDIR="${STAGING_KERNEL_DIR}" \
	FW_DIR="${D}/lib/firmware/rtl_bt/" \
	MDL_DIR="${D}/lib/modules/${KERNEL_VERSION}" \
	DRV_DIR="${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/bluetooth"  \

}


do_install () {

    mkdir -p ${D}/lib/firmware/rtl_bt
    cp -r ${S}/*_fw.bin ${D}/lib/firmware/rtl_bt

    mkdir -p ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/bluetooth
    cp ${S}/btusb.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/bluetooth/rtlbtusb.ko

    mkdir -p ${D}${sysconfdir}/modprobe.d
    echo "# make sure that we use the rtlbtusb kernel module for the gigabyte" > ${D}${sysconfdir}/modprobe.d/rtlbtusb.conf
    echo "blacklist btusb" >> ${D}${sysconfdir}/modprobe.d/rtlbtusb.conf
    echo "install rtlbtusb" >> ${D}${sysconfdir}/modprobe.d/rtlbtusb.conf
}



# SSTATE_DUPWHITELIST += "${PKGDATA_DIR}/runtime/kernel-module-btusb.packaged"
# SSTATE_DUPWHITELIST += "${PKGDATA_DIR}/runtime/kernel-module-btusb"
# SSTATE_DUPWHITELIST += "${PKGDATA_DIR}/runtime-reverse/kernel-module-btusb"


FILES_${PN} = "/etc/modprobe.d/* \
	/lib/firmware/rtl_bt/* \
"

