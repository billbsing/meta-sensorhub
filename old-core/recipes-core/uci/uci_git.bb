DESCRIPTION = "OpenWrt Unified Configuration Interface"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://file.c;beginline=2;endline=12;md5=4378b08263f37eb891e5c4d20cb3e6f7"
HOMEPAGE = "https://wiki.openwrt.org/doc/uci"

SRCREV = "${AUTOREV}"

DEPENDS = "libubox"

PR = "r0"

SRC_URI = "git://git.openwrt.org/project/uci.git  \
"

SRC_URI[md5sum] = "26520eea934f296be0783dabe7fcfd28"
SRC_URI[sha256sum] = "6fcaba6a409a46bdf832086736bb8f09d245ebce11027f41d39588a95dc7fd1d"

inherit cmake


S = "${WORKDIR}/git"

SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"

luadir = "/lua/5.2"

# EXTRA_OEMAKE = "'PREFIX=${D}${prefix}' 
# 'LUA_LIBDIR=${D}${libdir}${luadir}' 
# 'LUA_DIR=${D}${datadir}${luadir}' 
# 'LUA_INC=${SYSROOTS}${includedir}' 
# 'LUA_VERSION_NUM=502' 



EXTRA_OECMAKE="  \
    -DLUAPATH=${libdir}${luadir}  \
    -DLUA_VESION_NUM=502 \
"

do_install_append() {
#     rm ${D}${libdir}${luadir}/.debug/uci.so
}


RDEPENDS_${PN} = "libubox"

FILES_${PN}-dbg += " \
    ${libdir}${luadir}/.debug/uci.so \
"

FILES_${PN}-dev = " \
     ${includedir}/*.h   \
"


FILES_${PN} = " \
    ${bindir}/uci        \
    ${libdir}/libuci.so  \
    ${libdir}${luadir}/uci.so \
"
