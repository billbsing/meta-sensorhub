DESCRIPTION = "Lua Web Server API"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license_${PN}.html;md5=7c2fa6e47aa3fe401279cf647239e7f9"
HOMEPAGE = "http://keplerproject.github.com/wsapi"

PR = "r0"

DEPENDS = "lua lua-filesystem libfastcgi"
RDEPENDS_${PN} = "lua"

SRC_URI = "https://github.com/keplerproject/wsapi/archive/v${PV}.tar.gz;name=tarball \
	http://keplerproject.github.io/wsapi/license.html;name=license;downloadfilename=license_${PN}.html \
	file://lua-wsapi.pc \
"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"


SRC_URI[tarball.md5sum] = "3d4bb019c984128eff360b57200b19b5"
SRC_URI[tarball.sha256sum] = "49c73a35c69e8a708c6247223969c4005dd9bebb8b036343b10d1c9020e73a04"

SRC_URI[license.md5sum] = "7c2fa6e47aa3fe401279cf647239e7f9"                                                                                                                                   
SRC_URI[license.sha256sum] = "ed59c0a607f5f2b782c8046a982577e61d71f8b9a9363cf5b38831e50c9f06ea" 

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
