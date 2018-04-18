
IMAGE_INSTALL +="lighttpd"  
IMAGE_INSTALL +="redis"
IMAGE_INSTALL +="tzdata" 
IMAGE_INSTALL +="cronie"
# IMAGE_INSTALL +="first-boot" 
IMAGE_INSTALL +="opkg" 
IMAGE_INSTALL +="sensorhub"
IMAGE_INSTALL +="haveged"
IMAGE_INSTALL +="rsync"
IMAGE_INSTALL += "zipctl-sigma"
IMAGE_INSTALL += "zipgateway"


SENSORHUB_ROOT_AUTHORIZED_KEYS ?= ""
ROOTFS_POSTPROCESS_COMMAND += "sensorhub_root_authorized_keys; "
sensorhub_root_authorized_keys () {
    mkdir ${IMAGE_ROOTFS}${ROOT_HOME}/.ssh
    echo "${SENSORHUB_ROOT_AUTHORIZED_KEYS}" >>${IMAGE_ROOTFS}${ROOT_HOME}/.ssh/authorized_keys
    chmod -R go-rwx ${IMAGE_ROOTFS}${ROOT_HOME}/.ssh
}




