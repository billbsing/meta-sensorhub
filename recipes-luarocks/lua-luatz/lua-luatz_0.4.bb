DESCRIPTION = "This is a lua library for time and date manipulation"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=1e69b2305b725c096906fb462b1295ac"
HOMEPAGE = "https://github.com/daurnimator/luatz"

PR = "1"

DEPENDS = "lua"

SRC_URI = "https://github.com/daurnimator/luatz/archive/v${PV}-${PR}.tar.gz \
	file://lua-luatz.pc \
"

S = "${WORKDIR}/luatz-${PV}-${PR}"

SRC_URI[md5sum] = "452a7b111cc54252840b279c9e0cf5f1"
SRC_URI[sha256sum] = "9cd9fd1f3ba8f2eaa8613aab18a3726d290601f93a1c278d997a3aac5d026ae3"

luadir = "/lua/5.3"

do_install () {
    install -d ${D}${datadir}${luadir}/luatz
    install -m 0644 ${S}/luatz/*.lua ${D}${datadir}${luadir}/luatz

}

FILES_${PN} = "${datadir}${luadir}/luatz \
	${datadir}${luadir}/luatz/*.lua \
"


RDEPEND="lua"

