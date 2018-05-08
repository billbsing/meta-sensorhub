DESCRIPTION = "General Lua Libraries"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=00aa136d1f9efcc612951f8263e10f34"
HOMEPAGE = "https://github.com/lua-stdlib/normalize"

PR = "r0"

# SRCREV = "05eda33e248150551d7d7c4196d5e0913d5f258b"
SRCREV = "${AUTOREV}"


DEPENDS = "lua"

SRC_URI = "https://github.com/lua-stdlib/normalize/archive/v${PV}.tar.gz"

# inherit autotools

SRC_URI[md5sum] = "f7dbd9f3ba95a4e8fd7821fb6c1a0c57"
SRC_URI[sha256sum] = "4ab752a0eeb9e63dbdf54fdc63e33108daca4a728119fd0d68f639e3fe3e6561"

S = "${WORKDIR}/git"

luadir = "/lua/5.3"

do_cleanall() {
}

do_configure() {
}

do_compile() {
}

do_install () {

    install -d ${D}${datadir}${luadir}/std/normalize
    install -m 0644 ${S}/lib/std/normalize/*.lua ${D}${datadir}${luadir}/std/normalize
    echo 'return "Normalized Lua Functions / ${PV}"' > ${D}${datadir}${luadir}/std/normalize/version.lua
}

FILES_${PN} = "${datadir}${luadir}/std/normalize \
"


RDEPEND="lua lua-stdlib"

