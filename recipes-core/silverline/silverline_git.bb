DESCRIPTION = "Silverline Sensor Hub."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d049ae05b3c6406b06bd5d2a8eb2562c"
HOMEPAGE = "https://github.com/newtoncircus/silverline-sensor-hub"

PR = "r30"
SRCREV = "${AUTOREV}"

# This variable is used belowe as the upgrade process to create a 'version.info' file with the current version build using yocto
# If the current build is 'git' then we need to write the real version number, else put in "${PV}-${PR}"
# must be in the format nn.nn.nn or nn.nn.nn-rnn

INSTALL_VERSION="1.2.3-${PR}"


DEPENDS = "glib-2.0 lua \
	lua-stdlib lua-posix \
        lua-json lua-etlua lua-socket lua-logging \
        lua-filesystem lua-lpeg lua-rings \
        lua-xavante lua-copas lua-coxpcall lua-cosmo lua-luatz lua-md5 \
	lua-redis lua-telescope lua-openssl lua-azure-iot-hub lua-wsapi \
	lua-lzmq \
"

SRC_URI = "git://git@github.com/newtoncircus/silverline-sensor-hub.git;branch=ostro;protocol=ssh \
            file://silverline.pc \
	    file://sensorhub-bluetooth.service \
	    file://sensorhub-data.service \
	    file://sensorhub-network.service \
	    file://sensorhub-watchdog.service \
	    file://sensorhub-devices.service \
	    file://shdapAPIServer.service \
	    file://silverline.target \
"

SRC_URI[md5sum] = "dc7f94ec6ff15c985d2d6ad0f1b35654"
SRC_URI[sha256sum] = "13c2fb97961381f7d06d5b5cea55b743c163800896fd5c5e2356201d3619002d"


inherit useradd

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
'DISTOR=${DISTRO}' \
'SYSCONFDIR=${D}${sysconfdir}' \
'DATA_DIR=${D}/var/lib/sensorhub' \
"


RDEPENDS_${PN} = "lua-stdlib \
	lua-posix lua-coxpcall \
        lua-json lua-etlua lua-socket lua-logging \
        lua-filesystem lua-lpeg lua-rings \
        lua-xavante lua-copas lua-cosmo lua-redis \
	lua-luatz lua-md5 lua-telescope lua-openssl \
	lua-azure-iot-hub lua-wsapi lua-lzmq \
"

do_install () {
    oe_runmake \
        'INSTALL_TOP=${D}${prefix}' \
	'INSTALL_MAN=${D}${mandir}/man1' \
  	'INSTALL_MACHINE=${MACHINE}'  \
	'INSTALL_DISTRO=${DISTRO}' \
        install


    install -d ${D}/var/lib/sensorhub
    rm -f ${D}/opt/sensorhub/tools/support.lua

    chmod +x ${D}/opt/sensorhub/cgi/runWebserver.lua

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/silverline.pc ${D}${libdir}/pkgconfig/

    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/silverline.target ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sensorhub-bluetooth.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sensorhub-data.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sensorhub-network.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sensorhub-watchdog.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/sensorhub-devices.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/shdapAPIServer.service ${D}${systemd_unitdir}/system/

}

INSANE_SKIP_${PN} = "ldflags"
INSANE_SKIP_${PN}-dev = "ldflags"


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
/var/lib/sensorhub/*		\
${systemd_unitdir}/system/      \
"

FILES_${PN}-dbg = "\
	${libdir}${luadir}/.debug/*	\
"

FILES_${PN}-test = " \
	/opt/sensorhub/tests	\
	/opt/sensorhub/tests/*	\
"

pkg_postinst_${PN} ()  {
	systemctl daemon-reload
	/opt/sensorhub/tools/serverControl.lua stop
	systemctl stop shdapAPIServer
	systemctl stop lighttpd 
	/opt/sensorhub/tools/checkSystemFiles.lua --write 
	echo "${INSTALL_VERSION}" > /opt/sensorhub/lib/version.info
	systemctl start lighttpd 
	systemctl start shdapAPIServer
	/opt/sensorhub/tools/serverControl.lua enable 
	/opt/sensorhub/tools/serverControl.lua start
	systemctl daemon-reload
	systemctl set-default silverline
}

