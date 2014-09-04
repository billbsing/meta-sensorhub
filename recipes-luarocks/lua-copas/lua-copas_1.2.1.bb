DESCRIPTION = "Coroutine Oriented Portable Asynchronous Services"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license.html;beginline=59;endline=106;md5=7f0e1845e47b5c4933022a6b6586187b"
HOMEPAGE = "http://www.keplerproject.org/copas"

PR = "r0"

DEPENDS = "lua"

SRC_URI = "https://github.com/keplerproject/copas/archive/v1_2_1.tar.gz;name=tarball \
	http://keplerproject.github.io/copas/license.html;name=license \
	file://lua-copas.pc \
"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"


SRC_URI[tarball.md5sum] = "f488e7730705c948ed9480c9b0e3461a"
SRC_URI[tarball.sha256sum] = "08a33c4a289bee78cef1284da9624dafa1e20c10dc40990f5e40fb62d5dc20de"

SRC_URI[license.md5sum] = "125de05f13fd39a2f9464bdb754cb8cc"
SRC_URI[license.sha256sum] = "62d51e8cc2f3e4b1cb51b826fe3b901638055ac8cb04a9c7943662d39640a3b7"

S = "${WORKDIR}/copas-1_2_1"
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
    install -m 0644 ${WORKDIR}/lua-copas.pc ${D}${libdir}/pkgconfig/
}

FILES_${PN} = "${datadir}${luadir}/copas.lua \
	${datadir}${luadir}/copas \
"

