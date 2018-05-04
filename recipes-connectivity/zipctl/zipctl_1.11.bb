DESCRIPTION = "Zware Library Code"
SECTION = "zwave"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI = "\
	file://${PN} \
	file://00001_openssl_config.patch \
	file://00002_openssl_config_remove_release.patch;apply=0  \
	file://00003_demo_rename_ssl_lib.patch \
	file://00004_zip_library_makefile.patch \
"
S = "${WORKDIR}"

inherit gettext 

DEPENDS = "openssl"

export ZWAVE_LIB_PATH = "${S}/${PN}/zipctl"
export ZWAVE_DIR_PATH = "${S}/${PN}"
export SHARED_LDFLAGS="${LDFLAGS}"

do_compile() {
	cd ${S}/${PN}/src/zwave/hcapi
	bash install_openssl_lib.sh

	cd ${S}
	patch -p0 < 00002_openssl_config_remove_release.patch

	cd ${S}/${PN}/src/zwave/hcapi
	
	bash build_openssl_lib.sh linux release shared

	cd ${S}/${PN}/src/zwave/openssl
	cp libssl.so.1.0.2 install/lib/libzip_ssl.so.${PV}
	cp libcrypto.so.1.0.2 install/lib/libzip_crypto.so.${PV}

	cp install/lib/libssl.a install/lib/libzip_ssl.a
	cp install/lib/libcrypto.a install/lib/libzip_crypto.a

	cd ${S}/${PN}/src/zwave/hcapi
	make TARGET_PLATFORM=LINUX_ZIP2 DEBUG=0 SHARED=1
	cp lib/libzip_api.so ./libzip_api.so.${PV} 
	cp src/libzip_ctl.so ./libzip_ctl.so.${PV}
	make TARGET_PLATFORM=LINUX_ZIP2 DEBUG=0 SHARED=0
	cp lib/libzip_api.a .
	cp src/libzip_ctl.a .
	cd ${S}/${PN}/src/zwave/hcapi/demos/
	make TARGET_PLATFORM=LINUX_ZIP2 DEBUG=0 SHARED=0 
}
do_install() {
        install -d ${D}${libdir}
        install -m 0750 ${S}/${PN}/src/zwave/hcapi/libzip_api.so.${PV} ${D}${libdir}
        install -m 0750 ${S}/${PN}/src/zwave/hcapi/libzip_ctl.so.${PV} ${D}${libdir}

	cd ${D}${libdir}
	ln -s libzip_api.so.${PV} libzip_api.so
	ln -s libzip_ctl.so.${PV} libzip_ctl.so

        install -m 0750 ${S}/${PN}/src/zwave/hcapi/libzip_api.a ${D}${libdir}
        install -m 0750 ${S}/${PN}/src/zwave/hcapi/libzip_ctl.a ${D}${libdir}

        install -m 0750 ${S}/${PN}/src/zwave/openssl/install/lib/libzip_ssl.so.${PV} ${D}${libdir}
        install -m 0750 ${S}/${PN}/src/zwave/openssl/install/lib/libzip_crypto.so.${PV} ${D}${libdir}

	cd ${D}${libdir}
	ln -s libssl_zipctl.so.${PV} libzip_ssl.so
	ln -s libcrypto_zipctl.so.${PV} libzip_crypto.so

        install -m 0750 ${S}/${PN}/src/zwave/openssl/install/lib/libzip_ssl.a ${D}${libdir}
        install -m 0750 ${S}/${PN}/src/zwave/openssl/install/lib/libzip_crypto.a ${D}${libdir}

	install -d ${D}${includedir}/zipctl
	cp -r ${S}/${PN}/src/zwave/hcapi/include/* ${D}${includedir}/zipctl

	install -d ${D}${bindir}/zipctl
        install -m 750 ${S}/${PN}/src/zwave/hcapi/demos/add_node/add_node ${D}${bindir}/zipctl/add_node
        install -m 750 ${S}/${PN}/src/zwave/hcapi/demos/basic/basic ${D}${bindir}/zipctl/basic
        install -m 750 ${S}/${PN}/src/zwave/hcapi/demos/bin_sensor/bin_sensor ${D}${bindir}/zipctl/bin_sensor
        install -m 750 ${S}/${PN}/src/zwave/hcapi/demos/bin_switch/bin_switch ${D}${bindir}/zipctl/bin_switch
        install -m 750 ${S}/${PN}/src/zwave/hcapi/demos/gw_discovery/gw_discovery ${D}${bindir}/zipctl/gw_discovery
        install -m 750 ${S}/${PN}/src/zwave/hcapi/demos/nw_reset/nw_reset ${D}${bindir}/zipctl/nw_reset
        install -m 750 ${S}/${PN}/src/zwave/hcapi/demos/rm_node/rm_node ${D}${bindir}/zipctl/rm_node

}

# do_package_qa() {
# }

# PACKAGES = "${PN}-dbg ${PN}"
PACKAGES += "${PN}-demo"

FILES_${PN} = ""

FILES_${PN} += "\
	${libdir} \
	${includedir}/zipctl \
"

FILES_${PN}-dev += "\
	${libdir}/*.so \
	${includedir}/zipctl \
"
FILES_${PN}-staticdev = "\
	${libdir}/*.a \
	${includedir}/zipctl \
"
FILES_${PN}-demo = "\
	${bindir}/zipctl/add_node \
	${bindir}/zipctl/basic \
	${bindir}/zipctl/bin_sensor \
	${bindir}/zipctl/bin_switch \
	${bindir}/zipctl/gw_discovery \
	${bindir}/zipctl/nw_reset \
	${bindir}/zipctl/rm_node \
"

# ${includedir}/zipctl 
# skip packing debug info
# INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
# INSANE_SKIP_${PN} = "rdeps textrel"
# INSANE_SKIP_${PN} = "ldflags"
# INSANE_SKIP_${PN}-dev = "ldflags"

