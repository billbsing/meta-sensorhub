DESCRIPTION = "Lua binding to ZeroMQ"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=f2538dd3b47790d77a798895f5c9c26d"
HOMEPAGE = "https://github.com/zeromq/lzmq"

PR = "1"

DEPENDS = "lua zeromq"
SRC_URI = "https://github.com/zeromq/lzmq/archive/v${PV}.tar.gz \
	file://lua-lzmq.pc \
"

SRC_URI[md5sum] = "c4e51a60a5a26987bdce59e45d674a9e"
SRC_URI[sha256sum] = "51ec00117b0570db82b1eba7b62e95d7e98c880a028584b195a98b433dd8edd1"

S = "${WORKDIR}/lzmq-${PV}"
luadir = "/lua/5.2"
lzmqdir = "${libdir}/lua/lzmq"

EXTRA_OECONF = "LUA_VERSION_STRING=5.2"

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

