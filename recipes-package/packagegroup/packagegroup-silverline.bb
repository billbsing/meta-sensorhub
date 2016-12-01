DESCRIPTION = "Slverline Package Group"

inherit packagegroup

PACKAGES = "\
    packagegroup-silverline \
"

RDEPENDS_packagegroup-silverline = "\
    opkg \
    lighttpd \
    redis \
    avahi \
    tzdata \
    avahi \
    zipgateway-sigma \
    silverline \
"

#     zipctl-sigma 
#     cronie\
