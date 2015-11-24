DESCRIPTION = "iwlwifi is the wireless driver for Intel's current wireless chips."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.iwlwifi-7260-ucode;md5=88217f6050dece78d324915d5ecf0dfa"
HOMEPAGE = "http://wireless.kernel.org/en/users/Drivers/iwlwifi"

PR = "r0"

SRC_URI = "file://iwlwifi-7260-ucode-22.1.7.0.tgz"

SRC_URI[md5sum] = "676447d12fe373d97b2db2b9849d297b"
SRC_URI[sha256sum] = "1ecc335e2a81a4f6d19422a5e707a93ef56351030a4e95d698e064fe51ba6154"

FWPATH = "/lib/firmware"
S = "${WORKDIR}/iwlwifi-7260-ucode-${PV}.0"

do_install() {
	install -d ${D}${FWPATH}
        install -m 0644 ${S}/iwlwifi-7260-7.ucode ${D}${FWPATH}
}


FILES_${PN} = "${FWPATH}/iwlwifi-7260-7.ucode"


