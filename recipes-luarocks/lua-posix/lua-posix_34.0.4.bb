DESCRIPTION = "Lua bindings for POSIX (including curses)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0857507905a6983216d8e0b27533aa93"
HOMEPAGE = "http://github.com/luaposix/luaposix/"

PR = "r1"

DEPENDS = "lua lua-native"

SRC_URI = "https://github.com/luaposix/luaposix/archive/v${PV}.tar.gz;name=tarball;downloadfilename=lua-posix_${PV}.tar.gz \
	file://lua-posix.pc \
 	https://github.com/keplerproject/lua-compat-5.3/archive/v0.6.tar.gz;name=lua-compat;downloadfilename=lua-compat_0.6.tar.gz \
"

#	file://configure.ac.patch 
# file://configure.ac.patch 

# inherit autotools 

SRC_URI[tarball.md5sum] = "c736e339e5f35adafda6e626ef8dd119"
SRC_URI[tarball.sha256sum] = "eb6e7322da3013bdb3d524f68df4f5510a2efd805c06bf7cc27be6611eab7483"

SRC_URI[lua-compat.md5sum] = "e21cb9fce0953c49c0eaa271213dad4d"
SRC_URI[lua-compat.sha256sum] = "a8e0d884526092a63a88c8e38ca193c0dad87fdb42544b79d9ce83aae5595a4d"

S = "${WORKDIR}/luaposix-${PV}"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.3"

MAKE_FLAGS = "'PREFIX=${D}${prefix}' \
'CC=${CC}' \
'CFLAGS=-fPIC -I${SYSROOTS}${includedir} -DLUA_32BITS' \
'DRIVER_LIBS=-L${SYSROOTS}${libdir}' \
'LUA_INCDIR=${SYSROOTS}${include}' \
'INST_LIBDIR=${D}${libdir}${luadir}' \
'INST_LUADIR=${D}${datadir}${luadir}' \
'LUA=503' \
"


EXTRA_OECONF = "LUA_DIR=${datadir}${luadir} \
LUA_EXEC_DIR=${libdir}${luadir} \
LUA_INCLUDE=${SYSROOTS}${includepath} \
libdir=${libdir}${luadir} \
"

INSTALL_FLAGS = " \
'INST_LIBDIR=${D}${libdir}${luadir}' \
'INST_LUADIR=${D}${datadir}${luadir}' \
"

do_compile () {
	${STAGING_BINDIR_NATIVE}/lua ${S}/build-aux/luke ${MAKE_FLAGS}
}

do_install() {
	${STAGING_BINDIR_NATIVE}/lua ${S}/build-aux/luke install ${INSTALL_FLAGS}
}

FILES_${PN} = "\
	${libdir}${luadir}/posix \
	${datadir}${luadir}/posix \
"

INSANE_SKIP_${PN} = "ldflags"

RDEPENDS_${PN} = "lua-stdlib lua-stdlib-normalize lua-stdlib-debug"


