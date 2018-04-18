DESCRIPTION = "Safe templates for Lua"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/doc/cosmo.md;beginline=439;md5=451281d9fca91244e983082ebbd6c808"
HOMEPAGE = "http://cosmo.luaforge.net"

PR = "r0"

DEPENDS = "lua"

SRC_URI = "https://github.com/mascarenhas/cosmo/archive/v${PV}.tar.gz;name=tarball \
	file://lua-cosmo.pc \
"

SRC_URI[tarball.md5sum] = "36288f23da41db2fa78699d448a3df2b"
SRC_URI[tarball.sha256sum] = "86d17aea5080a90671d965cffeb9b104c19e0e1ea55c08687c0924c4512b52b1"

SRC_URI[license.md5sum] = "72e037b382bf352dfbff16741bfe6631"
SRC_URI[license.sha256sum] = "104297f4306687a912fb0ceab755261abfb3aea9677c238b94f6e1c3fba1edd4"

S = "${WORKDIR}/cosmo-${PV}"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.3"

MAKE_FLAGS = "'PREFIX=${D}${prefix}' \
'LUA_LIBDIR=${D}${libdir}${luadir}' \
'LUA_DIR=${D}${datadir}${luadir}' \
'LUA_VERSION_NUM=503' \
"

do_compile () {
    oe_runmake ${MAKE_FLAGS}
}

do_install () {
    oe_runmake ${MAKE_FLAGS} install
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-cosmo.pc ${D}${libdir}/pkgconfig/
}

FILES_${PN} = "${datadir}${luadir}/cosmo.lua \
	${datadir}${luadir}/cosmo \
"

