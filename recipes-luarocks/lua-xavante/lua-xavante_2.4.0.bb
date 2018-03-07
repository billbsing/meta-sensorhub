DESCRIPTION = "Lua Web Server Library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license_${PN}.html;md5=92b7419e9e8d7ba3dff3c41521a62537"
HOMEPAGE = "http://keplerproject.github.com/xavante"

PR = "r0"

DEPENDS = "lua"

SRC_URI = "https://github.com/keplerproject/xavante/archive/v${PV}.tar.gz;name=tarball;downloadfilename=xavante_${PV}.tar.gz \
	http://keplerproject.github.io/xavante/license.html;name=license;downloadfilename=license_${PN}.html \
	file://lua-xavante.pc \
"

SRC_URI[tarball.md5sum] = "73948f18ac884371da1ad2b17ebf2b70"
SRC_URI[tarball.sha256sum] = "0e4f49dd96cb092cd5d80cc66a5204dcb22a14f80897a121f2f0d1dceb1c7ba5"

SRC_URI[license.md5sum] = "92b7419e9e8d7ba3dff3c41521a62537"
SRC_URI[license.sha256sum] = "da825b72900a564d11240c8d71286fe6f3a91e0ce96a27270f1d1bce68395cf3"

S = "${WORKDIR}/xavante-${PV}"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.3"

MAKE_FLAGS = "'PREFIX=${D}${prefix}' \
'LUA_LIBDIR=${D}${libdir}${luadir}' \
'LUA_DIR=${D}${datadir}${luadir}' \
'LUA_VERSION_NUM=503' \
"


do_compile () {
    oe_runmake ${MAKE_FLAGS}
}


do_install () {
    
    oe_runmake ${MAKE_FLAGS} install
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-xavante.pc ${D}${libdir}/pkgconfig/
}

FILES_${PN} = "${datadir}${luadir}/xavante.lua \
	${datadir}${luadir}/sajax.lua \
	${datadir}${luadir}/xavante/*.lua \
"

