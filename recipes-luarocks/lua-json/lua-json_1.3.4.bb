DESCRIPTION = "customizable JSON decoder/encoder"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ac0d9618a43915269abea2cb61c95cac"
HOMEPAGE = "http://www.eharning.us/wiki/luajson/"

PR = "r0"

DEPENDS = "lua"
SRC_URI = "https://github.com/harningt/luajson/archive/${PV}.tar.gz \
	file://lua-json.pc \
"

SRC_URI[md5sum] = "66ef8d00bcaeec91b25433a697090bc8"
SRC_URI[sha256sum] = "aff67d64027f747b4611646fd0421802eda60397da9076e3f7fb17227e542e99"

S = "${WORKDIR}/luajson-${PV}"
luadir = "/lua/5.3"

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


