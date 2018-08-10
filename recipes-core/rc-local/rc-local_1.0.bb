SUMMARY = "Setup rc.local for Sensorhub"
LICENSE = "CLOSED"
# LICENSE = "WTFPL"

# LIC_FILES_CHKSUM = "file://COPYING.TXT;md5=086d912e268bb12027b04eac81622e3d"


# SRC_URI = "file://COPYING.TXT  


PR = "r3"

RC_LOCAL_RELEASE ?= "1"

SRC_URI = "file://rc.local.release \
			file://rc.local.test \
			file://first_boot.sh \
			file://setup_sensorhub_conf.sh \
			file://setup_network.sh \
"


do_compile() {
	:
}

do_install() {
	install -d ${D}${sysconfdir}
	install -d ${D}${bindir}

	if [ ${RC_LOCAL_RELEASE} = 1 ]; then
	   install ${WORKDIR}/rc.local.release ${D}${sysconfdir}/rc.local
	else
	   install ${WORKDIR}/rc.local.test ${D}${sysconfdir}/rc.local
	   install -m 0744 ${WORKDIR}/setup_sensorhub_conf.sh ${D}${bindir}
	fi


	install -d ${D}${bindir}
	install -m 0744 ${WORKDIR}/first_boot.sh ${D}${bindir}
    install -m 0744 ${WORKDIR}/setup_network.sh ${D}${bindir}

}

# RDEPENDS_${PN} = "dnsmasq"

FILES_${PN} = "${sysconfdir} \
		${bindir}		\
"

