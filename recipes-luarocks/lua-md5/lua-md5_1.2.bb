DESCRIPTION = "Basic cryptographic library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README;md5=095f673e0ae6e1364d882d1bdb636df5"
HOMEPAGE = "http://www.keplerproject.org/md5/"

PR = "r1"

DEPENDS = "lua"

SRC_URI = "https://github.com/keplerproject/md5/archive/v${PV}.tar.gz \
	file://lua-md5.pc \
	file://config.patch \
"

SRC_URI[md5sum] = "c166f8a983401802a86655a8c733441e"
SRC_URI[sha256sum] = "3c016da2cf0cfeb5dfdcf3bea82b64935c4faa6eec32ae164c48d870b4583ffa"

S = "${WORKDIR}/md5-${PV}"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.3"

MAKE_FLAGS = "'PREFIX=${D}${prefix}' \
'CFLAGS=-fPIC -I${SYSROOTS}${includedir}' \
'LIB_OPTION=-fPIC -shared' \
'LUA_LIBDIR=${D}${libdir}${luadir}' \
'LUA_DIR=${D}${datadir}${luadir}' \
'LUA_VERSION_NUM=503' \
'CC=${CC}' \
"

do_compile () {
    oe_runmake clean
    oe_runmake ${MAKE_FLAGS}
}

do_install () {
    oe_runmake ${MAKE_FLAGS} install
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-md5.pc ${D}${libdir}/pkgconfig/
}

INSANE_SKIP_${PN} = "ldflags"
INSANE_SKIP_${PN}-dev = "ldflags"

FILES_${PN} = "${libdir}${luadir}/md5 \
 	${libdir}${luadir}/md5/core.so \
	${libdir}${luadir}/des56.so \
 	${datadir}${luadir}/md5.lua \
"

