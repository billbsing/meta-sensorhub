DESCRIPTION = "Embedded templates for Lua"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=91d249bf7fbefd57348cd7fab61adf2e"
HOMEPAGE = "https://github.com/leafo/etlua"

PR = "1"

DEPENDS = "lua"
SRC_URI = "https://github.com/leafo/etlua/archive/v${PV}.tar.gz;downloadfilename=etlua-${PV}.tar.gz \
	file://lua-etlua.pc \
"

SRC_URI[md5sum] = "37c4480a1bc35319c32fc0a06d532fb2"
SRC_URI[sha256sum] = "66e602779ebf9ad9ef7624fea6bcffe9ecc540691144f7f25fb97062df7a5994"

S = "${WORKDIR}/etlua-${PV}"
luadir = "/lua/5.3"

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


