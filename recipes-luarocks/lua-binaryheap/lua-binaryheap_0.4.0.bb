DESCRIPTION = "A binary heap (or binary tree) is a sorting algorithm"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=b9bb4c8ef43f1a88ba6a763d3280d2e5"
HOMEPAGE = "https://github.com/Tieske/binaryheap.lua"

PR = "1"
MAJOR_VERSION="${@bb.data.getVar('PV', d, 1).split('.')[0]}"
MINOR_VERSION="${@bb.data.getVar('PV', d, 1).split('.')[1]}"
TAR_NAME="version_${MAJOR_VERSION}v${MINOR_VERSION}"

DEPENDS = "lua"
SRC_URI = "https://github.com/Tieske/binaryheap.lua/archive/refs/tags/${TAR_NAME}.tar.gz \
	file://lua-binaryheap.pc \
"

SRC_URI[md5sum] = "8dc4ea3df17fc7c586693d6fb6f0a8a3"
SRC_URI[sha256sum] = "10b1b6c6f2d22560f512f9896a6672ec5ae0eea1390ff8e662be1d5d9625b438"

S = "${WORKDIR}/binaryheap.lua-${TAR_NAME}"
luadir = "/lua/5.3"

do_compile () {
}

do_install () {
    install -d ${D}${datadir}${luadir}/
    install -m 0644 ${S}/src/binaryheap.lua ${D}${datadir}${luadir}/

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-binaryheap.pc ${D}${libdir}/pkgconfig/

}

FILES_${PN} = "${datadir}${luadir}/binaryheap.lua \
"

