DESCRIPTION = "Coroutine Oriented Portable Asynchronous Services"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8929ff1dba945c44eaa1b55d66c7792c"
HOMEPAGE = "http://www.keplerproject.org/copas"

PR = "r0"
USE_PV = "${@'${PV}'.replace('.', '_')}"

DEPENDS = "lua lua-socket lua-coxpcall lua-binaryheap lua-timerwheel"

SRC_URI = "https://github.com/keplerproject/copas/archive/v${USE_PV}.tar.gz \
	file://lua-copas.pc \
"

SRC_URI[md5sum] = "4ed29c1d1dcc949c1ecdc926815c5c1c"
SRC_URI[sha256sum] = "10f094fdc79332d9bbcc6861d41688331a357d28e42ad43e07abea1c873f4da3"

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

