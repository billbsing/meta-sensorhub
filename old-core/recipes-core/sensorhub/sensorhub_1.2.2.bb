DESCRIPTION = "Silverline Sensor Hub."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d049ae05b3c6406b06bd5d2a8eb2562c"
HOMEPAGE = "https://github.com/newtoncircus/silverline-sensor-hub"

PR = "r0"

DEPENDS = "redis lighttpd bluez5 lua libopenzwave \
	lua-stdlib lua-posix \
        lua-json lua-etlua lua-socket lua-logging \
        lua-filesystem lua-lpeg lua-rings \
        lua-xavante lua-copas lua-coxpcall lua-cosmo lua-luatz lua-md5 \
	lua-redis lua-telescope lua-openssl lua-azure-iot-hub lua-wsapi \
"

SRC_URI = "git://git@github.com/newtoncircus/silverline-sensor-hub.git;branch=test-1.2;protocol=ssh;tag=v${PV} \
            file://sensorhub.pc \
"

SRC_URI[md5sum] = "dc7f94ec6ff15c985d2d6ad0f1b35654"
SRC_URI[sha256sum] = "13c2fb97961381f7d06d5b5cea55b743c163800896fd5c5e2356201d3619002d"


inherit update-rc.d useradd

PACKAGES =+ "${PN}-test"

USERADD_PACKAGES = "${PN}" 
USERADD_PARAM_${PN} = "--home-dir /home/sensorhub --create-home --system --shell /bin/sh --user-group sensorhub" 


S = "${WORKDIR}/git"

SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"

luadir = "/lua/5.2"

EXTRA_OEMAKE = "'PREFIX=${D}${prefix}' \
'LUA_LIBDIR=${D}${libdir}${luadir}' \
'LUA_DIR=${D}${datadir}${luadir}' \
'LUA_INC=${SYSROOTS}${includedir}' \
'LUA_VERSION_NUM=502' \
'OZINCLUDEPATH=${SYSROOTS}${includedir}' \
'OZLIBPATH=${SYSROOTS}${libdir}'  \
'INCLUDEPATH=${SYSROOTS}${includedir}' \
'LIBPATH=${SYSROOTS}${libdir}' \
'CC=${CC}' \
'CXX=${CXX}' \
'INSTALL_DIR=${D}/opt/sensorhub' \
'MACHINE=${MACHINE}' \
'SYSCONFDIR=${D}${sysconfdir}' \
'DATA_DIR=${D}/var/lib/sensorhub' \
"


RDEPENDS_${PN} = "bluez5 lua libopenzwave \
	lua-stdlib \
	lua-posix lua-coxpcall \
        lua-json lua-etlua lua-socket lua-logging \
        lua-filesystem lua-lpeg lua-rings \
        lua-xavante lua-copas lua-cosmo lua-redis \
	lua-luatz lua-md5 lua-telescope lua-openssl \
	lua-azure-iot-hub lua-wsapi \
"

do_install () {
    oe_runmake \
        'INSTALL_TOP=${D}${prefix}' \
	'INSTALL_MAN=${D}${mandir}/man1' \
  	'INSTALL_MACHINE=${MACHINE}'  \
        install


    install -d ${D}/var/lib/sensorhub
    rm -f ${D}/opt/sensorhub/tools/support.lua

    chmod +x ${D}/opt/sensorhub/cgi/runWebserver.lua

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/sensorhub.pc ${D}${libdir}/pkgconfig/
}


# init scripts to auto start servers

INITSCRIPT_PACKAGES = "${PN} ${PN}-bluetooth ${PN}-watchdog ${PN}-data ${PN}-zwave"

INITSCRIPT_NAME_${PN} = "sensorhub-startup"
INITSCRIPT_PARAMS_${PN} = "defaults 01"

INITSCRIPT_NAME_${PN}-bluetooth = "sensorhub-bluetooth"
INITSCRIPT_PARAMS_${PN}-bluetooth = "defaults 88"

INITSCRIPT_NAME_${PN}-watchdog = "sensorhub-watchdog"
INITSCRIPT_PARAMS_${PN}-watchdog = "defaults 88"

INITSCRIPT_NAME_${PN}-data = "sensorhub-data"
INITSCRIPT_PARAMS_${PN}-data = "defaults 88"

INITSCRIPT_NAME_${PN}-zwave = "sensorhub-zwave"
INITSCRIPT_PARAMS_${PN}-zwave = "defaults 88"



FILES_${PN} = "${libdir}${luadir}/*.so  \
/opt/sensorhub/devices/*  	\
/opt/sensorhub/static/*	  	\
/opt/sensorhub/tools/*    	\
/opt/sensorhub/views/*    	\
/opt/sensorhub/lib/*	  	\
/opt/sensorhub/sql/*       	\
/opt/sensorhub/controllers/*    \
/opt/sensorhub/bin/* 		\
/opt/sensorhub/models/* 	\
/opt/sensorhub/cgi/* 		\
${sysconfdir}/init.d/*  	\
/var/lib/sensorhub/*		\
"
FILES_${PN}-dbg = "\
	${libdir}${luadir}/.debug/*	\
"

FILES_${PN}-test = " \
	/opt/sensorhub/tests	\
	/opt/sensorhub/tests/*	\
"

pkg_postinst_${PN} ()  {
	/opt/sensorhub/tools/serverControl.lua stop
	/etc/init.d/lighttpd stop
	/opt/sensorhub/tools/serverControl.lua start
	/opt/sensorhub/tools/checkSystemFiles.lua --write
	/etc/init.d/lighttpd start
	/opt/sensorhub/tools/serverControl.lua start
}

