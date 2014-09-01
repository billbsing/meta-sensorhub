


IMAGE_INSTALL += "ntp-tickadj ntp ntpdate ntp-utils"
IMAGE_INSTALL += "rsync tzdata picocom"
IMAGE_INSTALL += "lua"
IMAGE_INSTALL += "sqlite3 libopenzwave"
IMAGE_INSTALL += "iwlwifi-7260"

##  libsqlite3-dev"

IMAGE_INSTALL += "lua-stdlib lua-sqlite3 lua-posix \
	lua-json lua-etlua lua-socket lua-logging \
	lua-filesystem lua-lpeg lua-rings lua-wsapi \
	lua-xavante lua-copas lua-cosmo	lua-orbit \
"




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




