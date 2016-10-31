DESCRIPTION = "Slverline Package Groups"

inherit packagegroup

PACKAGES = "\
    packagegroup-silverline \
"

RDEPENDS_packagegroup-silverline = "\
    opkg \
    avahi \
    lighttpd \
    redis \
    silverline \
"

