SUMMARY = "Redis key-value store"
DESCRIPTION = "Redis is an open source, advanced key-value store."
HOMEPAGE = "http://redis.io"
SECTION = "libs"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=3c01b49fed4df1a79843688fa3f7b9d6"
DEPENDS = ""

SRC_URI = "http://download.redis.io/releases/${BP}.tar.gz \
           file://lua-update-Makefile-to-use-environment-build-setting.patch \
           file://oe-use-libc-malloc.patch \
           file://redis.conf \
           file://init-redis-server \
	   file://redis.service \
"

#    file://hiredis-use-default-CC-if-it-is-set.patch 



SRC_URI[md5sum] = "c75b11e4177e153e4dc1d8dd3a6174e4"
SRC_URI[sha256sum] = "ff0c38b8c156319249fec61e5018cf5b5fe63a65b61690bec798f4c998c232ad"

# inherit autotools-brokensep
inherit autotools-brokensep pkgconfig gettext systemd

EXTRA_OEMAKE = "'PREFIX=${D}${prefix}' \
'CC=${CC}' \
'CXX=${CXX}' \
"


SYSTEMD_SERVICE_${PN} = "redis.service"


do_install() {
    export PREFIX=${D}/${prefix}
    oe_runmake install
#    install -d ${D}/${sysconfdir}/redis
#    install -m 0644 ${WORKDIR}/redis.conf ${D}/${sysconfdir}/redis/redis.conf

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/redis.service ${D}${systemd_unitdir}/system
    install -d ${D}/var/lib/redis/
}

CONFFILES_${PN} = "${sysconfdir}/redis/redis.conf"

FILES_${PN} += "${systemd_unitdir}/system/redis.service"

