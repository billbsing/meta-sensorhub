DESCRIPTION = "ZIP Gateway server"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PR = "r1"

SRC_URI = "  \
	file://00001_lsub_lib.patch \
	file://00002_pkgconfig_paths.patch \
	file://main.conf \
	file://udhcpd.conf \
	file://zipgateway \
	file://zipgateway.cfg \
	file://zipgateway.service \
	file://zipgateway.init \
	file://zipgateway.tun \
"

#	file://command_class.cfg 

S="${WORKDIR}"

# skip debug
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} = "staticdev file-rdeps"
CMAKE_OPTIONS=" \
	-DSYSROOT_PATH=${STAGING_DIR_TARGET} \
	-DCMAKE_INSTALL_PREFIX=/ \
	-DINSTALL_LOCALSTATEDIR=/var \
	-DINSTALL_SYSCONFDIR=/etc \
"


do_compile() {
        mkdir -p ${S}/${PN}/usr/local/build
	cd ${S}/${PN}/usr/local/build
	cmake ${CMAKE_OPTIONS} ..
	${MAKE}
}
do_install() {
	install -d ${D}${bindir}
	install -d ${D}${base_libdir}/systemd/system/
	install -d ${D}${localstatedir}/lib/zipgateway
	mkdir -p ${D}${sysconfdir}/connman
	cp ${S}/main.conf ${D}/${sysconfdir}/connman/
	install -d ${D}${sysconfdir}/zipgateway
	install -m 0755 udhcpd.conf ${D}${sysconfdir}/zipgateway/udhcpd.conf
	touch ${D}${sysconfdir}/zipgateway/eeprom.dat
	chmod 755 ${D}${sysconfdir}/zipgateway/eeprom.dat
	install -m 0755 ${S}/${PN}/usr/local/build/zipgateway ${D}${bindir}/zipgatewayd
	install -m 0755 zipgateway.tun ${D}${sysconfdir}/zipgateway
	install -m 0644 ${S}/${PN}/usr/local/WRTpackage/files/*.pem ${D}${sysconfdir}/zipgateway
	install -m 0644 zipgateway.cfg ${D}${sysconfdir}/zipgateway
#	install -m 0644 command_class.cfg ${D}${sysconfdir}/zipgateway

	install -d ${D}/${systemd_unitdir}/system
	install -m 0644 ${S}/zipgateway.service ${D}${systemd_unitdir}/system/

	install -d ${D}${sysconfdir}/init.d/
	install -m 0755 ${S}/zipgateway.init ${D}${sysconfdir}/init.d/zipgateway
	# install -m 0755 ${S}/zipgateway.sh ${D}${bindir}/zipgateway
}



inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "zipgateway.service"

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_DEFAULT_TARGET="multi-user.target"



DEPENDS_${PN} = "libusb1 libcrypto libssl openssl"
RDEPENDS_${PN} = "libusb1 libcrypto libssl openssl"

#By default ${SHDAP_TARG_DIR} is packaged 
#Package remaining dirs

FILES_${PN} +="${base_libdir}/systemd/system"
FILES_${PN} += "${sysconfdir}/zipgateway ${sysconfdir}/connman/ "

