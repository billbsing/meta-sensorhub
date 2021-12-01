DESCRIPTION = "A lua mqtt client which support MQTT version 3.1.1"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8497ff0803ca32bb344bb942e74c100b"
HOMEPAGE = "https://xhaskx.github.io/luamqtt"

PR = "1"

DEPENDS = "lua lua-socket"
SRC_URI = "https://github.com/xHasKx/luamqtt/archive/refs/tags/v${PV}.tar.gz \
	file://lua-mqtt.pc \
"

SRC_URI[md5sum] = "5c588a6ab3fa3cc467ee7e94f48a7d49"
SRC_URI[sha256sum] = "1fd10c2a5ff08d690b77abac56385f25f9a497ffe4d25451442af50feb1e8329"

S = "${WORKDIR}/luamqtt-${PV}"
luadir = "/lua/5.3"

do_compile () {
}

do_install () {
    install -d ${D}${datadir}${luadir}/mqtt
    install -m 0644 ${S}/mqtt/* ${D}${datadir}${luadir}/mqtt

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-mqtt.pc ${D}${libdir}/pkgconfig/

}

FILES_${PN} = "${datadir}${luadir}/mqtt/* \
"

