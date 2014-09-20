

# core modules for silverline
IMAGE_INSTALL += "ntp-tickadj ntp ntpdate ntp-utils"
IMAGE_INSTALL += "rsync tzdata tzdata-misc"
IMAGE_INSTALL += "cronie avahi avahi-daemon"
# IMAGE_INSTALL += "picocom"
IMAGE_INSTALL += "mosquitto autossh logrotate"
IMAGE_INSTALL += "lua"

# libraries
IMAGE_INSTALL += "sqlite3 libopenzwave"

# drivers
IMAGE_INSTALL += "iwlwifi-7260"

# lua rocks libraries
IMAGE_INSTALL += "lua-stdlib lua-sqlite3 lua-posix \
	lua-json lua-etlua lua-socket lua-logging lua-md5 \
	lua-filesystem lua-lpeg lua-rings lua-wsapi \
	lua-xavante lua-copas lua-cosmo	lua-orbit lua-coxpcall \
"

# The final Package 

IMAGE_INSTALL += "sensorhub"

# IMAGE_ROOTFS_EXTRA_SPACE = "102400"


# only us if we want to add devtools to the image....

#################################################################################################
# for the time being build with full dev tools - so that we can build the packages on the board

# dev-pkgs breaks i2c communications and Cypress port extender driver as a result.
# Therefore not including it for now.
#IMAGE_FEATURES += "dev-pkgs tools-sdk"

## IMAGE_FEATURES += "tools-sdk"

# This is not included into tools-sdk
## IMAGE_INSTALL_append = " git"

#increasing rootfs size to provide more free space
## IMAGE_ROOTFS_SIZE = "2097152"




