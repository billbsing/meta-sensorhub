CRIPTION = "Lua is a powerful light-weight programming language designed \
for extending applications."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://doc/readme.html;beginline=364;endline=398;md5=3a31f6d639c9306486db2a0dd4aeafc0"
HOMEPAGE = "http://www.lua.org/"

PR = "r0"

DEPENDS = "readline"

SRC_URI = " http://www.lua.org/ftp/lua-5.2.3.tar.gz \
           file://lua.pc \
"

SRC_URI[md5sum] = "dc7f94ec6ff15c985d2d6ad0f1b35654"
SRC_URI[sha256sum] = "13c2fb97961381f7d06d5b5cea55b743c163800896fd5c5e2356201d3619002d"

inherit pkgconfig binconfig

UCLIBC_PATCHES += "file://uclibc-pthread.patch"
SRC_URI_append_libc-uclibc = "${UCLIBC_PATCHES}"

TARGET_CC_ARCH += " -fPIC ${LDFLAGS}"
EXTRA_OEMAKE = "'CC=${CC} -fPIC' 'MYCFLAGS=${CFLAGS} -DLUA_USE_LINUX -fPIC' MYLDFLAGS='${LDFLAGS}'"

SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.2"


do_configure_prepend() {
    sed -i -e s:/usr/local:${prefix}:g src/luaconf.h
}

do_compile () {
    oe_runmake linux
}


do_install () {
    oe_runmake \
        'INSTALL_TOP=${D}${prefix}' \
	'INSTALL_MAN=${D}${mandir}/man1' \
        install

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/lua.pc ${D}${libdir}/pkgconfig/

# copy over files for later builds
    install -m 0644 ${S}/src/*.a ${STAGING_LIBDIR}${luadir}
}

BBCLASSEXTEND = "native"

