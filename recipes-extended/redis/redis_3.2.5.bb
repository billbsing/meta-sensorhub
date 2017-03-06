SUMMARY = "Redis key-value store"
DESCRIPTION = "Redis is an open source, advanced key-value store."
HOMEPAGE = "http://redis.io"
SECTION = "libs"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=3c01b49fed4df1a79843688fa3f7b9d6"
DEPENDS = ""

SRC_URI = "http://download.redis.io/releases/${BP}.tar.gz \
           file://hiredis-use-default-CC-if-it-is-set.patch \
           file://lua-update-Makefile-to-use-environment-build-setting.patch \
           file://oe-use-libc-malloc.patch \
           file://redis.conf \
           file://init-redis-server \
	   file://redis.service \
"
SRC_URI[md5sum] = "d3d2b4dd4b2a3e07ee6f63c526b66b08"
SRC_URI[sha256sum] = "8509ceb1efd849d6b2346a72a8e926b5a4f6ed3cc7c3cd8d9f36b2e9ba085315"

# inherit autotools-brokensep
inherit autotools-brokensep pkgconfig gettext systemd

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

