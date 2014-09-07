DESCRIPTION = "Coxpcall encapsulates the protected calls with a coroutine based loop, so errors can be dealed without the usual pcall/xpcall issues with coroutines."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license_${PN}.html;md5=7a1ac58b3e462d7a12009cdf26db8858"
HOMEPAGE = "http://www.keplerproject.org/md5/"

PR = "r0"

DEPENDS = "lua"

SRC_URI = "https://github.com/keplerproject/coxpcall/archive/v1_15_0.tar.gz;name=tarball \
	http://keplerproject.github.io/coxpcall/license.html;name=license;downloadfilename=license_${PN}.html \
	file://lua-coxpcall.pc \
"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"


SRC_URI[md5sum] = "7a1ac58b3e462d7a12009cdf26db8858"
SRC_URI[sha256sum] = "4447c75259047a54f3753f7838afaa20adb54f2e6feb170b4e1bb50706f9ed90"

SRC_URI[license.md5sum] = "7a1ac58b3e462d7a12009cdf26db8858"
SRC_URI[license.sha256sum] = "4447c75259047a54f3753f7838afaa20adb54f2e6feb170b4e1bb50706f9ed90"


S = "${WORKDIR}/coxpcall-1_15_0"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.2"

MAKE_FLAGS = "'PREFIX=${D}${prefix}' \
'LUA_LIBDIR=${D}${libdir}${luadir}' \
'LUA_DIR=${D}${datadir}${luadir}' \
'LUA_VERSION_NUM=502' \
"


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

