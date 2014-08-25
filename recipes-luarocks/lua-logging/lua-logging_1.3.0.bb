DESCRIPTION = "A simple API to use logging features"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=8cea27c56ce851a51e816051857e2ca5"
HOMEPAGE = "http://neopallium.github.com/lualogging/"

PR = "1"

RDEPENDS_${PN} = "lua"
DEPENDS_${PN} = "lua lua-socket"

SRC_URI = "https://github.com/Neopallium/lualogging/archive/v${PV}.tar.gz \
	file://lua-logging.pc \
"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

SRC_URI[md5sum] = "05615b083f25b7ee74d53eb3827a8583"
SRC_URI[sha256sum] = "a69fc551b4c95ade3f71bd8e4013a3cc0eb30d86b218b69d90a3ba0adcc64d96"

S = "${WORKDIR}/lualogging-${PV}"
luadir = "/lua/5.2"

do_install () {


    install -d ${D}${datadir}${luadir}/logging

    install -m 0644 ${S}/src/*.lua ${D}${datadir}${luadir}
    install -m 0644 ${S}/src/logging/*.lua ${D}${datadir}${luadir}/logging

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-logging.pc ${D}${libdir}/pkgconfig/

}

FILES_${PN} = "${datadir}${luadir}/logging \
	${datadir}${luadir}/logging.lua \
	${datadir}${luadir}/logging/* \
"



