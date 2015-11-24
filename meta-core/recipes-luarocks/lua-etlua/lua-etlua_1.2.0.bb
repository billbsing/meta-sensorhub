DESCRIPTION = "Embedded templates for Lua"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=cd877d1039ebb0cfb22e2d40dbee2b09"
HOMEPAGE = "https://github.com/leafo/etlua"

PR = "1"

DEPENDS = "lua"
SRC_URI = "https://github.com/leafo/etlua/archive/v${PV}.tar.gz \
	file://lua-etlua.pc \
"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

SRC_URI[md5sum] = "2f1320bdd5a9348192a98d8073b48378"
SRC_URI[sha256sum] = "4a61ffc0226633e80c93c05badff7bcc608b3ce678e6111068083d8ec1f214ed"

S = "${WORKDIR}/etlua-${PV}"
luadir = "/lua/5.2"

do_configure() {
}

do_compile () {
}

do_install () {

    install -d ${D}${datadir}${luadir}
    install -m 0644 ${S}/etlua.lua ${D}${datadir}${luadir}

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-etlua.pc ${D}${libdir}/pkgconfig/

}

FILES_${PN} = "${datadir}${luadir}/etlua.lua"


