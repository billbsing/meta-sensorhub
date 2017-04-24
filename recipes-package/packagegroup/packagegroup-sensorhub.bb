DESCRIPTION = "Sensorhub Package Group"

inherit packagegroup

PACKAGES = "\
    packagegroup-sensorhub \
"

RDEPENDS_packagegroup-sensorhub = "\
    opkg \
    lighttpd \
    redis \
    tzdata \
    sensorhub \ 
    cronie \
    first-boot  \
    avahi \
    python \
    python-redis \
"

#    python-bluepy 
