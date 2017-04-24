DESCRIPTION = "A Lua client library for the redis key value storage system."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=06905b79316f82212a4b6722d6a5f437"
HOMEPAGE = "https://github.com/nrk/redis-lua"

PR = "rc1"

DEPENDS = "lua"

SRC_URI = "https://github.com/nrk/redis-lua/archive/master.tar.gz \
	file://lua-redis.pc \
"

SRC_URI[md5sum] = "874fdf475e2d9e2195606f2dc46167af"
SRC_URI[sha256sum] = "e9b7796d1a632d2d0db45e71ac0e64d2243dd4ec30dc0b1584bf19fbdd3c02eb"

S = "${WORKDIR}/redis-lua-master"
luadir = "/lua/5.2"

do_install () {
    install -d ${D}${datadir}${luadir}
    install -m 0644 ${S}/src/redis.lua ${D}${datadir}${luadir}

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua-redis.pc ${D}${libdir}/pkgconfig/

}

FILES_${PN} = "${datadir}${luadir}/redis.lua \
"


