DESCRIPTION = "Azure IotHub client for Lua"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=c6332748456118de50c6099011390b56"
HOMEPAGE = "https://github.com/billbsing/lua-azure-iot-hub"

PR = "r0"

DEPENDS = "lua azure-iot-hub-lib"

SRCREV = "${AUTOREV}"

SRC_URI = "git://github.com/billbsing/lua-azure-iot-hub.git \
	file://lua-azure-iot-hub.pc \
"

SRC_URI[md5sum] = "301fa48028c1d19c46319d335383c185"
SRC_URI[sha256sum] = "8cbd4d613e8ef17a02de2f9f7f5d3f6f08e1dbccf5ee946eca5a1179d521a33a"

S = "${WORKDIR}/git"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.2"


# 'CFLAGS=${CFLAGS} -Wall -fPIC -std=gnu99 -I${SYSROOTS}${includedir}' 

MAKE_FLAGS = "'PREFIX=${D}${prefix}' \
'CFLAGS=${CFLAGS} -I${SYSROOTS}${includedir}' \
'LIB_DIR=${D}${libdir}' \
'INCLUDE_DIR=${D}${includedir}' \
'LUA_LIB_DIR=${D}${libdir}${luadir}' \
'LUA_DIR=${D}${datadir}${luadir}' \
'LUA_VERSION_NUM=502' \
'AZURE_IOTHUB_INC_DIR=${SYSROOTS}${includedir}/azure-iot-hub' \
'AZURE_IOTHUB_LIB_DIR=${SYSROOTS}${libdir}/azure-iot-hub' \
'CC=${CC}' \
"


do_compile () {
    oe_runmake ${MAKE_FLAGS}
}


do_install () {
    oe_runmake ${MAKE_FLAGS} install
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-azure-iot-hub.pc ${D}${libdir}/pkgconfig/
}

PACKAGES = "${PN}-dbg ${PN}"

FILES_${PN} = "${libdir}${luadir}/luaazureiothub.so \
	${libdir}/pkgconfig/lua-azure-iot-hub.pc \
"


RDEPENDS_${PN} = "util-linux-libuuid libcrypto libssl"

