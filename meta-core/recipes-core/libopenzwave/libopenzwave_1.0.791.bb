DESCRIPTION = "An open-source interface to Z-Wave networks."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://license/license.txt;md5=584c7ddacb8739db77ddcc47bd9d3b52"
HOMEPAGE = "https://code.google.com/p/open-zwave/"

PR = "r0"


SRC_URI = "http://old.openzwave.com/downloads/openzwave-${PV}.tar.gz \
	file://hid.c.patch \
	file://Makefile.patch  \
"

SRC_URI[md5sum] = "552e94b56741ea1a51bbd2adff7ba3e8"
SRC_URI[sha256sum] = "7400a097f0bc1574f61ca8fac3aac5e1c2bf9715efe193ef37890e924c0fb50e"

DEPENDS = "udev"


S = "${WORKDIR}/openzwave-${PV}"

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
        install


#    oe_runmake \
#        'INSTALL_TOP=${D}${prefix}' \
#	'INSTALL_MAN=${D}${mandir}/man1' \
#	'DESTDIR=${SYSTOOLS}' \
#	'PREFIX=${prefix}' \
#        install

}
