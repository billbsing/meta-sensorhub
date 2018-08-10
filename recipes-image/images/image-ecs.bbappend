
IMAGE_INSTALL +="lighttpd"  
IMAGE_INSTALL +="redis"
IMAGE_INSTALL +="tzdata" 
IMAGE_INSTALL +="cronie"
IMAGE_INSTALL +="rc-local" 
IMAGE_INSTALL +="opkg" 
IMAGE_INSTALL +="sensorhub"
IMAGE_INSTALL +="haveged"
IMAGE_INSTALL +="rsync"
IMAGE_INSTALL +="dnsmasq"
# IMAGE_INSTALL += "zipctl"
IMAGE_INSTALL += "zipgateway"

# for zwave investigations
# IMAGE_INSTALL += "tcpdump"
# IMAGE_INSTALL += "cmake"
IMAGE_INSTALL += "python-pyserial"



SENSORHUB_ROOT_AUTHORIZED_KEYS ?= ""
ROOTFS_POSTPROCESS_COMMAND += "sensorhub_root_authorized_keys; "
sensorhub_root_authorized_keys () {
    mkdir ${IMAGE_ROOTFS}${ROOT_HOME}/.ssh
    echo "${SENSORHUB_ROOT_AUTHORIZED_KEYS}" >>${IMAGE_ROOTFS}${ROOT_HOME}/.ssh/authorized_keys
    chmod -R go-rwx ${IMAGE_ROOTFS}${ROOT_HOME}/.ssh
}


ROOTFS_POSTPROCESS_COMMAND += "sensorhub_root_password; "

SENSORHUB_ROOT_PASSWORD ?= ""

sensorhub_root_password() {
	#!/bin/sh -xv
	bbnote "password set to ${SENSORHUB_ROOT_PASSWORD}"
	if [ ! -z  "${SENSORHUB_ROOT_PASSWORD}" ]; then
#		bberror "sensorhub_root_password called  ${SENSORHUB_ROOT_PASSWORD}"
		bbnote "Setting root password"
		usermod --root ${IMAGE_ROOTFS} -p $(openssl passwd ${SENSORHUB_ROOT_PASSWORD}) root
	fi
}



