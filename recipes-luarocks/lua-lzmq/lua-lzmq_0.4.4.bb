DESCRIPTION = "Lua binding to ZeroMQ"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=f8e09b9966d70fdedd2c53a4cfa7e085"
HOMEPAGE = "https://github.com/zeromq/lzmq"

PR = "1"

DEPENDS = "lua zeromq"
SRC_URI = "https://github.com/zeromq/lzmq/archive/v${PV}.tar.gz \
	file://lua-lzmq.pc \
"

SRC_URI[md5sum] = "dba93aab8c4ba38fc573e6421118f27d"
SRC_URI[sha256sum] = "cf70200045b8bcb0e929c338ad421b6a291cf1038053532888dc201af3224d8b"

S = "${WORKDIR}/lzmq-${PV}"
luadir = "/lua/5.3"
lzmqdir = "${libdir}/lua/lzmq"

EXTRA_OECONF = "LUA_VERSION_STRING=5.3"

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
/usr/src/debug/lua-lzmq/${PV}-1/lzmq-${PV}/* \
"

FILES_${PN}-test = "\
${datadir}${luadir}/lzmq/test/* \
${datadir}${luadir}/lzmq/test/.luacov \
${datadir}${luadir}/lzmq/test/.luacov.ffi \
"

FILES_${PN}-examples = "\
${datadir}${luadir}/lzmq/example/* \
"

