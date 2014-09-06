DESCRIPTION = "Mosquitto is an open source (BSD licensed) message broker that implements the MQ Telemetry Transport protocol versions 3.1 and 3.1.1. MQTT provides a lightweight method of carrying out messaging using a publish/subscribe model."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=89aa5ea5f32e4260d84c5d185ee3add4"
HOMEPAGE = "http://mosquitto.org/"

PR = "r0"

SRC_URI = "http://mosquitto.org/files/source/mosquitto-1.3.4.tar.gz \
           file://mosquitto.pc \
"

SRC_URI[md5sum] = "9d729849efd74c6e3eee17a4a002e1e9"
SRC_URI[sha256sum] = "0a3982d6b875a458909c8828731da04772035468700fa7eb2f0885f4bd6d0dbc"

DEPENDS = "libc-ares"

inherit extrausers 

SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"

CFLAGS = "-Wall -O2"
EXTRA_OEMAKE = "'prefix=${prefix}' \
	'WITH_MEMORY_TRACKING=no' \
	'CFLAGS=-Wall -O2' \
	'LIB_CFLAGS=${CFLAGS} ${CPPFLAGS} -I. -I.. -I../lib -I ${SYSROOTS}${includedir} -L ${SYSROOTS}${libdir}' \
	'DESTDIR=${D}' \
"


do_compile () {
    oe_runmake 
}


do_install () {
    oe_runmake install


    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/mosquitto.pc ${D}${libdir}/pkgconfig/
}

PACKAGES += "${PN}-python"

FILES_${PN}-python += "${libdir}/python2.7/site-packages/mosquitto.py \
  ${libdir}/python2.7/site-packages/mosquitto-1.3.4-py2.7.egg-info \
  ${libdir}/python2.7/site-packages/mosquitto.pyc \
 "

EXTRA_USERS_PARAMS = "\
         useradd -p '' -c 'Mosquitto MQTT Broker daemon:' -d /etc/mosquitto -M -s /bin/false  -U mosquitto; \
"
