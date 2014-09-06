DESCRIPTION = "Lua Web Server Library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license.html;md5=125de05f13fd39a2f9464bdb754cb8cc"
HOMEPAGE = "http://keplerproject.github.com/xavante"

PR = "r0"

DEPENDS = "lua"

SRC_URI = "https://github.com/keplerproject/xavante/archive/v${PV}.tar.gz;name=tarball \
	http://keplerproject.github.io/xavante/license.html;name=license \
	file://lua-xavante.pc \
"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"


SRC_URI[tarball.md5sum] = "7a79d284a774485b8e31a6a0359480b6"
SRC_URI[tarball.sha256sum] = "1fc185f9126f12efb2ad126bd3e66a713070a9976d94f769ee9fa43681061b1b"

SRC_URI[license.md5sum] = "125de05f13fd39a2f9464bdb754cb8cc"
SRC_URI[license.sha256sum] = "62d51e8cc2f3e4b1cb51b826fe3b901638055ac8cb04a9c7943662d39640a3b7"

S = "${WORKDIR}/xavante-${PV}"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.2"

MAKE_FLAGS = "'PREFIX=${D}${prefix}' \
'LUA_LIBDIR=${D}${libdir}${luadir}' \
'LUA_DIR=${D}${datadir}${luadir}' \
'LUA_VERSION_NUM=502' \
"


do_compile () {
    oe_runmake ${MAKE_FLAGS}
}


do_install () {
    
    oe_runmake ${MAKE_FLAGS} install
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-xavante.pc ${D}${libdir}/pkgconfig/
}

FILES_${PN} = "${datadir}${luadir}/xavante.lua \
	${datadir}${luadir}/sajax.lua \
	${datadir}${luadir}/xavante/*.lua \
"

