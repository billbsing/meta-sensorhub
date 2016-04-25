RDEPENDS_${PN} += " \
               lighttpd-module-fastcgi \
"


FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

do_install_append() {
	rm -rf ${D}${sysconfdir}/lighttpd.d
	rm -rf ${D}/www/logs
	rm -rf ${D}/www/vars
	mv ${D}/www/pages ${D}/www/html
}


