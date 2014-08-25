DESCRIPTION = "Pattern-matching library for Lua, based on Parsing Expression Grammars"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/lpeg;beginline=1384;endline=1414;md5=cf3c4cdb7efe115ccaba0a86c38b6cb7"
HOMEPAGE = "http://www.keplerproject.org/md5/"

PR = "r0"

DEPENDS = "lua"

SRC_URI = "https://github.com/lua/lpeg/archive/${PV}.tar.gz;name=tarball \
	http://www.inf.puc-rio.br/~roberto/lpeg;name=license \
	file://lua-lpeg.pc \
	file://makefile.patch \
"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"


SRC_URI[tarball.md5sum] = "96a468edb8c51087e15600a2a6c714d6"
SRC_URI[tarball.sha256sum] = "3db883f8bdd7667032bf8053cd5c9d25a011156a8edbfa10f67680652dbb36aa"

SRC_URI[license.md5sum] = "a9f2b5ff6ca65c98eac97d9ece7fea4f"
SRC_URI[license.sha256sum] = "045079f006198ad41b182ab8b9f1bda71808727678f9e1ad61584c3c89845ac1"

S = "${WORKDIR}/lpeg-${PV}"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.2"

MAKE_FLAGS = "'PREFIX=${D}${prefix}' \
'LUA_LIBDIR=${D}${libdir}${luadir}' \
'LUA_DIR=${D}${datadir}${luadir}' \
'LUA_VERSION_NUM=502' \
'LUA_INCLUDE=${SYSROOTS}${includedir}' \
"


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

FILES_${PN} = "${libdir}${luadir}/lpeg.so \
"

