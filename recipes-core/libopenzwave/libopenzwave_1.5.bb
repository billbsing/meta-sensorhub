DESCRIPTION = "An open-source interface to Z-Wave networks."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://license/license.txt;md5=584c7ddacb8739db77ddcc47bd9d3b52"
HOMEPAGE = "https://code.google.com/p/open-zwave/"

PR = "r0"


SRC_URI = "https://github.com/OpenZWave/open-zwave/archive/V${PV}.tar.gz \
	file://build_flag.patch \
	file://build_flag_examples.patch \
"

#	file://hid.c.patch 
#	file://Makefile.patch  

SRC_URI[md5sum] = "02182d274ea5127cf2b48399e0b751ed"
SRC_URI[sha256sum] = "abfb4c7e7728e86ba374c2e0ef7de912594eb24a8c44935457352df844530238"

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


