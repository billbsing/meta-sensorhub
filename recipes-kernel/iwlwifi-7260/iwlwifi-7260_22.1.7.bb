DESCRIPTION = "iwlwifi is the wireless driver for Intel's current wireless chips."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.iwlwifi-7260-ucode;md5=ad3069a42853ac3efd7d379d87f6088b"
HOMEPAGE = "http://wireless.kernel.org/en/users/Drivers/iwlwifi"

PR = "r0"

DEPENDS = "iwlwifi"
SRC_URI = "http://wireless.kernel.org/en/users/Drivers/iwlwifi?action=AttachFile&do=get&target=iwlwifi-7260-ucode-22.1.7.0.tgz"

SRC_URI[md5sum] = "676447d12fe373d97b2db2b9849d297b"
SRC_URI[sha256sum] = "1ecc335e2a81a4f6d19422a5e707a93ef56351030a4e95d698e064fe51ba6154"


do_install() {
        install -m 0644 iwlwifi-7260-22.ucode ${D}${FWPATH}
}



