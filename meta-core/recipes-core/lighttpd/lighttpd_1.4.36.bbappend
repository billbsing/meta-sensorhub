RDEPENDS_${PN} += " \
               lighttpd-module-fastcgi \
"


FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

do_install_append() {
	rmdir ${D}${sysconfdir}/lighttpd.d
	mv ${D}/www/pages ${D}/www/html
	rm -f ${D}/www/logs
	ln -sf ${localstatedir}/log/lighttpd ${D}/www/logs
}


