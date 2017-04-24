FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://writemac.service"

do_install_append() {
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/writemac.service ${D}${systemd_unitdir}/system/
}

