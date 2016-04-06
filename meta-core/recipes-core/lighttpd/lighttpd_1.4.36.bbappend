RDEPENDS_${PN} += " \
               lighttpd-module-fastcgi \
"

inherit useradd

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"


USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--system --home /bin/false --no-create-home \
                       --user-group http"

do_install_append() {
	rmdir ${D}${sysconfdir}/lighttpd.d
	chown -R http:http ${D}/www
	mv ${D}/www/pages ${D}/www/html
	rm -f ${D}/www/logs
	ln -sf ${localstatedir}/log/lighttpd ${D}/www/logs
}


