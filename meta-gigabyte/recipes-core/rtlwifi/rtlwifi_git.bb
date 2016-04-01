


DESCRIPTION = "A repo for the newest Realtek rtlwifi codes.	\
This code will build on any kernel 3.0 and newer. It includes the following drivers: \
rtl8192ce, rtl8192se, rtl8192de, rtl8188ee, rtl8192ee, rtl8723ae, rtl8723be, and rtl8821ae."


LICENSE = "Proprietary"

LIC_FILES_CHKSUM = "file://firmware/rtlwifi/Realtek-Firmware-License.txt;md5=9590c633f1c19a55a2b128dfb1098664"
HOMEPAGE = "https://github.com/lwfinger/rtlwifi_new"

PR = "r0"
SRCREV = "b0b00f0f556ab9ab3b801736d1e71dd5f51ed859"


SRC_URI = "https://github.com/lwfinger/rtlwifi_new/archive/troy.zip \
	file://Makefile.patch  \
"

SRC_URI[md5sum] = "8ecbd40cd658416b279dfd3c77027dd3"
SRC_URI[sha256sum] = "0216800e685b78a40da701643e00a9ca9b91bda3b687c72daff3a9f8746049c7"

inherit pkgconfig module


S = "${WORKDIR}/rtlwifi_new-troy"


do_compile () {
    oe_runmake \
	CC="${CC}" \ 
	PWD="${S}" \
	KVER="${KERNEL_VERSION}" \
	KSRC="${STAGING_KERNEL_DIR}" \
	FIRMWAREDIR="${D}/lib/firmware" \
	MODDESTDIR="${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless/rtlwifi"  \

}


do_install () {
    mkdir -p ${D}/lib/firmware/rtlwifi 
    mkdir -p ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless/rtlwifi
    oe_runmake \
	CC="${CC}" \ 
	PWD="${S}" \
	KVER="${KERNEL_VERSION}" \
	KSRC="${STAGING_KERNEL_DIR}" \
	FIRMWAREDIR="${D}/lib/firmware" \
	MODDESTDIR="${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless/rtlwifi"  \
        install
}



FILES_${PN} = "/lib/firmware/rtlwifi/*"

# BBCLASSEXTEND = "native"

