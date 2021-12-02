DESCRIPTION = "A lua mqttc client which support MQTT version 3.1.1"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://notice.html;md5=a00d6f9ab542be7babc2d8b80d5d2a4c"
HOMEPAGE = "https://github.com/Yongke/luamqttc"

PR = "1"

DEPENDS = "lua lua-socket"

SRC_URI = "https://github.com/Yongke/luamqttc/archive/v${PV}.tar.gz \
	file://lua-mqttc.pc \
	file://CMakeLists.patch \
"

SRC_URI[md5sum] = "e014ae76a1a794e4b5e952737d19ba03"
SRC_URI[sha256sum] = "16da6c4b39015c45e315d2585b02b75b6f034a8237bebbce1732e69872e8a6a1"

S = "${WORKDIR}/luamqttc-${PV}"
luadir = "/lua/5.3"


EXTRA_OEBUILD = "LUA_VERSION=5.3  \
INCLUDE_DIR=${D}${libdir}${luadir} \
LUA_LIBDIR=${D}${libdir}${luadir}"

inherit pkgconfig cmake

do_install () {
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-mqttc.pc ${D}${libdir}/pkgconfig/
    install -d ${D}${libdir}${luadir}
    install -m 0644 ${WORKDIR}/build/libluamqttpacket.so ${D}${libdir}${luadir}/
    install -d ${D}${datadir}${luadir}/mqttc
    install -m 0644 ${S}/src/client.lua ${D}${datadir}${luadir}/mqttc/
    install -m 0644 ${S}/src/timer.lua ${D}${datadir}${luadir}/mqttc/
}



FILES_${PN} = "${libdir}/pkgconfig/lua-mqttc.pc \
${libdir}${luadir}/libluamqttpacket.so \
${datadir}${luadir}/mqttc/client.lua \
${datadir}${luadir}/mqttc/timer.lua \
"

