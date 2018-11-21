#!/bin/sh

# first_boot.sh
# Script called on the first boot to install Sensorhub from the package manager
# this script will always be called until the package is installed

TOOLS_PATH=/opt/sensorhub/tools
CHECK_FILES_BIN=${TOOLS_PATH}/checkSystemFiles.lua
SYSTEMCTL_BIN=/bin/systemctl
OPKG_BIN=/usr/bin/opkg
REBOOT_BIN=/sbin/reboot
PACKAGE_NAME=sensorhub
POST_INSTALL=/usr/bin/sensorhub_postinstall.sh
SLEEP_BIN=/bin/sleep

if [[ -f ${CHECK_FILES_BIN} ]]; then
	${CHECK_FILES_BIN} -w
fi

PACKAGE_STATUS=`${OPKG_BIN} status ${PACKAGE_NAME}`

if [ "${PACKAGE_STATUS}" = "" ]; then
	echo "Package ${PACKAGE_NAME} not loaded"
	${CHECK_FILES_BIN} -w
	${OPKG_BIN} update
	${OPKG_BIN} install ${PACKAGE_NAME}

	PACKAGE_STATUS=`${OPKG_BIN} status ${PACKAGE_NAME}`
	if [ "${PACKAGE_STATUS}" != "" ]; then
		${REBOOT_BIN}
	fi
fi

# when an older distro is built, the wait above did not exist, so call the post install script
if [ -f $POST_INSTALL ]; then
	$POST_INSTALL &
fi
