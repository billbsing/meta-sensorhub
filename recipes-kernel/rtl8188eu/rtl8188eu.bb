DESCRIPTION = "RealTek RTL8188EU Wireless driver"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
HOMEPAGE = "https://github.com/lwfinger/rtl8188eu"


inherit module
inherit kernel-arch

PR = "r0"
PV = "0.1"
SRCREV = "${AUTOREV}"


SRC_URI = "git://github.com/lwfinger/rtl8188eu.git \ 
	file://Makefile.patch \
"


SRC_URI[md5sum] = "b516dfe18806c42e0fd34b24e6599a6c"
SRC_URI[sha256sum] = "24e6c8b3777f2113503f85e61161cd335a5c1df3dab15c66bf302dd7236cfe39"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

S = "${WORKDIR}/git"


FWPATH = "/lib/firmware/rtlwifi"
MODULEPATH = "${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless"


EXTRA_OEMAKE += "MODDESTDIR=${MODULEPATH} SRC=${S}"

do_install_append() {
	mkdir -p ${D}${FWPATH} 
	cp -n ${S}/rtl8188eufw.bin ${D}${FWPATH}
}

FILES_${PN} = "${FWPATH}/rtl8188eufw.bin"


# Kernel module packages MUST begin with 'kernel-module-', otherwise
# multilib image generation can fail.
#
# The following line is only necessary if the recipe name does not begin
# with kernel-module-.
#
PKG_${PN} = "kernel-module-${PN}"
