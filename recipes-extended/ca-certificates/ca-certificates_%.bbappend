


FILESEXTRAPATHS_append := "${THISDIR}/files:"

SRC_URI += "file://ca-certificates.conf \
file://isrgrootx1.crt"

PR = "r2"


do_install_append() {
    install -d ${D}${datadir}/ca-certificates
    install -d ${D}${sysconfdir}

	install ${WORKDIR}/isrgrootx1.crt ${D}${datadir}/ca-certificates
	install ${WORKDIR}/ca-certificates.conf ${D}${sysconfdir}/ca-certificates.conf 
}


