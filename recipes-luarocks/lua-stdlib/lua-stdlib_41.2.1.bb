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

SRC_URI[md5sum] = "81e5c4890dc9c01df8a97c4a41231ebd"
SRC_URI[sha256sum] = "8879457b1505e4c949649186c29ba13b30ea0eceeba77903f282a8341cd7255a"

S = "${WORKDIR}/lua-stdlib-release-v${PV}"

luadir = "/lua/5.3"


# EXTRA_OECONF = "--lua-version=5.3 \
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

