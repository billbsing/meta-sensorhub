DESCRIPTION = "customizable JSON decoder/encoder"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bf7064a714513510018a46181cf261ae"
HOMEPAGE = "http://www.eharning.us/wiki/luajson/"

PR = "1"

DEPENDS = "lua"
SRC_URI = "https://github.com/harningt/luajson/archive/${PV}.tar.gz \
	file://lua-json.pc \
"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

SRC_URI[md5sum] = "e9f5454bef1e26c7cc0c3442c223cf9b"
SRC_URI[sha256sum] = "6a986efa210a912a04c8e6cae8f3b5e72172c02f974152e14818af6598a5e2db"

S = "${WORKDIR}/luajson-${PV}"
luadir = "/lua/5.2"

do_install () {


    install -d ${D}${datadir}${luadir}/json
    install -d ${D}${datadir}${luadir}/json/encode
    install -d ${D}${datadir}${luadir}/json/decode

    install -m 0644 ${S}/lua/*.lua ${D}${datadir}${luadir}
    install -m 0644 ${S}/lua/json/*.lua ${D}${datadir}${luadir}/json


    install -m 0644 ${S}/lua/json/encode/* ${D}${datadir}${luadir}/json/encode
    install -m 0644 ${S}/lua/json/decode/* ${D}${datadir}${luadir}/json/decode

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-json.pc ${D}${libdir}/pkgconfig/

}

FILES_${PN} = "${datadir}${luadir}/json \
	${datadir}${luadir}/json.lua \
	${datadir}${luadir}/json/* \
"


