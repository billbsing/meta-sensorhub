DESCRIPTION = "An open-source interface to Z-Wave networks."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://license/license.txt;md5=584c7ddacb8739db77ddcc47bd9d3b52"
HOMEPAGE = "https://code.google.com/p/open-zwave/"

PR = "r0"


SRC_URI = "https://github.com/OpenZWave/open-zwave/archive/v${PV}.tar.gz \
"

#	file://hid.c.patch 
#	file://Makefile.patch  

SRC_URI[md5sum] = "f4d4b5ee679702ac94ef987128ae6a6e"
SRC_URI[sha256sum] = "ca122365f6237dc245be0129eb460e78126976b9c7bd06487795d3acfc9f2a5f"

DEPENDS = "udev"
 
inherit pkgconfig


S = "${WORKDIR}/open-zwave-${PV}"

SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"

EXTRA_OEMAKE= "'SYS_INCLUDE=-I ${SYSROOTS}${includedir}' \
'PREFIX=${D}${prefix}' \
'CC=${CC}'  \
'CXX=${CXX}' \
'LD=${CXX}' \
'MACHINE=${MACHINE}' \
"

do_compile () {
    oe_runmake 
}


do_install () {
    oe_runmake \
        'INSTALL_TOP=${D}${prefix}' \
	'INSTALL_MAN=${D}${mandir}/man1' \
	'DESTDIR=${D}' \
	'PREFIX=${prefix}' \
	'pkgconfigdir=${libdir}/pkgconfig' \
	'sysconfdir=/etc/openzwave/' \
        install

}


