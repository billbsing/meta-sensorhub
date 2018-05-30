DESCRIPTION = "Portable inter-process communication for Lua"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=db730d22fa9e43373db197c933940973"
HOMEPAGE = "https://github.com/siffiejoe/lua-luaipc"

PR = "1"
SRCREV = "${AUTOREV}"


DEPENDS = "lua zeromq lua-llthreads2"
SRC_URI = "git://github.com/siffiejoe/lua-luaipc.git \
	file://lua-ipc.pc \
"

SRC_URI[md5sum] = "03807279230b2c4b3d0ab6a5cd586d8d"
SRC_URI[sha256sum] = "588924c87c9ddc461eb88da88ab7b29eb60c7a405769e5e1a54d724071b3988d"

S = "${WORKDIR}/git"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.3"


MAKE_FLAGS = "'PREFIX=${D}${prefix}' \
'CFLAGS=-fPIC -Wall -O2 -g' \
'EXTRALIBS=-L${SYSROOTS}${libdir}rt' \
'LUA_INCDIR=${SYSROOTS}${includedir}' \
'LIBFLAG=-fPIC -shared ${LDFLAGS}' \
'LUA_LIBDIR=${D}${libdir}${luadir}' \
'LUA_DIR=${D}${datadir}${luadir}' \
'DLL_INSTALL_DIR=${D}${libdir}${luadir}' \
'LUA_VERSION_NUM=503' \
'CC=${CC}' \
"

do_compile () {
    oe_runmake clean
    oe_runmake ${MAKE_FLAGS}
}

do_install () {
    oe_runmake ${MAKE_FLAGS} install
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-ipc.pc ${D}${libdir}/pkgconfig/
}

FILES_${PN} = "\
	${libdir}${luadir}/ipc.so \
"


#FILES_${PN}-dbg = "\
#	/usr/src/debug/lua-lzmq/${PV}-1/${PV}/* \
#"

#FILES_${PN}-test = "\
#	${datadir}${luadir}/lzmq/test/* \
#	${datadir}${luadir}/lzmq/test/.luacov \
#	${datadir}${luadir}/lzmq/test/.luacov.ffi \
#"

#FILES_${PN}-examples = "\
#	${datadir}${luadir}/lzmq/example/* \
#"
#
