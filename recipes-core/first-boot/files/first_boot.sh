#!/bin/sh

# firstBoot.sh
# Script called on the first boot to install Sensorhub from the package manager
# this script will always be called until the package is installed


CHECK_FILES_BIN=/opt/sensorhub/tools/checkSystemFiles.lua
SYSTEMCTL_BIN=/bin/systemctl
OPKG_BIN=/usr/bin/opkg
REBOOT_BIN=/sbin/reboot
PACKAGE_NAME=sensorhub
if [[ -f ${CHECK_FILES_BIN} ]]; then
        ${CHECK_FILES_BIN} -w
fi

PACKAGE_STATUS=`${OPKG_BIN} status ${PACKAGE_NAME}`

if [[ $PACKAGE_STATUS == "" ]]; then
        ${CHECK_FILES_BIN} -w
        ${OPKG_BIN} update
        ${OPKG_BIN} install ${PACKAGE_NAME}
	PACKAGE_STATUS=`${OPKG_BIN} status ${PACKAGE_NAME}`
	if [[ $PACKAGE_STATUS != "" ]]; then
		${REBOOT_BIN}
	fi
fi


