DESCRIPTION = "This is a lua library for time and date manipulation"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=a48c000cda84a128dce71240c7efcb1e"
HOMEPAGE = "https://github.com/daurnimator/luatz"

PR = "1"

DEPENDS = "lua"

SRC_URI = "https://github.com/daurnimator/luatz/archive/v${PV}.tar.gz \
	file://lua-luatz.pc \
"

S = "${WORKDIR}/luatz-${PV}"

SRC_URI[md5sum] = "81e43a78e5626a2d27c447823e8542ab"
SRC_URI[sha256sum] = "4a3d6c0c597a772c5a785a6cf8e198a94ab3c42b45199c6fb1b8140490e4459d"

luadir = "/lua/5.2"

do_install () {
    install -d ${D}${datadir}${luadir}/luatz
    install -m 0644 ${S}/luatz/*.lua ${D}${datadir}${luadir}/luatz

}

FILES_${PN} = "${datadir}${luadir}/luatz \
	${datadir}${luadir}/luatz/*.lua \
"


RDEPEND="lua"

