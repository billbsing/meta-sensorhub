DESCRIPTION = "Pattern-matching library for Lua, based on Parsing Expression Grammars"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license_${PN}.html;beginline=1384;endline=1414;md5=edae3bad58030b744e197a59928f9bdc"
HOMEPAGE = "http://www.keplerproject.org/md5/"

PR = "r0"

DEPENDS = "lua"

SRC_URI = "https://github.com/LuaDist/lpeg//archive/${PV}.tar.gz;name=tarball \
	http://www.inf.puc-rio.br/~roberto/lpeg/;name=license;downloadfilename=license_${PN}.html \
	file://lua-lpeg.pc \
	file://makefile.patch \
"

SRC_URI[tarball.md5sum] = "faaf4ddf9b6ae13377dbf32ff8fcabc3"
SRC_URI[tarball.sha256sum] = "cdd8b8c6217522ef15c2129ec4cf550accfc162b89474c7340a3569496f9f907"

SRC_URI[license.md5sum] = "3cb5e143d0adf6656c8edeb7342f6071"
SRC_URI[license.sha256sum] = "de2b0ef5c730564d38434e10b0f0615d788af46b51887fc66dd9dbcf442d4329"

S = "${WORKDIR}/lpeg-${PV}"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.3"

MAKE_FLAGS = "'PREFIX=${D}${prefix}' \
'LUA_LIBDIR=${D}${libdir}${luadir}' \
'LUA_DIR=${D}${datadir}${luadir}' \
'LUA_VERSION_NUM=503' \
'LUA_INCLUDE=${SYSROOTS}${includedir}' \
'COPT=-O2 -DLUA_C89_NUMBERS' \
"

# 'COPT=-DLUA_32BITS' 

do_compile () {
    oe_runmake clean
    oe_runmake ${MAKE_FLAGS} linux
}


do_install () {
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-lpeg.pc ${D}${libdir}/pkgconfig/
    install -d ${D}${libdir}${luadir}
    install -m 0644 ${S}/lpeg.so ${D}${libdir}${luadir}
}

INSANE_SKIP_${PN} = "ldflags"
INSANE_SKIP_${PN}-dev = "ldflags"

FILES_${PN} = "${libdir}${luadir}/lpeg.so"

