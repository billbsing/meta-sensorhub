DESCRIPTION = "Slverline Package Group"

inherit packagegroup

PACKAGES = "\
    packagegroup-silverline \
"

RDEPENDS_packagegroup-silverline = "\
    opkg \
    avahi \
    lighttpd \
    redis \
    cronie \
    tzdata \
    zipctl-sigma \
    zipgateway-sigma \
    silverline \
"

