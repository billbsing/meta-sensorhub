DESCRIPTION = "Automatically restart SSH sessions and tunnels"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://INSTALL;beginline=1;endline=4;md5=5af55bd3329bcb507e6621117b5211de"
HOMEPAGE = "http://www.harding.motd.ca/autossh/"


inherit autotools

# DEPENDS = "libfastcgi-dev"

PR = "r0"

SRC_URI = "http://www.harding.motd.ca/autossh/autossh-${PV}.tgz  \
"

SRC_URI[md5sum] = "26520eea934f296be0783dabe7fcfd28"
SRC_URI[sha256sum] = "6fcaba6a409a46bdf832086736bb8f09d245ebce11027f41d39588a95dc7fd1d"

# S = "${WORKDIR}/autossh-${PV}"

EXTRA_OECONF = "--prefix=${D}${prefix} \
	--bindir=${D}${bindir} \
	--datadir=${D}${datadir} \
	--datarootdir=${D}${datadir} \
	--docdir=${D}${datadir}/doc/autossh \
	--mandir=${D}${mandir} \
"
do_install_append() {
	rm -rf ${D}${datadir}/examples
}

