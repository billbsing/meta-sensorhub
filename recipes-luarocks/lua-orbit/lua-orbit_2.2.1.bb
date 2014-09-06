DESCRIPTION = "MVC for Lua Web Development"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license.html;md5=fdc2f4a452f524d38b6cf6edcfd91798"
HOMEPAGE = "http://orbit.luaforge.net"

PR = "r0"

DEPENDS = "lua lua-filesystem lua-lpeg lua-wsapi lua-xavante lua-cosmo lua-coxpcall"

SRC_URI = "https://github.com/keplerproject/orbit/archive/v${PV}.tar.gz;name=tarball \
	http://keplerproject.github.io/orbit/license.html;name=license \
	file://lua-orbit.pc \
"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

SRC_URI[tarball.md5sum] = "a0a19d22f5e01326bcc0e5a486bf3039"
SRC_URI[tarball.sha256sum] = "135960031e747c8fd552bac69eaaa8dd46e5488fbb19c6bdf33b625ba3d6b466"

SRC_URI[license.md5sum] = "43b89094d5b8a9fd2d6ed6ef87cd4dbf"
SRC_URI[license.sha256sum] = "dd7d37caed0f4ee994e9e64ea4ab292dce549bb609412c84fc47ed079275ae98"


S = "${WORKDIR}/orbit-${PV}"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.2"

MAKE_FLAGS = "'PREFIX=${D}${prefix}' \
'LUA_LIBDIR=${D}${libdir}${luadir}' \
'LUA_DIR=${D}${datadir}${luadir}' \
'LUA_VERSION_NUM=502' \
'BIN_DIR=${D}${bindir}' \
"

do_configure_prepend() {
     sed -i "s/5.1/5.2/g" ${S}/configure
     sed -i "s/51/52/g" ${S}/configure
}


do_compile () {
    oe_runmake clean
    oe_runmake ${MAKE_FLAGS}
}


do_install () {
    
    oe_runmake ${MAKE_FLAGS} install
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-orbit.pc ${D}${libdir}/pkgconfig/
}

FILES_${PN} = "${datadir}${luadir}/orbit.lua \
	${datadir}${luadir}/orbit/*.lua \
	${bindir}/orbit \
"

