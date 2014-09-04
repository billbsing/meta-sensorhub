DESCRIPTION = "FastCGI Dev Kit Library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.TERMS;md5=e3aacac3a647af6e7e31f181cda0a06a"
HOMEPAGE = "http://www.fastcgi.com/"


inherit autotools

DEPENDS = "libfastcgi-dev"

PR = "r0"

SRC_URI = "http://www.fastcgi.com/dist/fcgi.tar.gz \
        file://libfastcgi.pc \
	file://fcgio.cpp.patch \
	file://configure.in.patch \
	file://libfcgi.patch \
"

SRC_URI[md5sum] = "e79c7f4545cf1853af1e2ca3b40ab087"
SRC_URI[sha256sum] = "165604cffa37d534c348f78e4923d0f1ce4d8808b901891a9e64ebf634c4d0d5"

S = "${WORKDIR}/fcgi-2.4.1-SNAP-0311112127"

EXTRA_OECONF = "LIBS=-lm"
