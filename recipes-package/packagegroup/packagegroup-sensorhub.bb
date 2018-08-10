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
    rc-local  \
    avahi \
    python \
    python-redis \
    python-pip \
"

#    python-bluepy 
#    mosquitto-clients 
