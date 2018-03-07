DESCRIPTION = "Openssl binding for Lua, which have LuaCrypto-compat module..."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d59d1a970000b257b5b9349f1cac6d6a"
HOMEPAGE = "https://github.com/wahern/luaossl"

PR = "rc1"

DEPENDS = "lua"

# https://github.com/wahern/luaossl/archive/rel-20171028.tar.gz

SRC_URI = "https://github.com/wahern/luaossl/archive/rel-${PV}.tar.gz;name=tarball;downloadfilename=lua-openssl_${PV}.tar.gz \
	https://github.com/keplerproject/lua-compat-5.3/archive/v0.6.tar.gz;name=lua-compat;downloadfilename=lua-compat-0.6.tar.gz \
	file://lua-openssl.pc \
"

#	https://github.com/keplerproject/lua-compat-5.3/archive/v0.3.tar.gz;name=lua-compat 
#	file://X509_ALGOR_cmp.patch 
#	file://lua-openssl.pc 
#

SRC_URI[tarball.md5sum] = "0859a60515c410e447ffc1d159c3916d"
SRC_URI[tarball.sha256sum] = "0fe2f898d05666ffe5554a0cc92262ef47022f50d35e39d5e6530abd1dbce301"

SRC_URI[lua-compat.md5sum] = "e21cb9fce0953c49c0eaa271213dad4d"
SRC_URI[lua-compat.sha256sum] = "a8e0d884526092a63a88c8e38ca193c0dad87fdb42544b79d9ce83aae5595a4d"

RDEPENDS_${PN} = "libcrypto libssl"

S = "${WORKDIR}/luaossl-rel-${PV}"
LUA_COMPAT_S="${WORKDIR}/lua-compat-5.3-0.6"

SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/${lua_version}"
lua_version = "5.3"

MAKE_FLAGS = "'prefix=${D}' \
'LUA_VERSION=503'  \
'LUAV=${lua_version}' \
'LUA_CFLAGS=-I${SYSROOTS}${includedir}${luadir}' \
'LUA_LIBS=-L${SYSROOTS}${libdir}' \
'LUA_LIBDIR=${SYSROOTS}${libdir}${luadir}' \
'OPENSSL_LIBS=-lcrypto -lssl' \
'OPENSSL_CFLAGS=-fPIC -lrt -dl'  \
'CC=${CC} -fPIC'  \
'LDFLAGS=-export-dynamic -fPIC -lrt -ldl' \
'DESTDIR=${D}' \
'libdir=${D}${libdir}' \
"

INSTALL_FLAGS="'prefix=${D}' \
'DESTDIR=${D}' \
'CP=install -m 0644' \
"

do_compile () {
    cp -r ${LUA_COMPAT_S} ${SYSROOTS}${includedir}${luaddir}/lua-compat
  
    oe_runmake ${MAKE_FLAGS} clean
    oe_runmake ${MAKE_FLAGS} 
}


do_install () {
    oe_runmake ${INSTALL_FLAGS} install5.3

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-openssl.pc ${D}${libdir}/pkgconfig/
}

INSANE_SKIP_${PN} = "ldflags"
INSANE_SKIP_${PN}-dev = "ldflags"

FILES_${PN} = "\
	${libdir}${luadir} \
	${datadir}${luadir} \
"

