DESCRIPTION = "Database connectivity for Lua (SQLite3 driver)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license_${PN}.html;md5=66567e98ce30262a987b42bd88db71a8"
HOMEPAGE = "http://www.keplerproject.org/luasql/"

PR = "r0"

DEPENDS = "lua sqlite3"

SRC_URI = "https://github.com/keplerproject/luasql/archive/v${PV}.tar.gz;name=tarball;downloadfilename=luasql_${PV}.tar.gz \
	http://keplerproject.github.io/luasql/doc/us/license.html;name=license;downloadfilename=license_${PN}.html \
	file://lua-sqlite3.pc \
"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"


SRC_URI[tarball.md5sum] = "af9f0f3a2313a1fcf88c40700092048d"
SRC_URI[tarball.sha256sum] = "e173ff7b17a2757951b4b2f67d3b1bfe04caad7185b68cffa7758ce822e25e9f"

SRC_URI[license.md5sum] = "66567e98ce30262a987b42bd88db71a8"
SRC_URI[license.sha256sum] = "86fd83cff3a7f3f358ed3758426f160a8542a07ce5692951ba6048ed6ba813c2"



S = "${WORKDIR}/luasql-${PV}"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.2"

MAKE_FLAGS = "'T=sqlite3' \
'PREFIX=${D}${prefix}' \
'CFLAGS=-fPIC' \
'LIB_OPTION = -shared -fPIC' \
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


