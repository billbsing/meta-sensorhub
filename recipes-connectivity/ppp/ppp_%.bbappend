#  add ppp files for connection to HUAWEI modem


FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "\
	file://options \
	file://mu609 \
	file://mu609-chat-connect \
	file://UC20 \
	file://wcdma-chat-connect \
	file://wcdma-chat-disconnect \
	file://apn \
	file://08setupdns \
	file://92removedns \
"
# S = "${WORKDIR}"

inherit systemd

do_install_append() {
	install -d ${D}${sysconfdir}/ppp/peers
	install -m 0755 ${WORKDIR}/options ${D}${sysconfdir}/ppp/options
	install -m 0755 ${WORKDIR}/apn ${D}${sysconfdir}/ppp/apn
	install -m 0755 ${WORKDIR}/UC20 ${D}${sysconfdir}/ppp/peers/UC20
	install -m 0755 ${WORKDIR}/mu609 ${D}${sysconfdir}/ppp/peers/mu609
	install -m 0755 ${WORKDIR}/mu609-chat-connect ${D}${sysconfdir}/ppp/peers/mu609-chat-connect
	install -m 0755 ${WORKDIR}/wcdma-chat-connect ${D}${sysconfdir}/ppp/peers/wcdma-chat-connect
	install -m 0755 ${WORKDIR}/wcdma-chat-disconnect ${D}${sysconfdir}/ppp/peers/wcdma-chat-disconnect
	rm -rf ${D}${sysconfdir}/ppp/peers/provider
	ln -s mu609 ${D}${sysconfdir}/ppp/peers/provider

	install -d ${D}${sysconfdir}/ppp/ip-up.d
	install -m 0755 ${WORKDIR}/08setupdns ${D}${sysconfdir}/ppp/ip-up.d
	install -d ${D}${sysconfdir}/ppp/ip-down.d
	install -m 0755 ${WORKDIR}/92removedns ${D}${sysconfdir}/ppp/ip-down.d
}


# SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "ppp@mu609.service"
SYSTEMD_AUTO_ENABLE = "enable"


FILES_${PN} += "\
        ${sysconfdir}/ppp/options \
        ${sysconfdir}/ppp/apn \
        ${sysconfdir}/ppp/peers/UC20 \
        ${sysconfdir}/ppp/peers/mu609   \
        ${sysconfdir}/ppp/peers/mu609-chat-connect \
        ${sysconfdir}/ppp/peers/wcdma-chat-connect \
        ${sysconfdir}/ppp/peers/wcdma-chat-disconnect \
        ${sysconfdir}/ppp/peers/provider \
		${sysconfdir}/ppp/ip-up.d/08setupdns  \
		${sysconfdir}/ppp/ip-down.d/92removedns  \
"

