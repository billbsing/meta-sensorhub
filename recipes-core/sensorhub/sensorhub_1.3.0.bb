DESCRIPTION = "ConnectedLife Sensor Hub."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d049ae05b3c6406b06bd5d2a8eb2562c"
HOMEPAGE = "https://github.com/newtoncircus/silverline-sensor-hub"

PR = "r3"
# SRCREV = "${AUTOREV}"

# This variable is used belowe as the upgrade process to create a 'version.info' file with the current version build using yocto
# If the current build is 'git' then we need to write the real version number, else put in "${PV}-${PR}"
# must be in the format nn.nn.nn or nn.nn.nn-rnn

# INSTALL_VERSION="1.2.5-${PR}"
INSTALL_VERSION="${PV}-${PR}"
GIT_BRANCH="development"

MAINTAINER="bill.barman@connectedlife.io"

DEPENDS = "glib-2.0 lua \
	lua-stdlib lua-posix \
        lua-json lua-etlua lua-socket lua-logging \
        lua-filesystem lua-lpeg lua-rings \
        lua-xavante lua-copas lua-coxpcall lua-cosmo lua-luatz lua-md5 \
	lua-redis lua-telescope lua-openssl lua-azure-iot-hub lua-wsapi \
	lua-lzmq \
	zipctl zipgateway \
"
# git://git@github.com/newtoncircus/silverline-sensor-hub.git;tag=v${PV};protocol=ssh 

SRC_URI = "git://git@github.com/newtoncircus/silverline-sensor-hub.git;protocol=ssh;tag=v${PV};branch=${GIT_BRANCH} \
            file://sensorhub.pc \
	    file://sensorhub-bluetooth-scanner.service \
	    file://sensorhub-bluetooth.service \
	    file://sensorhub-data.service \
	    file://sensorhub-network.service \
	    file://sensorhub-watchdog.service \
	    file://sensorhub-action.service \
	    file://sensorhub-factory-reset.service \
	    file://sensorhub-support.service \
	    file://sensorhub-zwave.service \
"

SRC_URI[md5sum] = "dc7f94ec6ff15c985d2d6ad0f1b35654"
SRC_URI[sha256sum] = "13c2fb97961381f7d06d5b5cea55b743c163800896fd5c5e2356201d3619002d"


inherit useradd

PACKAGES =+ "${PN}-test"

USERADD_PACKAGES = "${PN}" 
USERADD_PARAM_${PN} = "--home-dir /home/sensorhub --create-home --system --shell /bin/bash --user-group sensorhub" 


S = "${WORKDIR}/git"

SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"

luadir = "/lua/5.3"

EXTRA_OEMAKE = "'PREFIX=${D}${prefix}' \
'LUA_LIBDIR=${D}${libdir}${luadir}' \
'LUA_DIR=${D}${datadir}${luadir}' \
'LUA_INC=${SYSROOTS}${includedir}' \
'LUA_VERSION_NUM=503' \
'OZINCLUDEPATH=${SYSROOTS}${includedir}' \
'OZLIBPATH=${SYSROOTS}${libdir}'  \
'INCLUDEPATH=${SYSROOTS}${includedir}' \
'LIBPATH=${SYSROOTS}${libdir}' \
'CC=${CC}' \
'CXX=${CXX}' \
'INSTALL_DIR=${D}/opt/sensorhub' \
'MACHINE=${MACHINE}' \
'SYSCONFDIR=${D}${sysconfdir}' \
'DATA_DIR=${D}/var/lib/sensorhub' \
'LIB_ZIP_PATH=${STAGING_LIBDIR}' \
'CFLAGS=-Wall -fPIC -DOS_LINUX -DLUA_32BITS' \
'ZIP_INC=${STAGING_INCDIR}/zipctl' \
"


RDEPENDS_${PN} = "lua-stdlib \
	lua-posix lua-coxpcall \
        lua-json lua-etlua lua-socket lua-logging \
        lua-filesystem lua-lpeg lua-rings \
        lua-xavante lua-copas lua-cosmo lua-redis \
	lua-luatz lua-md5 lua-telescope lua-openssl \
	lua-azure-iot-hub lua-wsapi lua-lzmq \
	zipctl zipgateway \
"

do_install () {
    oe_runmake \
        'INSTALL_TOP=${D}${prefix}' \
	'INSTALL_MAN=${D}${mandir}/man1' \
  	'INSTALL_MACHINE=${MACHINE}'  \
        install


    install -d ${D}/var/lib/sensorhub
    rm -f ${D}/opt/sensorhub/tools/support.lua

    chmod +x ${D}/opt/sensorhub/cgi/runWebserver.lua

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/sensorhub.pc ${D}${libdir}/pkgconfig/

    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/sensorhub-bluetooth-scanner.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sensorhub-bluetooth.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sensorhub-data.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sensorhub-network.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sensorhub-watchdog.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sensorhub-action.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sensorhub-factory-reset.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sensorhub-support.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sensorhub-zwave.service ${D}${systemd_unitdir}/system/

    install -d ${D}${sysconfdir}
    install -m 0644 ${S}/install/resetData/lighttpd.conf ${D}${sysconfdir}/

    install -d ${D}${sysconfdir}/cron.daily
    install -m 0755 ${S}/install/resetData/cron.daily/autoupgrade ${D}${sysconfdir}/cron.daily/

    install -d ${D}${sysconfdir}/cron.weekly
    install -m 0755 ${S}/install/resetData/cron.weekly/refreshTimezone ${D}${sysconfdir}/cron.weekly/

    install -d ${D}${sysconfdir}/redis
    install -m 0644 ${S}/install/resetData/redis/redis.conf ${D}${sysconfdir}/redis/

    install -d ${D}${sysconfdir}/zipgateway
    install -m 0644 ${S}/install/resetData/zipgateway/* ${D}${sysconfdir}/zipgateway/

}

# INSANE_SKIP_${PN} = "ldflags"
# INSANE_SKIP_${PN}-dev = "ldflags"
inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "sensorhub-bluetooth.service  \
	sensorhub-bluetooth-scanner.service  \
	sensorhub-data.service  \
	sensorhub-network.service  \
	sensorhub-watchdog.service  \
	sensorhub-action.service  \
	sensorhub-zwave.service  \
"

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_DEFAULT_TARGET="multi-user.target"


FILES_${PN} = "${libdir}${luadir}/*.so  \
${datadir}${luadir}/zwave/*  \
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
${sysconfdir}/opkg/base-feeds.conf		\
${sysconfdir}/zipgateway/*                      \
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
