DESCRIPTION = "`llthreads` library rewritten without `LuaNativeObjects` code generator"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYRIGHT.llthreads;md5=f5d7328cfe52196f9b3fcfe2e5088264"
HOMEPAGE = "https://github.com/moteus/lua-llthreads2"

PR = "1"

DEPENDS = "lua zeromq"
SRC_URI = "https://github.com/moteus/lua-llthreads2/archive/v${PV}.tar.gz \
	file://lua-llthreads2.pc \
"

SRC_URI[md5sum] = "b76af7ef6970b0e8089472fe377d3757"
SRC_URI[sha256sum] = "012399cac472f51848f6ee5822d71958fd9547511a9c7a57dde9a9cb6ec93ef7"

S = "${WORKDIR}/lua-llthreads2-${PV}"
luadir = "/lua/5.3"
# lzmqdir = "${libdir}/lua/lzmq"

EXTRA_OECONF = "LUA_VERSION_STRING=5.3"

inherit pkgconfig cmake

PACKAGES = "${PN}-dbg ${PN}-test ${PN}-examples ${PN}"

do_install_append () {
    install -d ${D}${libdir}${luadir}
    install -d ${D}${datadir}${luadir}
    mv ${D}${libdir}/lua/llthreads2* ${D}${libdir}${luadir}
    mv ${D}${datadir}/lua-llthreads2 ${D}${datadir}${luadir}
}

FILES_${PN} = "\
	${libdir}${luadir}/llthreads2.so \
	${libdir}${luadir}/llthreads2/*  \
"
# FILES_${PN}-dbg = "\
# /usr/src/debug/lua-lzmq/${PV}-1/lzmq-${PV}/* \
# "

FILES_${PN}-test = "\
	${datadir}${luadir}/lua-llthreads2 \
"

# FILES_${PN}-examples = "\
# ${datadir}${luadir}/lzmq/example/* \
# "

