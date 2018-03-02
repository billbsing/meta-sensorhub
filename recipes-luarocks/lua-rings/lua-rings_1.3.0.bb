DESCRIPTION = "Create new Lua states from within Lua"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license_${PN}.html;md5=181770fbb1683ac3e7ef130c0873599f"
HOMEPAGE = "http://keplerproject.github.com/rings"

PR = "r0"

DEPENDS = "lua"

SRC_URI = "https://github.com/keplerproject/rings/archive/v_1_3_0.tar.gz;name=tarball \
	http://keplerproject.github.io/rings/license.html;name=license;downloadfilename=license_${PN}.html \
	file://lua-rings.pc \
"

SRC_URI[tarball.md5sum] = "f4182f03934d40cbb40d80922febc1b1"
SRC_URI[tarball.sha256sum] = "a1101c2fc8d5b943e66b70ce5d44d8a605567c98dc47fbdcb6722bdc81eabefe"

SRC_URI[license.md5sum] = "181770fbb1683ac3e7ef130c0873599f"
SRC_URI[license.sha256sum] = "142c565bf110f96cc1a17209ab57eb343cdfbe5ecb29bf68ed89a94a63d273c7"


S = "${WORKDIR}/rings-v_1_3_0"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.3"

MAKE_FLAGS = "'PREFIX=${D}${prefix}' \
'LUA_LIBDIR=${D}${libdir}${luadir}' \
'LUA_DIR=${D}${datadir}${luadir}' \
'LUA_VERSION_NUM=503' \
'CFLAGS=-fPIC'  \
'LIB_OPTION = -shared -fPIC' \
"


do_compile () {
    oe_runmake clean
    oe_runmake ${MAKE_FLAGS}
}


do_install () {
    oe_runmake ${MAKE_FLAGS} install
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-rings.pc ${D}${libdir}/pkgconfig/
}

INSANE_SKIP_${PN} = "ldflags"
INSANE_SKIP_${PN}-dev = "ldflags"

FILES_${PN} = "${libdir}${luadir}/rings.so \
	${datadir}${luadir}/stable.lua \
"
