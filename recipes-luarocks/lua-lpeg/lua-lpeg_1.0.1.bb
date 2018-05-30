DESCRIPTION = "Pattern-matching library for Lua, based on Parsing Expression Grammars"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license_${PN}.html;beginline=1384;endline=1414;md5=edae3bad58030b744e197a59928f9bdc"
HOMEPAGE = "http://www.keplerproject.org/md5/"

PR = "1"

DEPENDS = "lua"

SRC_URI = "http://www.inf.puc-rio.br/~roberto/lpeg/lpeg-${PV}.tar.gz;name=tarball \
	http://www.inf.puc-rio.br/~roberto/lpeg/;name=license;downloadfilename=license_${PN}.html \
	file://makefile.patch  \
	file://lua-lpeg.pc \
"


SRC_URI[tarball.md5sum] = "049e0cc18fd540ccddab701a1d771e46"
SRC_URI[tarball.sha256sum] = "62d9f7a9ea3c1f215c77e0cadd8534c6ad9af0fb711c3f89188a8891c72f026b"

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
'COPT=-DLUA_32BITS' \
'CFLAGS=$(COPT) -I$(LUA_LIBDIR) -fPIC' \
"
# 'COPT=-DLUA_32BITS' 
# 'COPT=-O2' 
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

