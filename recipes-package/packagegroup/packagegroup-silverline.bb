DESCRIPTION = "Slverline Package Group"

inherit packagegroup

PACKAGES = "\
    packagegroup-silverline \
"

RDEPENDS_packagegroup-silverline = "\
    opkg \
    lighttpd \
    redis \
    tzdata \
    cronie \
    silverline \
"
#    avahi  
