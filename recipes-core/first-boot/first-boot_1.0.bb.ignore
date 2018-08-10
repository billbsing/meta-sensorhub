SUMMARY = "First boot script for Sensorhub"
LICENSE = "CLOSED"
# LICENSE = "WTFPL"

LIC_FILES_CHKSUM = "file://../COPYING.TXT;md5=086d912e268bb12027b04eac81622e3d"



SRC_URI = "file://COPYING.TXT  \
		file://first-boot.service \
		file://first_boot.sh \
"


do_compile() {
	:
}

do_install() {
   install -d ${D}${systemd_unitdir}/system
   install -m 0644 ${WORKDIR}/first-boot.service ${D}${systemd_unitdir}/system/

   install -d ${D}${bindir}
   install ${WORKDIR}/first_boot.sh ${D}${bindir}/
}

inherit systemd

SYSTEMD_PACKAGES = "${PN}"

SYSTEMD_SERVICE_${PN} = "first-boot.service"

SYSTEMD_AUTO_ENABLE = "enable"


FILES_${PN} = "${systemd_unitdir}/system/*  \
	${bindir}/*        \
"
