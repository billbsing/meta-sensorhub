DESCRIPTION = "File System Library for the Lua Programming Language"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d9b7e441d51a96b17511ee3be5a75857"
HOMEPAGE = "http://keplerproject.github.com/luafilesystem"

PR = "1"

DEPENDS = "lua sqlite3"

SRC_URI = "https://github.com/keplerproject/luafilesystem/archive/v1_6_2.tar.gz \
	file://lua-filesystem.pc \
	file://config.patch \
"


SRC_URI[md5sum] = "8d20c36c70df8291cc1cc478155645ef"
SRC_URI[sha256sum] = "7f2910e6c7fbc1d64d0a6535e6a514ed138051af13ee94bccdeb7a20146f18d9"

S = "${WORKDIR}/luafilesystem-1_6_2"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.3"

# 'PREFIX=${D}${prefix}' 


MAKE_FLAGS = "'PREFIX=${D}${prefix}'  \
'LUA_LIBDIR=${D}${libdir}${luadir}' \
'LUA_INC=${SYSROOTS}${includedir}' \
'LUA_VERSION_NUM=503' \
'CFLAGS=-fPIC -I${SYSROOTS}${includedir}' \
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

