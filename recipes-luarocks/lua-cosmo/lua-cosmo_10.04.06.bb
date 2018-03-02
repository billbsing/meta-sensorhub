DESCRIPTION = "Safe templates for Lua"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license_${PN}.html;md5=72e037b382bf352dfbff16741bfe6631"
HOMEPAGE = "http://cosmo.luaforge.net"

PR = "r0"

DEPENDS = "lua"

SRC_URI = "https://github.com/mascarenhas/cosmo/archive/v${PV}.tar.gz;name=tarball \
	http://cosmo.luaforge.net;name=license;downloadfilename=license.html;downloadfilename=license_${PN}.html \
	file://lua-cosmo.pc \
"

SRC_URI[tarball.md5sum] = "b8ee7562aeea1df2876c2cc9938025ca"
SRC_URI[tarball.sha256sum] = "e55f43331f87c693e71bcaa9bd3e2e5002a8b0228908381a49c1f56d22746e0a"

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

