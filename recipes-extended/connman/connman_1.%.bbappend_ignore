# fix connman.service file


FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://connman.service"



do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/connman.service ${D}${systemd_unitdir}/system/
}

