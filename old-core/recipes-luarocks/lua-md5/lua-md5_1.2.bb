DESCRIPTION = "Basic cryptographic library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README;md5=095f673e0ae6e1364d882d1bdb636df5"
HOMEPAGE = "http://www.keplerproject.org/md5/"

PR = "r0"

DEPENDS = "lua"

SRC_URI = "https://github.com/keplerproject/md5/archive/v${PV}.tar.gz \
	file://lua-md5.pc \
"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"


SRC_URI[md5sum] = "c166f8a983401802a86655a8c733441e"
SRC_URI[sha256sum] = "3c016da2cf0cfeb5dfdcf3bea82b64935c4faa6eec32ae164c48d870b4583ffa"


S = "${WORKDIR}/md5-${PV}"
luadir = "/lua/5.2"

MAKE_FLAGS = "'PREFIX=${D}${prefix}' \
'CFLAGS=-fPIC' \
'LIB_OPTION=-fPIC -shared' \
'LUA_LIBDIR=${D}${libdir}${luadir}' \
'LUA_DIR=${D}${datadir}${luadir}' \
'LUA_VERSION_NUM=502' \
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

FILES_${PN} = "${libdir}${luadir}/md5 \
 	${libdir}${luadir}/md5/core.so \
	${libdir}${luadir}/des56.so \
 	${datadir}${luadir}/md5.lua \
"

