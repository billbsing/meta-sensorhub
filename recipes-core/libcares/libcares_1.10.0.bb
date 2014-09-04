DESCRIPTION = "c-ares is a C library for asynchronous DNS requests (including name resolves)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license.html;beginline=21;endline=31md5=fdc2f4a452f524d38b6cf6edcfd91798"
HOMEPAGE = "http://c-ares.haxx.se/"


inherit autotools


PR = "r0"

SRC_URI = "https://github.com/bagder/c-ares/archive/cares-1_10_0.tar.gz \
	http://c-ares.haxx.se/license.html \
        file://libcares.pc \
"

SRC_URI[md5sum] = "b666df149eaf6070ea00364da3bec775"
SRC_URI[sha256sum] = "fe918a070138beddfcce5d11be205800217e6b9a28d97e0b5ad9c890f671ff32"

S = "${WORKDIR}/c-ares-cares-1_10_0"


