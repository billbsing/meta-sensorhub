DESCRIPTION = "General Lua Libraries"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=8f0d64baadc25360d876fcdd981af76c"
HOMEPAGE = "https://github.com/lua-stdlib/lua-stdlib"

PR = "40"

DEPENDS = "lua"

SRC_URI = "https://github.com/lua-stdlib/lua-stdlib/archive/v${PR}.tar.gz \
	file://lua-stdlib.pc \
"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

SRC_URI[md5sum] = "aea62d12fb9aaa6e684e6ff47ac65282"
SRC_URI[sha256sum] = "d9c0b16dc8789a49ce56bc77127cad3e59a63352e924278361fa072ba754d22d"

luadir = "/lua/5.2"

do_install () {


    install -d ${D}${datadir}${luadir}/std
    install -d ${D}${datadir}${luadir}/std/debug_init

    install -m 0644 ${S}/lib/std.lua.in ${D}${datadir}${luadir}/std.lua
    install -m 0644 ${S}/lib/std/*.lua ${D}${datadir}${luadir}/std

    install -m 0644 ${S}/lib/std/debug_init/* ${D}${datadir}${luadir}/std/debug_init

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-stdlib.pc ${D}${libdir}/pkgconfig/

}

FILES_${PN} = "${datadir}${luadir}/std \
	${datadir}${luadir}/std.lua \
	${datadir}${luadir}/std/* \
"


RDEPEND="lua"

