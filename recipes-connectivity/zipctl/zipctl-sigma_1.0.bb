DESCRIPTION = "Zware Library Code from Sigma"
SECTION = "zwave"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://*"
S = "${WORKDIR}"

inherit gettext 

DEPENDS = "openssl"

export ZWAVE_LIB_PATH = "${S}/${PN}/zipctl"
export ZWAVE_DIR_PATH = "${S}/${PN}"


do_compile() {
	cd ${S}/${PN}/src/zwave/hcapi
	chmod +x install_openssl_lib.sh
	sh install_openssl_lib.sh
	cd ${S}/
	patch -p0 < zipctl.patch
	cd ${S}/${PN}/src/zwave/hcapi
	chmod +x build_openssl_lib.sh 
	sh build_openssl_lib.sh linux debug
	make TARGET_PLATFORM=LINUX_ZIP2 DEBUG=1
	cd ${S}/${PN}/src/zwave/hcapi/demos/
	make TARGET_PLATFORM=LINUX_ZIP2 DEBUG=1
}
do_install() {
        install -d ${D}${libdir}
        install -m 0750 ${S}/${PN}/src/zwave/hcapi/lib/libzip_api.so ${D}${libdir}
        install -m 0750 ${S}/${PN}/src/zwave/hcapi/src/libzip_ctl.so ${D}${libdir}

	install -d ${STAGING_LIBDIR}${libdir}
        install -m 0750 ${S}/${PN}/src/zwave/hcapi/lib/libzip_api.so ${STAGING_LIBDIR}${libdir}
        install -m 0750 ${S}/${PN}/src/zwave/hcapi/src/libzip_ctl.so ${STAGING_LIBDIR}${libdir}

}

# do_package_qa(){
# }

PACKAGES = "${PN}-dbg ${PN}"

FILES_${PN} += "${libdir}/libzip_*.so"


# skip packing debug info
# INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
# INSANE_SKIP_${PN} = "rdeps textrel"
INSANE_SKIP_${PN} = "ldflags"
# INSANE_SKIP_${PN}-dev = "ldflags"

