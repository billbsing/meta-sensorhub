DESCRIPTION = "Lua bindings for POSIX (including curses)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=60996f8a87d66935bafd18394981cd71"
HOMEPAGE = "http://github.com/luaposix/luaposix/"

PR = "r1"

DEPENDS = "lua"

SRC_URI = "https://github.com/luaposix/luaposix/archive/v${PV}.tar.gz \
	file://lua-posix.pc \
"

# file://configure.ac.patch \
inherit autotools 

SRC_URI[md5sum] = "2bfede7b7cee96c5d0f6c0354e17498c"
SRC_URI[sha256sum] = "09dbbde825fd9b76a8a1f6a80920434f8629a392cd1840021ed4b95b21fcf073"


S = "${WORKDIR}/luaposix-release-v32"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.3"

MAKE_FLAGS = "'T=sqlite3' \
'PREFIX=${D}${prefix}' \
'DRIVER_LIBS=-L${SYSROOTS}${libdir} -lsqlite3' \
'DRIVER_INCS=-I${SYSROOTS}${include}' \
'LUA_LIBDIR=${D}${libdir}${luadir}' \
'LUA_DIR=${D}${datadir}${luadir}' \
'LUA_VERSION_NUM=503' \
"


EXTRA_OECONF = "LUA_DIR=${datadir}${luadir} \
LUA_EXEC_DIR=${libdir}${luadir} \
LUA_INCLUDE=${SYSROOTS}${includepath} \
libdir=${libdir}${luadir} \
"

FILES_${PN} = "${libdir}${luadir}/posix_c.la \
	${libdir}${luadir}/curses_c.so \
	${libdir}${luadir}/curses_c.la \
	${libdir}${luadir}/posix_c.so \
	${datadir}${luadir}/curses.lua \
	${datadir}${luadir}/posix.lua \
	${datadir}${luadir}/posix \
	${datadir}${luadir}/posix/sys.lua \
"


