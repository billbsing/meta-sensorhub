DESCRIPTION = "A lua mqttc client which support MQTT version 3.1.1"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://notice.html;md5=a00d6f9ab542be7babc2d8b80d5d2a4c"
HOMEPAGE = "https://github.com/Yongke/luamqttc"

PR = "1"

DEPENDS = "lua lua-socket"

SRC_URI = "https://github.com/Yongke/luamqttc/archive/v${PV}.tar.gz \
	file://lua-mqttc.pc \
"

SRC_URI[md5sum] = "e014ae76a1a794e4b5e952737d19ba03"
SRC_URI[sha256sum] = "16da6c4b39015c45e315d2585b02b75b6f034a8237bebbce1732e69872e8a6a1"

S = "${WORKDIR}/luamqttc-${PV}"
luadir = "/lua/5.3"


EXTRA_OEBUILD = "LUA_VERSION_STRING=5.3  \
INC_DIR=${D}${libdir}${luadir} \
LUA_LIBDIR=${D}${libdir}${luadir}"

inherit pkgconfig cmake

# FILES_${PN} = "${datadir}${luadir}/mqtt_library.lua \
#	${datadir}${luadir}/utility.lua \
# "

