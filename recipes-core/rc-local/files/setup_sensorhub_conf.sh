#!/bin/sh

# setup_sensorhub_conf.sh
# checks for a valid sensorhub.conf file, if not found than create one for testing

TOOLS_PATH=/opt/sensorhub/tools
CHECK_FILES_BIN=${TOOLS_PATH}/checkSystemFiles.lua
SYSTEMCTL_BIN=/bin/systemctl
CONF_FILE=sensorhub.conf
RESET_DATA_PATH=/var/lib/sensorhub/resetData
SET_WIFI_TETHER=${TOOLS_PATH}/setWifiTether.lua

if [[ ! -f /etc/${CONF_FILE} ]]; then
	echo "cannot find /etc/${CONF_FILE}"
	if [[ ! -f ${RESET_DATA_PATH}/${CONF_FILE} ]]; then
		echo "cannot find backup config file: ${RESET_DATA_PATH}/${CONF_FILE}, so setting to test mode"
		SSID=connectedlife_`cat /etc/machine-id | cut -c -4`
		echo "# Sensorhub config file for testing only 
ssid = ${SSID} 
password = 001122334455
" > /etc/${CONF_FILE}

	fi
	${SYSTEMCTL_BIN} restart lighttpd
	${CHECK_FILES_BIN} -w
	${SET_WIFI_TETHER} --force
fi

if [[ -f ${SET_WIFI_TETHER} ]]; then
	${SET_WIFI_TETHER}
fi

