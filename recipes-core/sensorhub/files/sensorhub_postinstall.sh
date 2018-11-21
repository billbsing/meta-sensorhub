#!/bin/sh

OPKG_BIN=/usr/bin/opkg
/bin/sleep 30

NEEDS_REBOOT=/tmp/needs-reboot

PACKAGE_NAME=dnsmasq
PACKAGE_STATUS=`${OPKG_BIN} status ${PACKAGE_NAME}`
if [ "${PACKAGE_STATUS}" = "" ]; then
	echo "Package ${PACKAGE_NAME} not loaded"
	${OPKG_BIN} install ${PACKAGE_NAME}
fi

PACKAGE_NAME=rc-local
PACKAGE_STATUS=`${OPKG_BIN} status ${PACKAGE_NAME}`
if [ "${PACKAGE_STATUS}" = "" ]; then
	echo "Package ${PACKAGE_NAME} not loaded"
	${OPKG_BIN} install ${PACKAGE_NAME}
fi
PACKAGE_NAME=connman
PACKAGE_STATUS=`${OPKG_BIN} status ${PACKAGE_NAME}`
if [ "${PACKAGE_STATUS}" = "" ]; then
	echo "Package ${PACKAGE_NAME} not loaded"
	${OPKG_BIN} install ${PACKAGE_NAME}
	PACKAGE_STATUS=`${OPKG_BIN} status ${PACKAGE_NAME}`
	if [ "${PACKAGE_STATUS}" != "" ]; then
		/sbin/reboot
	fi
fi

if [ -f $NEEDS_REBOOT ]; then
	echo "will reboot"
	sleep 10
	/sbin/reboot
fi
