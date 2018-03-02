DESCRIPTION = "Network support for the Lua language"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ab6706baf6d39a6b0fa2613a3b0831e7"
HOMEPAGE = "http://www.impa.br/~diego/software/luasocket"

PR = "rc1"

DEPENDS = "lua"

SRC_URI = "https://github.com/diegonehab/luasocket/archive/v${PV}-${PR}.tar.gz \
	file://lua-socket.pc \
	file://makefile.patch \
"


SRC_URI[md5sum] = "08bd2f265b244eb4bf5c2c36bf89b759"
SRC_URI[sha256sum] = "8b67d9b5b545e1b694753dab7bd6cdbc24c290f2b21ba1e14c77b32817ea1249"

S = "${WORKDIR}/luasocket-${PV}-${PR}"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.3"


MAKE_FLAGS = "'prefix=${D}' \
'LUAPREFIX_linux=${prefix}' \
'CDIR_linux=${libdir}${luadir}' \
'LDIR_linux=${datadir}${luadir}' \
'LUAV=5.3' \
'LUAINC_linux_base=${SYSROOTS}${includedir}' \
'LUAINC_linux=${SYSROOTS}${includedir}' \
'MYCFLAGS=-arch ${MACHINE}' \
'GCC_linux=${CC}' \
"

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

