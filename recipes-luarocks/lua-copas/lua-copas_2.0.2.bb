DESCRIPTION = "Coroutine Oriented Portable Asynchronous Services"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license_${PN}.html;md5=7744f07e5acfc3472595fa95c9bb83f3"
HOMEPAGE = "http://www.keplerproject.org/copas"

PR = "r0"
USE_PV = "${@'${PV}'.replace('.', '_')}"

DEPENDS = "lua"

SRC_URI = "https://github.com/keplerproject/copas/archive/v${USE_PV}.tar.gz;name=tarball \
	http://keplerproject.github.io/copas/license.html;name=license;downloadfilename=license_${PN}.html \
	file://lua-copas.pc \
"

SRC_URI[tarball.md5sum] = "c07a2111ae620e87ace2d63b227b8dcc"
SRC_URI[tarball.sha256sum] = "45c7b723bc46596f153c710db43a318276aaa62bd3d2ff704d483f0fc2573c19"

SRC_URI[license.md5sum] = "7744f07e5acfc3472595fa95c9bb83f3"
SRC_URI[license.sha256sum] = "7efff457d0bd4bac36c1ae5be5ebea9192d76cc699cf0d60aa25268b08f1cfd4"

S = "${WORKDIR}/copas-${USE_PV}"
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
    install -m 0644 ${WORKDIR}/lua-copas.pc ${D}${libdir}/pkgconfig/
}

FILES_${PN} = "${datadir}${luadir}/copas.lua \
	${datadir}${luadir}/copas \
"

