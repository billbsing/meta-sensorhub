DESCRIPTION = "General Lua Libraries"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=97c3f0908c9d2f747338e51da4f3df0a"
HOMEPAGE = "https://github.com/lua-stdlib/lua-stdlib"

PR = "1"

DEPENDS = "lua"

SRC_URI = "https://github.com/lua-stdlib/lua-stdlib/archive/release-v${PV}.tar.gz \
	file://lua-stdlib.pc \
"

inherit autotools

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

SRC_URI[md5sum] = "5d99d87b95ca64f1e079289fe9e486ff"
SRC_URI[sha256sum] = "c9d9d11dcd2e3e956e697f5455b5f62baadf233d8726d80514d0432273cc27e9"

S = "${WORKDIR}/lua-stdlib-release-v${PV}"

luadir = "/lua/5.2"


# EXTRA_OECONF = "--lua-version=5.2 \
# --luadir=${libdir}${luadir} \
# LUA_INCLUDE=${SYSROOTS}${includepath} \
# "
# LUA_PATH=${datadir}${luadir} 
# LUA_EXEC_DIR=${libdir}${luadir} 
# LUA_INCLUDE=${SYSROOTS}${includepath} 
# "

do_configure() {
}

do_compile() {
}

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

