DESCRIPTION = "Basic cryptographic library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license_${PN}.html;md5=a35821c342d3ddd417297923a254aefd"
HOMEPAGE = "http://www.keplerproject.org/md5/"

PR = "r0"

DEPENDS = "lua"

SRC_URI = "https://github.com/keplerproject/md5/archive/v${PV}.tar.gz;name=tarball \
	http://www.keplerproject.org/luasql/license.html;name=license;downloadfilename=license_${PN}.html \
	file://lua-md5.pc \
"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"


SRC_URI[tarball.md5sum] = "c166f8a983401802a86655a8c733441e"
SRC_URI[tarball.sha256sum] = "3c016da2cf0cfeb5dfdcf3bea82b64935c4faa6eec32ae164c48d870b4583ffa"

SRC_URI[license.md5sum] = "a35821c342d3ddd417297923a254aefd"
SRC_URI[license.sha256sum] = "250ed109a20a48283c0aed493cd346388ce47846ae7d9e11f18a50249e2481cb"

S = "${WORKDIR}/md5-${PV}"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.2"

MAKE_FLAGS = "'PREFIX=${D}${prefix}' \
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

FILES_${PN} = "${libdir}${luadir}/des56.so \
 	${libdir}${luadir}/md5/core.so \
 	${datadir}${luadir}/md5.lua \
"

