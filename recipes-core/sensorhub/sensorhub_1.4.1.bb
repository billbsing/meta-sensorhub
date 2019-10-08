DESCRIPTION = "ConnectedLife Sensor Hub."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d049ae05b3c6406b06bd5d2a8eb2562c"
HOMEPAGE = "https://github.com/newtoncircus/connectedlife-sensor-hub"

PR = "r14"

# This variable is used belowe as the upgrade process to create a 'version.info' file with the current version build using yocto
# If the current build is 'git' then we need to write the real version number, else put in "${PV}-${PR}"
# must be in the format nn.nn.nn or nn.nn.nn-rnn
INSTALL_VERSION="${PV}-${PR}"

# this recipe accepts two types of sources depending if SENSORHUB_BUILD_TEST is set...
# RELEASE (default) - github - tag=v${PV}
# TEST -    github - branch=test/${PV}

RELEASE_BUILD="git://git@github.com/newtoncircus/connectedlife-sensor-hub.git;protocol=ssh;tag=v${PV}"

# Test builds
GIT_BRANCH="test/${PV}"
# SRCREV = "${AUTOREV}"
TEST_BUILD="git://git@github.com/newtoncircus/connectedlife-sensor-hub.git;protocol=ssh;branch=${GIT_BRANCH}"

BUILD="${@bb.utils.contains('SENSORHUB_BUILD_TEST', '1', '${TEST_BUILD}', '${RELEASE_BUILD}', d)}"
SRCREV="${@bb.utils.contains('SENSORHUB_BUILD_TEST', '1', '${AUTOREV}', '', d)}"

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

SRC_URI = "${BUILD} \
            file://sensorhub.pc \
	    file://sensorhub-bluetooth-scanner.service \
	    file://sensorhub-bluetooth.service \
	    file://sensorhub-data.service \
	    file://sensorhub-network.service \
	    file://sensorhub-watchdog.service \
	    file://sensorhub-action.service \
	    file://sensorhub-factory-reset.service \
	    file://sensorhub-control.service \
	    file://sensorhub-zwave.service \
	    file://sensorhub-zigbee.service \
		file://sensorhub_postinstall.sh \
"

SRC_URI[md5sum] = "dc7f94ec6ff15c985d2d6ad0f1b35654"
SRC_URI[sha256sum] = "13c2fb97961381f7d06d5b5cea55b743c163800896fd5c5e2356201d3619002d"


inherit useradd

# PACKAGES =+ "${PN}-test"
PACKAGES =+ "${@bb.utils.contains('SENSORHUB_BUILD_TEST', '1', '${PN}-test', '', d)}"


USERADD_PACKAGES = "${PN}" 
USERADD_PARAM_${PN} = "--home-dir /home/sensorhub --create-home --system --shell /bin/sh --user-group sensorhub" 


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
	zipctl zipgateway rc-local dnsmasq connman \
"

do_install () {
    oe_runmake \
        'INSTALL_TOP=${D}${prefix}' \
	'INSTALL_MAN=${D}${mandir}/man1' \
  	'INSTALL_MACHINE=${MACHINE}'  \
        install


    install -d ${D}/var/lib/sensorhub

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
    install -m 0644 ${WORKDIR}/sensorhub-control.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sensorhub-zwave.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sensorhub-zigbee.service ${D}${systemd_unitdir}/system/

    install -d ${D}${sysconfdir}
    install -m 0644 ${S}/install/resetData/lighttpd.conf ${D}${sysconfdir}/

    install -d ${D}${sysconfdir}/cron.daily

    install -d ${D}${sysconfdir}/cron.monthly
    install -m 0755 ${S}/install/resetData/cron.monthly/autoupgrade ${D}${sysconfdir}/cron.monthly/

    install -d ${D}${sysconfdir}/cron.weekly
    install -m 0755 ${S}/install/resetData/cron.weekly/refreshTimezone ${D}${sysconfdir}/cron.weekly/

    install -d ${D}${sysconfdir}/redis
    install -m 0644 ${S}/install/resetData/redis/redis.conf ${D}${sysconfdir}/redis/

    install -d ${D}${sysconfdir}/zipgateway
    install -m 0644 ${S}/install/resetData/zipgateway/* ${D}${sysconfdir}/zipgateway/

	install -m 0644 ${S}/install/resetData/opkg/${SENSORHUB_PACKAGE_FEED_FILE} ${D}/var/lib/sensorhub/resetData/opkg/base-feeds.conf
	rm ${D}/var/lib/sensorhub/resetData/opkg/base-feeds-*.conf

	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/sensorhub_postinstall.sh ${D}${bindir}

	if [ ! "${@bb.utils.contains('SENSORHUB_BUILD_TEST', '1', '1', '', d)}" ]; then
		rm -rf ${D}/opt/sensorhub/tests
	fi

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
	sensorhub-zigbee.service  \
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
${sysconfdir}/cron.daily	\
${sysconfdir}/cron.weekly/refreshTimezone	\
${sysconfdir}/cron.monthly/autoupgrade		\
${sysconfdir}/redis/redis.conf			\
${sysconfdir}/opkg/base-feeds.conf		\
${sysconfdir}/zipgateway/*                      \
${bindir}/sensorhub_postinstall.sh			\
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
touch /tmp/needs-reboot
if [ -f /usr/bin/sensorhub_postinstall.sh ]; then
	/usr/bin/sensorhub_postinstall.sh &
fi
echo "End postinst"

}
