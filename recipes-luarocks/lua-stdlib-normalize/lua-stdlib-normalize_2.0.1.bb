DESCRIPTION = "General Lua Libraries"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=00aa136d1f9efcc612951f8263e10f34"
HOMEPAGE = "https://github.com/lua-stdlib/normalize"

PR = "r0"

# SRCREV = "05eda33e248150551d7d7c4196d5e0913d5f258b"
SRCREV = "${AUTOREV}"


DEPENDS = "lua"

SRC_URI = "git://github.com/lua-stdlib/normalize.git;branch=release-v${PV};protocol=ssh \
"

# inherit autotools

SRC_URI[md5sum] = "4b41ea135794d27172904640f7565fe0"
SRC_URI[sha256sum] = "71c4a3d929b22dba4b915c7526039a643326333a77b0d6d4e38e8847785181d0"

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

