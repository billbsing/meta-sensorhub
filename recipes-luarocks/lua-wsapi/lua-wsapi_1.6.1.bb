DESCRIPTION = "Lua Web Server API"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license.html;beginline=61;endline=107;md5=ce95361dc1792e8c56a55e135a7aebcd"
HOMEPAGE = "http://keplerproject.github.com/wsapi"

PR = "r0"

DEPENDS = "lua lua-filesystem libfastcgi"

SRC_URI = "https://github.com/keplerproject/wsapi/archive/v${PV}.tar.gz;name=tarball \
	http://keplerproject.github.io/wsapi/license.html;name=license \
	file://lua-wsapi.pc \
"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"


SRC_URI[tarball.md5sum] = "3d4bb019c984128eff360b57200b19b5"
SRC_URI[tarball.sha256sum] = "49c73a35c69e8a708c6247223969c4005dd9bebb8b036343b10d1c9020e73a04"

SRC_URI[license.md5sum] = "fdc2f4a452f524d38b6cf6edcfd91798"
SRC_URI[license.sha256sum] = "208ec820f3d35201cb7819f09a85c702cddbca1b2fdc2009b8be03c0f6338cf4"

S = "${WORKDIR}/wsapi-${PV}"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.2"

MAKE_FLAGS = "'PREFIX=${D}${prefix}' \
'LUA_LIBDIR=${D}${libdir}${luadir}' \
'LUA_DIR=${D}${datadir}${luadir}' \
'LUA_VERSION_NUM=502' \
'LIB_OPTION = -shared -fPIC' \
'BIN_DIR=${D}${bindir}' \
"


do_compile () {
    oe_runmake clean
    oe_runmake config
    oe_runmake ${MAKE_FLAGS}
}


do_install () {
   echo ${bindir} 
    oe_runmake ${MAKE_FLAGS} install
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-wsapi.pc ${D}${libdir}/pkgconfig/
}

FILES_${PN} = "${datadir}${luadir}/wsapi.lua \
	${datadir}${luadir}/wsapi/*.lua \
	${bindir}/wsapi.cgi \
	${bindir}/wsapi.fcgi \
"
