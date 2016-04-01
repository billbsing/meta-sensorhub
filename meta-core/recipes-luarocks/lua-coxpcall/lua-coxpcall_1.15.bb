DESCRIPTION = "Coxpcall encapsulates the protected calls with a coroutine based loop, so errors can be dealed without the usual pcall/xpcall issues with coroutines."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license_${PN}.html;md5=ca5f7ed35804bc8934157fcca1ca9f96"
HOMEPAGE = "http://www.keplerproject.org/md5/"

PR = "r0"

DEPENDS = "lua"

SRC_URI = "https://github.com/keplerproject/coxpcall/archive/v1_15_0.tar.gz;name=tarball \
	http://keplerproject.github.io/coxpcall/license.html;name=license;downloadfilename=license_${PN}.html \
	file://lua-coxpcall.pc \
"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"


SRC_URI[tarball.md5sum] = "8b8e3d91f85530d119d6b74b79b4ec13"
SRC_URI[tarball.sha256sum] = "05caabb99593ec7f209b577836c865341f3052d0ebb2f20959d670808f75b535"

SRC_URI[license.md5sum] = "ca5f7ed35804bc8934157fcca1ca9f96"
SRC_URI[license.sha256sum] = "11f0f03dda1a28eca658d5e3361c15fd4d048371364aacc5f6870d77a66866c8"

S = "${WORKDIR}/coxpcall-1_15_0"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.2"

MAKE_FLAGS = "'PREFIX=${D}${prefix}' \
'LUA_LIBDIR=${D}${libdir}${luadir}' \
'LUA_DIR=${D}${datadir}${luadir}' \
'LUA_VERSION_NUM=502' \
"
do_configure() {
}

do_compile () {
    oe_runmake ${MAKE_FLAGS}
}


do_install () {
    oe_runmake ${MAKE_FLAGS} install
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-coxpcall.pc ${D}${libdir}/pkgconfig/
}

FILES_${PN} = "${datadir}${luadir}/coxpcall.lua \
"

