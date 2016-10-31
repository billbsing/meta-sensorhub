DESCRIPTION = "Lua Web Server Library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license_${PN}.html;md5=b6c8936a4893c2ba97d590217f283c7c"
HOMEPAGE = "http://keplerproject.github.com/xavante"

PR = "r0"

DEPENDS = "lua"

SRC_URI = "https://github.com/keplerproject/xavante/archive/v${PV}.tar.gz;name=tarball;downloadfilename=xavante_${PV}.tar.gz \
	http://keplerproject.github.io/xavante/license.html;name=license;downloadfilename=license_${PN}.html \
	file://lua-xavante.pc \
"


SRC_URI[tarball.md5sum] = "7a79d284a774485b8e31a6a0359480b6"
SRC_URI[tarball.sha256sum] = "1fc185f9126f12efb2ad126bd3e66a713070a9976d94f769ee9fa43681061b1b"

SRC_URI[license.md5sum] = "b6c8936a4893c2ba97d590217f283c7c"
SRC_URI[license.sha256sum] = "3afd6afdff09de1764ee1b50cc794dfe8355a4abb61abe66a8ac9153dca3bf55"

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

