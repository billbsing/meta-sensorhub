

RDEPENDS_${PN} += "lighttpd-module-fastcgi"


FILESEXTRAPATHS_prepend := "${THISDIR}/files:"


SRC_URI += "file://lighttpd.service \
"

SYSTEMD_SERVICE_${PN} = "lighttpd.service"

do_install_append() {
    rm -rf ${D}${sysconfdir}/lighttpd.d
    rm -rf ${D}/www/logs
    rm -rf ${D}/www/vars
    mv ${D}/www/pages ${D}/www/html

    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/lighttpd.service ${D}/${systemd_unitdir}/system/
    rm -rf ${D}/${sysconfdir}/lighttpd.conf
}

FILES_${PN} += "${systemd_unitdir}/system/lighttpd.service \
"
