#!/bin/sh

# firstBoot.sh
# Script called on the first boot to install silverline from the package manager
# this script will always be called until the package is installed


CHECK_FILES_BIN=/opt/sensorhub/tools/checkSystemFiles.lua
SYSTEMCTL_BIN=/bin/systemctl
OPKG_BIN=/usr/bin/opkg
REBOOT_BIN=/sbin/reboot
PACKAGE_NAME=silverline 

PACKAGE_STATUS=`${OPKG_BIN} status ${PACKAGE_NAME}`

if [[ $PACKAGE_STATUS == "" ]]; then
        ${SYSTEMCTL_BIN} stop ma
        ${SYSTEMCTL_BIN} disable ma
        ${CHECK_FILES_BIN} -w
        ${OPKG_BIN} update
        ${OPKG_BIN} install ${PACKAGE_NAME}
	PACKAGE_STATUS=`${OPKG_BIN} status ${PACKAGE_NAME}`
	if [[ $PACKAGE_STATUS != "" ]]; then
		${REBOOT_BIN}
	fi
fi


