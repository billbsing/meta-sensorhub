SUMMARY = "Python interface to Bluetooth LE on Linux"
HOMEPAGE = "https://github.com/IanHarvey/bluepy"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=59e0d45ea684dda215889aa1b5acd001"
SRCNAME="bluepy"

SRC_URI = "https://github.com/IanHarvey/bluepy/archive/v/1.0.5.tar.gz"
SRC_URI[md5sum] = "6ade53350de0424ffde82ab792354893"
SRC_URI[sha256sum] = "96bea127204cbd2531362fc3999b2ef54096e0e70e9abc012bdccbd01749300e"
S = "${WORKDIR}/bluepy-v-${PV}"

DEPENDS_${PN} = "glib-2.0"

inherit setuptools autotools

do_compile() {
	cd ${S}
	rm -rf ${S}/build
 	${STAGING_BINDIR_NATIVE}/python-native/python setup.py build
}


do_install() {
 	cd ${S}
 	${STAGING_BINDIR_NATIVE}/python-native/python setup.py install
}
