CRIPTION = "Lua is a powerful light-weight programming language designed \
for extending applications."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://doc/readme.html;beginline=364;endline=398;md5=c1fc795345486008cd84355ff82fc63e"
HOMEPAGE = "http://www.lua.org/"

PR = "r0"

DEPENDS = "readline"

SRC_URI = " http://www.lua.org/ftp/lua-5.3.4.tar.gz \
	file://00001-setup.patch \
	file://lua.pc \
"

SRC_URI[md5sum] = "53a9c68bcc0eda58bdc2095ad5cdfc63"                                                                                                                                      
SRC_URI[sha256sum] = "f681aa518233bc407e23acf0f5887c884f17436f000d453b2491a9f11a52400c"

inherit pkgconfig binconfig 

# nativesdk

UCLIBC_PATCHES += "file://uclibc-pthread.patch"
SRC_URI_append_libc-uclibc = "${UCLIBC_PATCHES}"

TARGET_CC_ARCH += " -fPIC ${LDFLAGS}"
EXTRA_OEMAKE = "'CC=${CC} -fPIC' 'MYCFLAGS=${CFLAGS} -DLUA_USE_LINUX -DLUA_32BITS -DLUA_COMPAT_BITLIB -fPIC' MYLDFLAGS='${LDFLAGS}'"

SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
luadir = "/lua/5.3"


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
    ln -sf ${bindir}/lua ${D}${bindir}/lua5.3

# copy over files for later builds
    install -d ${STAGING_LIBDIR}${luadir}
    install -m 0644 ${S}/src/*.a ${STAGING_LIBDIR}${luadir}
}

BBCLASSEXTEND = "native"

