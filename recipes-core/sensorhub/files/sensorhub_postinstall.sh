#!/bin/sh

OPKG_BIN=/usr/bin/opkg
/bin/sleep 30


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
