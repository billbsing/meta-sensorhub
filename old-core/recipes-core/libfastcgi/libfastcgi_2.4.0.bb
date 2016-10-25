DESCRIPTION = "FastCGI Dev Kit Library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.TERMS;md5=e3aacac3a647af6e7e31f181cda0a06a"
HOMEPAGE = "http://www.fastcgi.com/"


inherit autotools

# DEPENDS = "libfastcgi-dev"

PR = "r0"

SRC_URI = "https://github.com/LuaDist/fcgi/archive/${PV}.tar.gz \
        file://libfastcgi.pc \
	file://fcgio.cpp.patch \
	file://libfcgi.patch \
	file://acinclude.patch \
"

# file://configure.in.patch 

SRC_URI[md5sum] = "426e1325c3df2b91eee2dba1988d9bf0"
SRC_URI[sha256sum] = "6596975a3bd3ddd1995cdeb66a503d2b5d7277f71038511634d7d3609e4588bb"


S = "${WORKDIR}/fcgi-${PV}"

EXTRA_OECONF = "LIBS=-lm"

do_configure_prepend() {
   cp ${S}/configure.in ${S}/configure.ac
   touch ${S}/NEWS
   touch ${S}/AUTHORS
   touch ${S}/ChangeLog

}




