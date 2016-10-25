DESCRIPTION = "C utility functions for OpenWrt"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://utils.c;beginline=4;endline=16;md5=9866fe64048d1f3c6b54bbd5e58109bd"
HOMEPAGE = "https://wiki.openwrt.org/doc/libubox"

SRCREV = "${AUTOREV}"

inherit cmake

PR = "r0"

SRC_URI = "git://git.openwrt.org/project/libubox.git  \
"

SRC_URI[md5sum] = "26520eea934f296be0783dabe7fcfd28"
SRC_URI[sha256sum] = "6fcaba6a409a46bdf832086736bb8f09d245ebce11027f41d39588a95dc7fd1d"

S = "${WORKDIR}/git"

SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"

luadir = "/lua/5.2"


EXTRA_OECMAKE=" -DLUAPATH=${D}${libdir}${luadir} \
	-DBUILD_EXAMPLES:BOOL=NO \
	-DLUAPATH=${libdir}${luadir}  \
	-DBUILD_LUA:BOOL=NO \
"


FILES_${PN}-dev = " \
     ${includedir}/libubox/*.h   \
"


FILES_${PN} = " \
    ${libdir}/libubox.so  \
"


