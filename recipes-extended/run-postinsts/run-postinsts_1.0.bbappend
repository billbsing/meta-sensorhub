FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://postinst \
"



do_install_append() {

	install -d ${D}${sysconfdir}/default/
	install -m 0755 ${WORKDIR}/postinst ${D}${sysconfdir}/default


	sed -i -e 's:TimeoutSec=0:TimeoutSec=240:g' \
               ${D}${systemd_unitdir}/system/run-postinsts.service
}
