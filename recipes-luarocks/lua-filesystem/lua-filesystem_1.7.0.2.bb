DESCRIPTION = "File System Library for the Lua Programming Language"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d9b7e441d51a96b17511ee3be5a75857"
HOMEPAGE = "http://keplerproject.github.com/luafilesystem"

PR = "1"
USE_PV = "${@'${PV}'.replace('.', '_')}"

DEPENDS = "lua sqlite3"

SRC_URI = "https://github.com/keplerproject/luafilesystem/archive/v${USE_PV}.tar.gz \
	file://lua-filesystem.pc \
"

#	file://config.patch 

SRC_URI[md5sum] = "5166c00df1599a54dc97e84852be7f0c"
SRC_URI[sha256sum] = "23b4883aeb4fb90b2d0f338659f33a631f9df7a7e67c54115775a77d4ac3cc59"

S = "${WORKDIR}/luafilesystem-${USE_PV}"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.3"

# 'PREFIX=${D}${prefix}' 


MAKE_FLAGS = "'PREFIX=${D}${prefix}'  \
'LUA_LIBDIR=${D}${libdir}${luadir}' \
'LUA_INC=${SYSROOTS}${includedir}' \
'LUA_VERSION_NUM=503' \
'CFLAGS=-fPIC -I${SYSROOTS}${includedir}  -DLUA_C89_NUMBERS -DLUA_32BITS' \
'CC=${CC}' \
"


do_compile () {
    oe_runmake clean
    oe_runmake ${MAKE_FLAGS}
}


do_install () {
    
    oe_runmake ${MAKE_FLAGS} install
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-filesystem.pc ${D}${libdir}/pkgconfig/
}

INSANE_SKIP_${PN} = "ldflags"
INSANE_SKIP_${PN}-dev = "ldflags"


FILES_${PN} = "${libdir}${luadir}/lfs.so"

# RDEPENDS_${PN} = "libc"

