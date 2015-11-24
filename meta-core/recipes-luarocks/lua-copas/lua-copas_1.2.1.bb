DESCRIPTION = "Coroutine Oriented Portable Asynchronous Services"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license_${PN}.html;md5=7fc988d88deee16d7298c35f2b95429b"
HOMEPAGE = "http://www.keplerproject.org/copas"

PR = "r0"

DEPENDS = "lua"

SRC_URI = "https://github.com/keplerproject/copas/archive/v1_2_1.tar.gz;name=tarball \
	http://keplerproject.github.io/copas/license.html;name=license;downloadfilename=license_${PN}.html \
	file://lua-copas.pc \
"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"


SRC_URI[tarball.md5sum] = "f488e7730705c948ed9480c9b0e3461a"
SRC_URI[tarball.sha256sum] = "08a33c4a289bee78cef1284da9624dafa1e20c10dc40990f5e40fb62d5dc20de"

SRC_URI[license.md5sum] = "7fc988d88deee16d7298c35f2b95429b"
SRC_URI[license.sha256sum] = "68cdf2bf8d41686c4579a12ec327fe890fbda2090f1d949cd06950fd8aca5cd1"

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

