DESCRIPTION = "LuaSec is a binding for OpenSSL library to provide TLS/SSL communication."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=70571f4e22925c672a25e539a76caeb7"
HOMEPAGE = "https://github.com/brunoos/luasec"

PR = "1"

DEPENDS = "lua lua-socket openssl"

SRC_URI = "https://github.com/brunoos/luasec/archive/v${PV}.tar.gz \
	file://lua-sec.pc \
"

SRC_URI[md5sum] = "0635682400b9f0dabcb01039bd921df2"
SRC_URI[sha256sum] = "0001e24323beee51cd1ee186b369b50a19696d59960b537093883e5ce264d886"

S = "${WORKDIR}/luasec-${PV}"
luadir = "/lua/5.3"

# 'CDIR_linux=${libdir}${luadir}' 
# 
# 'LUAV=5.3' 
# 'LDIR_linux=${datadir}${luadir}' 
# 
# 'LUAINC_linux=${SYSROOTS}${includedir}' 
# 'LUAINC_linux_base=${SYSROOTS}${includedir}' 
# 'MYCFLAGS=-arch ${MACHINE}' 
# 'LUAPREFIX_linux=${prefix}' 


EXTRA_OEMAKE = "'prefix=${D}' \
'INC_PATH=-I${D}${includedir}' \
'LIB_PATH=-L${D}${libdir}' \
'LUAPATH=${D}${datadir}${luadir}'  \
'LUACPATH=${D}${libdir}${luadir}' \
'MYCFLAGS=-DLUA_32BITS' \
'GCC_linux=${CC}' \
"

do_compile () {
    oe_runmake clean
    oe_runmake linux
}

do_install() {
	oe_runmake install
}

FILES_${PN} = "${libdir}${luadir}/ssl.so \
  ${datadir}${luadir}/ssl.lua  \
  ${datadir}${luadir}/ssl/https.lua \
"

