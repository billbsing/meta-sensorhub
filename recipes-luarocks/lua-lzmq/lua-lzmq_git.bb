DESCRIPTION = "Lua binding to ZeroMQ"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=f8e09b9966d70fdedd2c53a4cfa7e085"
HOMEPAGE = "https://github.com/zeromq/lzmq"

PR = "1"
SRCREV = "${AUTOREV}"

# SRC_URI = "https://github.com/zeromq/lzmq/archive/v${PV}.tar.gz 

DEPENDS = "lua zeromq lua-llthreads2"
SRC_URI = "git://github.com/zeromq/lzmq.git \
	file://lua-lzmq.pc \
"

#	file://00002-debug.patch 
#	file://00001-64bit-to-32bit.patch   


SRC_URI[md5sum] = "03807279230b2c4b3d0ab6a5cd586d8d"
SRC_URI[sha256sum] = "588924c87c9ddc461eb88da88ab7b29eb60c7a405769e5e1a54d724071b3988d"

# S = "${WORKDIR}/lzmq-${PV}"
S = "${WORKDIR}/git"
luadir = "/lua/5.3"
lzmqdir = "${libdir}/lua/lzmq"

EXTRA_OECONF = "LUA_VERSION_STRING=5.3"
# EXTRA_OECMAKE = "-DLUA_32BITS:bool=ON" 

inherit pkgconfig cmake

PACKAGES = "${PN}-dbg ${PN}-test ${PN}-examples ${PN}"

do_install_append () {
    install -d ${D}${libdir}${luadir}
    install -d ${D}${datadir}${luadir}
    mv ${D}${libdir}/lua/lzmq* ${D}${libdir}${luadir}
    mv ${D}${datadir}/lzmq* ${D}${datadir}${luadir}
}

FILES_${PN} = "\
	${libdir}${luadir}/lzmq.so \
	${libdir}${luadir}/lzmq/*  \
	${datadir}${luadir}/lzmq/LICENCE.txt \
	${datadir}${luadir}/lzmq/README.md \
"
FILES_${PN}-dbg = "\
	/usr/src/debug/lua-lzmq/${PV}-1/${PV}/* \
"

FILES_${PN}-test = "\
	${datadir}${luadir}/lzmq/test/* \
	${datadir}${luadir}/lzmq/test/.luacov \
	${datadir}${luadir}/lzmq/test/.luacov.ffi \
"

FILES_${PN}-examples = "\
	${datadir}${luadir}/lzmq/example/* \
"

