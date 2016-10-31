RDEPENDS_${PN} = "lighttpd-module-fastcgi"


FILESEXTRAPATHS_prepend := "${THISDIR}/files:"


SRC_URI += "file://${PN}-ipv4.conf \
	   file://${PN}-ipv6.conf \
"


do_install_append() {
    rm -rf ${D}${sysconfdir}/lighttpd.d
    rm -rf ${D}/www/logs
    rm -rf ${D}/www/vars
    mv ${D}/www/pages ${D}/www/html

    # copy the firewall configuration fragments in place
    install -d ${D}${systemd_unitdir}/system/${PN}.socket.d
    install -m 0644 ${WORKDIR}/${PN}-ipv4.conf ${D}${systemd_unitdir}/system/${PN}.socket.d
    if ${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', 'true', 'false', d)}; then
        install -m 0644 ${WORKDIR}/${PN}-ipv6.conf ${D}${systemd_unitdir}/system/${PN}.socket.d
    fi
}

FILES_${PN} += "${systemd_unitdir}/system/${PN}.socket.d/*.conf"
