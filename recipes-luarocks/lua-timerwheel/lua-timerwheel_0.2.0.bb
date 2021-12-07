DESCRIPTION = "Efficient timer for timeout related timers: fast insertion, deletion, and execution (all as O(1) implemented), but with lesser precision."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8dc41afebab626417b8683d7b2f4f437"
HOMEPAGE = "https://github.com/Tieske/timerwheel.lua"

PR = "1"

DEPENDS = "lua"
SRC_URI = "https://github.com/Tieske/timerwheel.lua/archive/refs/tags/${PV}.tar.gz \
	file://lua-timerwheel.pc \
"

SRC_URI[md5sum] = "3c599f14269a5ee6f8923647061b38dc"
SRC_URI[sha256sum] = "b81250f0ea0e2eebecd995180906c883e0d70f3f43aef3b7bfd24b9cd5f77a22"

S = "${WORKDIR}/timerwheel.lua-${PV}"
luadir = "/lua/5.3"

do_compile () {
}

do_install () {
    install -d ${D}${datadir}${luadir}/
    install -m 0644 ${S}/src/timerwheel.lua ${D}${datadir}${luadir}/

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-timerwheel.pc ${D}${libdir}/pkgconfig/

}

FILES_${PN} = "${datadir}${luadir}/timerwheel.lua \
"

