CRIPTION = "This is LuaRocks, a deployment and management system for Lua modules."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=21cc02223d633a3e8d629b0f1b1d468b"
HOMEPAGE = "http://www.luarocks.org/"

PR = "r0"

SRC_URI = "http://luarocks.org/releases/luarocks-${PV}.tar.gz \
	file://luarocks.pc \
"

SRC_URI[md5sum] = "0afc5fd6ee6ec6128fccda1bc559f41e"
SRC_URI[sha256sum] = "62625c7609c886bae23f8db55dba45dbb083bae0d19bf12fe29ec95f7d389ff3"

inherit pkgconfig binconfig 


SYSROOTS = "${TMPDIR}/sysroots/clanton"
do_configure() {
    ./configure --with-lua=${SYSROOTS}${prefix} --sysconfdir=${D}/etc/luarocks --lua-version=5.2  --prefix=${D}${prefix} --force-config
}

do_compile () {
    oe_runmake
}

do_install () {
    oe_runmake install
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/luarocks.pc ${D}${libdir}/pkgconfig/

}

RDEPENDS_${PN} = "lua"
