DESCRIPTION = "Openssl binding for Lua, which have LuaCrypto-compat module..."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=812b618ca15555178643425a6ee34a6d"
HOMEPAGE = "https://github.com/zhaozg/lua-openssl"

PR = "rc1"

DEPENDS = "lua"

SRC_URI = "https://github.com/zhaozg/lua-openssl/archive/${PV}.tar.gz;name=openssl \
	https://github.com/keplerproject/lua-compat-5.3/archive/v0.3.tar.gz;name=lua-compat \
	file://X509_ALGOR_cmp.patch \
	file://lua-openssl.pc \
"

SRC_URI[openssl.md5sum] = "f53bd850ff27b925cc97d230adb8a2ae"
SRC_URI[openssl.sha256sum] = "9f6de0a6c66129b23059d790c3b9a9473dccfcb780c83c1c820585f40b800305"
SRC_URI[lua-compat.md5sum] = "98f93f9de24b26ee5f6f7053979599f8"
SRC_URI[lua-compat.sha256sum] = "ac61979b944b17a245c6b1a696a23b18e664296d392dc1966788afda6a911ccd"

RDEPENDS_${PN} = "libcrypto libssl"

S = "${WORKDIR}/lua-openssl-${PV}"
LUA_COMPAT_S="${WORKDIR}/lua-compat-5.3-0.3"

SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/${lua_version}"
lua_version = "5.2"

MAKE_FLAGS = "'prefix=${D}' \
'LUA_VERSION=502'  \
'LUAV=${lua_version}' \
'LUA_CFLAGS=-I${SYSROOTS}${includedir}${luadir}' \
'LUA_LIBS=-L${SYSROOTS}${libdir}' \
'LUA_LIBDIR=${SYSROOTS}${libdir}${luadir}' \
'OPENSSL_LIBS=-lcrypto -lssl' \
'OPENSSL_CFLAGS=-fPIC -lrt -dl'  \
'CC=${CC} -fPIC'  \
'LDFLAGS=-export-dynamic -fPIC -lrt -ldl' \
"

do_compile () {
    cp -r ${LUA_COMPAT_S} ${SYSROOTS}${includedir}${luaddir}/lua-compat
  
    oe_runmake ${MAKE_FLAGS} clean
    oe_runmake ${MAKE_FLAGS} 
}


do_install () {
    oe_runmake 'LUA_LIBDIR=${D}${libdir}${luadir}' install

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-openssl.pc ${D}${libdir}/pkgconfig/
}


FILES_${PN} = "\
	${libdir}${luadir} \
	${libdir}${luadir}/openssl.so \
"

