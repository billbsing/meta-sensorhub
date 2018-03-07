DESCRIPTION = "General Lua Libraries"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=390f19ee9b5c2dc3104e5bc5ce8ce40c"
HOMEPAGE = "https://github.com/lua-stdlib/_debug"

PR = "r0"


DEPENDS = "lua"

SRC_URI = "https://github.com/lua-stdlib/_debug/archive/v${PV}.tar.gz \
"

SRC_URI[md5sum] = "5ad826c15fa7ebe674321183f75833ac"
SRC_URI[sha256sum] = "40697e8b0177647f828fb86627449b40c6df2685e82fa99e9dc772201758841d"


S = "${WORKDIR}/_debug-${PV}"

luadir = "/lua/5.3"

do_cleanall() {
}

do_configure() {
}

do_compile() {
}

do_install () {

    install -d ${D}${datadir}${luadir}/std/_debug
    install -m 0644 ${S}/lib/std/_debug/*.lua ${D}${datadir}${luadir}/std/_debug
    echo 'return "ebug hints library / ${PV}"' > ${D}${datadir}${luadir}/std/_debug/version.lua
}

FILES_${PN} = "${datadir}${luadir}/std/_debug \
"


RDEPEND="lua lua-stdlib"

