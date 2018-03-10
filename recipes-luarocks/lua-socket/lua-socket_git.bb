DESCRIPTION = "Network support for the Lua language"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ab6706baf6d39a6b0fa2613a3b0831e7"
HOMEPAGE = "http://www.impa.br/~diego/software/luasocket"

PR = "1"
SRCREV = "${AUTOREV}"

DEPENDS = "lua"

# SRC_URI = https://github.com/diegonehab/luasocket/archive/v${PV}-${PR}.tar.gz

SRC_URI = "git://github.com/diegonehab/luasocket.git \
 	file://makefile.patch \
	file://lua-socket.pc \
"


SRC_URI[md5sum] = "c487ad5e9f0da6d103f63dcfe2728f38"
SRC_URI[sha256sum] = "502d96f3e1b1fd55ecf1ace37e76aca23d2b38d9cf638ae12cf3114a55580479"


S = "${WORKDIR}/git"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.3"


MAKE_FLAGS = "'prefix=${D}' \
'LUAPREFIX_linux=${prefix}' \
'CDIR_linux=${libdir}${luadir}' \
'LDIR_linux=${datadir}${luadir}' \
'LUAV=5.3' \
'LUAINC_linux_base=${SYSROOTS}${includedir}' \
'LUAINC_linux=${SYSROOTS}${includedir}' \
'GCC_linux=${CC}' \
"

# 'MYCFLAGS=-arch ${MACHINE}' 
do_compile () {
    oe_runmake clean
    oe_runmake ${MAKE_FLAGS} linux
}


do_install () {
    oe_runmake ${MAKE_FLAGS} install
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-socket.pc ${D}${libdir}/pkgconfig/
}

INSANE_SKIP_${PN} = "ldflags"
INSANE_SKIP_${PN}-dev = "ldflags"

FILES_${PN} = "${libdir}${luadir}/mime/core.so \
	${libdir}${luadir}/socket/core.so \
	${datadir}${luadir}/socket.lua \
	${datadir}${luadir}/mime.lua \
	${datadir}${luadir}/ltn12.lua \
	${datadir}${luadir}/socket/*.lua \
"

