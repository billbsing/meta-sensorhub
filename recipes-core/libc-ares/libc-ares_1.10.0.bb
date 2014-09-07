DESCRIPTION = "c-ares is a C library for asynchronous DNS requests (including name resolves)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license_${PN}.html;beginline=21;endline=31;md5=e5dce34f1b10215f340d837ffff33812"
HOMEPAGE = "http://c-ares.haxx.se/"


inherit autotools


PR = "r0"

SRC_URI = "https://github.com/bagder/c-ares/archive/cares-1_10_0.tar.gz;name=tarball \
	http://c-ares.haxx.se/license.html;name=license;downloadfilename=license_${PN}.html \
        file://libc-ares.pc \
"

SRC_URI[tarball.md5sum] = "b666df149eaf6070ea00364da3bec775"
SRC_URI[tarball.sha256sum] = "fe918a070138beddfcce5d11be205800217e6b9a28d97e0b5ad9c890f671ff32"

SRC_URI[license.md5sum] = "7d708467e3a377daaef93c644fd494bf"
SRC_URI[license.sha256sum] = "aa259141af2018178b832960800be9eaec1b04b18b54b55748e6735396962597"

S = "${WORKDIR}/c-ares-cares-1_10_0"


