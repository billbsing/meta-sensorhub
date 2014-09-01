DESCRIPTION = "Create new Lua states from within Lua"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license.html;beginline=61;endline=107;md5=b64be35c72f82af37b05fb2f72b0284f"
HOMEPAGE = "http://keplerproject.github.com/rings"

PR = "r0"

DEPENDS = "lua"

SRC_URI = "https://github.com/keplerproject/rings/archive/v_1_3_0.tar.gz;name=tarball \
	http://keplerproject.github.io/rings/license.html;name=license \
	file://lua-rings.pc \
"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"


SRC_URI[tarball.md5sum] = "f4182f03934d40cbb40d80922febc1b1"
SRC_URI[tarball.sha256sum] = "a1101c2fc8d5b943e66b70ce5d44d8a605567c98dc47fbdcb6722bdc81eabefe"

SRC_URI[license.md5sum] = "a35821c342d3ddd417297923a254aefd"
SRC_URI[license.sha256sum] = "250ed109a20a48283c0aed493cd346388ce47846ae7d9e11f18a50249e2481cb"

S = "${WORKDIR}/rings-v_1_3_0"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.2"

MAKE_FLAGS = "'PREFIX=${D}${prefix}' \
'LUA_LIBDIR=${D}${libdir}${luadir}' \
'LUA_DIR=${D}${datadir}${luadir}' \
'LUA_VERSION_NUM=502' \
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

FILES_${PN} = "${libdir}${luadir}/rings.so \
	${datadir}${luadir}/stable.lua \
"
