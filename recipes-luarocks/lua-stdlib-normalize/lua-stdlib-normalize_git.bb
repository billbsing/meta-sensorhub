DESCRIPTION = "General Lua Libraries"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=bbc0740b409d4a3570a06f2228971d0f"
HOMEPAGE = "https://github.com/lua-stdlib/normalize"

PR = "r0"

# SRCREV = "05eda33e248150551d7d7c4196d5e0913d5f258b"
SRCREV = "${AUTOREV}"


DEPENDS = "lua"

SRC_URI = "git://github.com/lua-stdlib/normalize.git"

# inherit autotools

SRC_URI[md5sum] = "e25438b85d7bc8eb1d49da526a62bd41"
SRC_URI[sha256sum] = "221d9098b4e599ab452bb0014f9edf466a46c3d813e87024767bcedc17b4c3e7"

S = "${WORKDIR}/git"

luadir = "/lua/5.3"
LUA_VERSION="5.3"

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

FILES_${PN} = "${datadir}${luadir}/std/normalize"


RDEPEND="lua lua-stdlib"

