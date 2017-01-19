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
    avahi \
    silverline \
"

#     zipctl-sigma 
#    zipgateway-sigma 
