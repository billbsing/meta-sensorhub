


BAD_RECOMMENDATIONS = "tzdata_2013b tzcode-native_2013b		 \
	opencv nodejs						\
	python python-modules python-numpy python-opencv	\
	alsa-lib alsa-utils alsa-tools				\
"

# core modules for silverline
IMAGE_INSTALL += "ntp-tickadj "
IMAGE_INSTALL += "ntp"
IMAGE_INSTALL += "ntpdate"
IMAGE_INSTALL += "ntp-utils"
IMAGE_INSTALL += "rsync"
IMAGE_INSTALL += "tzdata"
IMAGE_INSTALL += "iw"
IMAGE_INSTALL += "cronie"
IMAGE_INSTALL += "avahi"
IMAGE_INSTALL += "avahi-daemon"
# IMAGE_INSTALL += "connman-client"
# IMAGE_INSTALL += "picocom"
# IMAGE_INSTALL += "mosquitto autossh logrotate"
IMAGE_INSTALL += "lua"

# libraries
IMAGE_INSTALL += "sqlite3 libopenzwave"

# drivers
# IMAGE_INSTALL += "iwlwifi-7260"

# lua rocks libraries
IMAGE_INSTALL += "lua-stdlib lua-sqlite3 lua-posix \
	lua-json lua-etlua lua-socket lua-logging lua-md5 \
	lua-filesystem lua-lpeg lua-rings lua-wsapi lua-luatz \
	lua-xavante lua-copas lua-cosmo	lua-orbit lua-coxpcall \
"

# The final Package 

IMAGE_INSTALL += "sensorhub"



