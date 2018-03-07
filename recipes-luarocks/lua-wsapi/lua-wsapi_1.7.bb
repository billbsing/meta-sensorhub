DESCRIPTION = "Lua Web Server API"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license_${PN}.html;md5=7c303fa69c61ad25e6a04ef328d650f0"
HOMEPAGE = "http://keplerproject.github.com/wsapi"

PR = "r0"

DEPENDS = "lua lua-filesystem fcgi"
RDEPENDS_${PN} = "lua"

SRC_URI = "https://github.com/keplerproject/wsapi/archive/v${PV}.tar.gz;name=tarball \
	http://keplerproject.github.io/wsapi/license.html;name=license;downloadfilename=license_${PN}.html \
	file://lua-wsapi.pc \
"

SRC_URI[tarball.md5sum] = "aacadf9dabeb82b044cb3f1006c5df0d"
SRC_URI[tarball.sha256sum] = "48dc7aba0fd2e96a3e5ef51045b5d923964f6ae299de761aa0467031ad44e987"

SRC_URI[license.md5sum] = "7c303fa69c61ad25e6a04ef328d650f0"
SRC_URI[license.sha256sum] = "6aa14e3febf7a9e810ce672b015f5a5514241ce5d1c3a6a48f921f089d270159"

S = "${WORKDIR}/wsapi-${PV}"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.3"

MAKE_FLAGS = "'PREFIX=${D}${prefix}' \
'LUA_LIBDIR=${D}${libdir}${luadir}' \
'LUA_DIR=${D}${datadir}${luadir}' \
'LUA_VERSION_NUM=503' \
'LIB_OPTION = -shared -fPIC' \
'BIN_DIR=${D}${bindir}' \
"


do_compile () {
    oe_runmake clean
    oe_runmake config
    oe_runmake all ${MAKE_FLAGS}
}


do_install () {
   echo ${bindir} 
    oe_runmake ${MAKE_FLAGS} install
    oe_runmake ${MAKE_FLAGS} install-fcgi
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-wsapi.pc ${D}${libdir}/pkgconfig/
}

FILES_${PN} = "${datadir}${luadir}/wsapi.lua \
	${datadir}${luadir}/wsapi/*.lua \
	${libdir}${luadir}/lfcgi.so  \
	${bindir}/wsapi.cgi \
	${bindir}/wsapi.fcgi \
"
