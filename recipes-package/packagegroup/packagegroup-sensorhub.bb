DESCRIPTION = "Sensorhub Package Group"

inherit packagegroup

PACKAGES = "\
    packagegroup-sensorhub \
    packagegroup-sensorhub-test \
"

RDEPENDS_packagegroup-sensorhub = "\
    opkg \
    lighttpd \
    redis \
    tzdata \
    cronie \
    rc-local  \
    avahi \
    python \
    python-redis \
    python-pip \
    sensorhub \ 
"

RDEPENDS_packagegroup-sensorhub-test = "\
    opkg \
    lighttpd \
    redis \
    tzdata \
    cronie \
    rc-local  \
    avahi \
    python \
    python-redis \
    python-pip \
    sensorhub-test \ 
"

#    python-bluepy 
#    mosquitto-clients 
