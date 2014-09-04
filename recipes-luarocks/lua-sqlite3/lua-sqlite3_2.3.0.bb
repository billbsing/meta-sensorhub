DESCRIPTION = "Database connectivity for Lua (SQLite3 driver)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license.html;beginline=61;endline=107;md5=b64be35c72f82af37b05fb2f72b0284f"
HOMEPAGE = "http://www.keplerproject.org/luasql/"

PR = "r0"

DEPENDS = "lua sqlite3"

SRC_URI = "https://github.com/keplerproject/luasql/archive/v${PV}.tar.gz;name=tarball \
	http://www.keplerproject.org/luasql/license.html;name=license \
	file://lua-sqlite3.pc \
"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"


SRC_URI[tarball.md5sum] = "af9f0f3a2313a1fcf88c40700092048d"
SRC_URI[tarball.sha256sum] = "e173ff7b17a2757951b4b2f67d3b1bfe04caad7185b68cffa7758ce822e25e9f"

SRC_URI[license.md5sum] = "a35821c342d3ddd417297923a254aefd"
SRC_URI[license.sha256sum] = "250ed109a20a48283c0aed493cd346388ce47846ae7d9e11f18a50249e2481cb"

S = "${WORKDIR}/luasql-${PV}"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.2"

MAKE_FLAGS = "'T=sqlite3' \
'PREFIX=${D}${prefix}' \
'DRIVER_LIBS=-L${SYSROOTS}${libdir} -lsqlite3' \
'DRIVER_INCS=-I${SYSROOTS}${includedir}' \
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
    install -m 0644 ${WORKDIR}/lua-sqlite3.pc ${D}${libdir}/pkgconfig/
}

FILES_${PN} = "${libdir}${luadir}/luasql"


