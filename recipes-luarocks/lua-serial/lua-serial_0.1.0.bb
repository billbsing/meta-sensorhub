DESCRIPTION = "The Lua serial module provides functions to deal with serial port settings such as bit rates (speed) data bits, parity."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=25c5261bc8703bdb4e990ad37b86fdec"
HOMEPAGE = "https://github.com/javalikescript/luaserial"

PR = "0"
MAJOR_VERSION="${@bb.data.getVar('PV', d, 1).split('.')[0]}"
MINOR_VERSION="${@bb.data.getVar('PV', d, 1).split('.')[1]}"
PV_SOURCE = "${MAJOR_VERSION}.${MINOR_VERSION}"

DEPENDS = "lua"

SRC_URI = "https://github.com/javalikescript/luaserial/archive/refs/tags/${PV_SOURCE}.tar.gz \
	file://Makefile \
	file://lua-serial.pc \
"

SRC_URI[md5sum] = "e27c059d8f525355c90faa34856f3a23"
SRC_URI[sha256sum] = "b96e5cb26482d8cb7b193aa9263f357e47c8745f29fe86aadb4a55541e559d88"

S = "${WORKDIR}/luaserial-${PV_SOURCE}"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.3"

EXTRA_OEMAKE = " \
'PLAT=linux' \
'LUA_INCDIR=${SYSROOTS}${includedir}' \
'LUA_LIBDIR=${SYSROOTS}${libdir}'  \
'CFLAGS=${CFLAGS}' \
'LIBFLAG=-static-libgcc ${LDFLAGS}' \
'LUA_VERSION=5.3' \
'CC=${CC}' \
"


do_compile () {
	cp "${WORKDIR}/Makefile" "${S}/"
    oe_runmake clean
    oe_runmake show 
    oe_runmake 
}

do_install() {
	install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-serial.pc ${D}${libdir}/pkgconfig/

	
	install -d ${D}${libdir}${luadir}
    install -m 0644 ${S}/serial.so ${D}${libdir}${luadir}
}

FILES_${PN} = "${libdir}${luadir}/serial.so"

