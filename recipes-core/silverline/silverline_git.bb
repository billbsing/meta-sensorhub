DESCRIPTION = "Silverline Sensor Hub."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d049ae05b3c6406b06bd5d2a8eb2562c"
HOMEPAGE = "https://github.com/newtoncircus/silverline-sensor-hub"

PR = "r10"
SRCREV = "${AUTOREV}"

# This variable is used belowe as the upgrade process to create a 'version.info' file with the current version build using yocto
# If the current build is 'git' then we need to write the real version number, else put in "${PV}-${PR}"
# must be in the format nn.nn.nn or nn.nn.nn-rnn

INSTALL_VERSION="1.2.4-${PR}"


DEPENDS = "glib-2.0 lua \
	lua-stdlib lua-posix \
        lua-json lua-etlua lua-socket lua-logging \
        lua-filesystem lua-lpeg lua-rings \
        lua-xavante lua-copas lua-coxpcall lua-cosmo lua-luatz lua-md5 \
	lua-redis lua-telescope lua-openssl lua-azure-iot-hub lua-wsapi \
	lua-lzmq \
"

SRC_URI = "git://git@github.com/newtoncircus/silverline-sensor-hub.git;branch=ostro;protocol=ssh \
            file://silverline.pc \
	    file://sensorhub-bluetooth.service \
	    file://sensorhub-data.service \
	    file://sensorhub-network.service \
	    file://sensorhub-watchdog.service \
	    file://sensorhub-devices.service \
	    file://shdapAPIServer.service \
	    file://sensorhub-action.service \
	    file://sensorhub-factory-reset.service \
"

SRC_URI[md5sum] = "dc7f94ec6ff15c985d2d6ad0f1b35654"
SRC_URI[sha256sum] = "13c2fb97961381f7d06d5b5cea55b743c163800896fd5c5e2356201d3619002d"


inherit useradd

PACKAGES =+ "${PN}-test"

USERADD_PACKAGES = "${PN}" 
USERADD_PARAM_${PN} = "--home-dir /home/sensorhub --create-home --system --shell /bin/bash --user-group sensorhub" 


S = "${WORKDIR}/git"

SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"

luadir = "/lua/5.2"

EXTRA_OEMAKE = "'PREFIX=${D}${prefix}' \
'LUA_LIBDIR=${D}${libdir}${luadir}' \
'LUA_DIR=${D}${datadir}${luadir}' \
'LUA_INC=${SYSROOTS}${includedir}' \
'LUA_VERSION_NUM=502' \
'OZINCLUDEPATH=${SYSROOTS}${includedir}' \
'OZLIBPATH=${SYSROOTS}${libdir}'  \
'INCLUDEPATH=${SYSROOTS}${includedir}' \
'LIBPATH=${SYSROOTS}${libdir}' \
'CC=${CC}' \
'CXX=${CXX}' \
'INSTALL_DIR=${D}/opt/sensorhub' \
'MACHINE=${MACHINE}' \
'DISTOR=${DISTRO}' \
'SYSCONFDIR=${D}${sysconfdir}' \
'DATA_DIR=${D}/var/lib/sensorhub' \
"


RDEPENDS_${PN} = "lua-stdlib \
	lua-posix lua-coxpcall \
        lua-json lua-etlua lua-socket lua-logging \
        lua-filesystem lua-lpeg lua-rings \
        lua-xavante lua-copas lua-cosmo lua-redis \
	lua-luatz lua-md5 lua-telescope lua-openssl \
	lua-azure-iot-hub lua-wsapi lua-lzmq \
"

do_install () {
    oe_runmake \
        'INSTALL_TOP=${D}${prefix}' \
	'INSTALL_MAN=${D}${mandir}/man1' \
  	'INSTALL_MACHINE=${MACHINE}'  \
	'INSTALL_DISTRO=${DISTRO}' \
        install


    install -d ${D}/var/lib/sensorhub
    rm -f ${D}/opt/sensorhub/tools/support.lua

    chmod +x ${D}/opt/sensorhub/cgi/runWebserver.lua

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/silverline.pc ${D}${libdir}/pkgconfig/

    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/sensorhub-bluetooth.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sensorhub-data.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sensorhub-network.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sensorhub-watchdog.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sensorhub-devices.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/shdapAPIServer.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sensorhub-action.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sensorhub-factory-reset.service ${D}${systemd_unitdir}/system/

    install -d ${D}${sysconfdir}
    install -m 0644 ${S}/install/ostro/resetData/lighttpd.conf ${D}${sysconfdir}/

    install -d ${D}${sysconfdir}/cron.daily
    install -m 0644 ${S}/install/ostro/resetData/cron.daily/autoupgrade ${D}${sysconfdir}/cron.daily/

    install -d ${D}${sysconfdir}/cron.weekly
    install -m 0644 ${S}/install/ostro/resetData/cron.weekly/refreshTimezone ${D}${sysconfdir}/cron.weekly/

    install -d ${D}${sysconfdir}/redis
    install -m 0644 ${S}/install/ostro/resetData/redis/redis.conf ${D}${sysconfdir}/redis/

}

INSANE_SKIP_${PN} = "ldflags"
INSANE_SKIP_${PN}-dev = "ldflags"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "sensorhub-bluetooth.service  \
	sensorhub-data.service  \
	sensorhub-network.service  \
	sensorhub-watchdog.service  \
	sensorhub-devices.service  \
	shdapAPIServer.service  \
	sensorhub-action.service  \
"

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_DEFAULT_TARGET="multi-user.target"


FILES_${PN} = "${libdir}${luadir}/*.so  \
/opt/sensorhub/devices/*  	\
/opt/sensorhub/static/*	  	\
/opt/sensorhub/tools/*    	\
/opt/sensorhub/views/*    	\
/opt/sensorhub/lib/*	  	\
/opt/sensorhub/sql/*       	\
/opt/sensorhub/controllers/*    \
/opt/sensorhub/bin/* 		\
/opt/sensorhub/models/* 	\
/opt/sensorhub/cgi/* 		\
/opt/sensorhub/actions/* 	\
/var/lib/sensorhub/*		\
${systemd_unitdir}/system/      \
${sysconfdir}/lighttpd.conf	\
${sysconfdir}/cron.daily/autoupgrade		\
${sysconfdir}/cron.weekly/refreshTimezone	\
${sysconfdir}/redis/redis.conf			\
"

FILES_${PN}-dbg = "\
	${libdir}${luadir}/.debug/*	\
"

FILES_${PN}-test = " \
	/opt/sensorhub/tests	\
	/opt/sensorhub/tests/*	\
"

pkg_postinst_${PN} ()  {

#!/bin/sh -e
echo "Start postinst"
echo "write reset config data"
/opt/sensorhub/tools/checkSystemFiles.lua --write
/opt/sensorhub/tools/serverControl.lua enable
echo "update version"
rm -f /opt/sensorhub/lib/version.info
echo "${INSTALL_VERSION}" > /opt/sensorhub/lib/version.info
rm -f /etc/sensorhub-version.info
echo "${INSTALL_VERSION}" > /etc/sensorhub-version
echo "End postinst"

}
