DESCRIPTION = "A highly customizable test library for Lua that allows declarative tests with nested contexts"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;beginline=174;md5=63302b5a0128df0d19a2e107cf82a0cc"
HOMEPAGE = "http://norman.github.com/telescope/"

PR = "rc1"

DEPENDS = "lua"

SRC_URI = "https://github.com/norman/telescope/archive/${PV}.tar.gz \
	file://lua-telescope.pc \
"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"


SRC_URI[md5sum] = "315a6ab6dfa654c18c7d9d30d2e17802"
SRC_URI[sha256sum] = "c285309830495a29d6248e3463a260ca71cd738f93f43ec4ab93d3e1822089b0"


S = "${WORKDIR}/telescope-${PV}"
luadir = "/lua/5.2"

do_compile () {
}


do_install () {


    install -d ${D}${datadir}${luadir}/telescope
    install -m 0644 ${S}/telescope.lua ${D}${datadir}${luadir}
    install -m 0644 ${S}/telescope/compat_env.lua ${D}${datadir}${luadir}/telescope
    install -d ${D}${bindir}
    install -m 0644 ${S}/tsc ${D}${bindir}

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-telescope.pc ${D}${libdir}/pkgconfig/

}

FILES_${PN} = "${datadir}${luadir}/telescope.lua \
	${datadir}${luadir}/telescope/* \
	${bindir}/tsc \
"




