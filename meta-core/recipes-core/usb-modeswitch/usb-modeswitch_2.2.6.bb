DESCRIPTION = "Handling Mode-Switching USB Devices on Linux"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
HOMEPAGE = "http://www.draisberghof.de/usb_modeswitch"

PR = "r0"

DEPENDS = "tcl libusb"

SRC_URI = "http://www.draisberghof.de/usb_modeswitch/usb-modeswitch-2.2.6.tar.bz2;name=code \
http://www.draisberghof.de/usb_modeswitch/usb-modeswitch-data-20151101.tar.bz2;name=data;filename=data	\
http://www.draisberghof.de/usb_modeswitch/device_reference.txt;name=device_reference	\
"

SRC_URI[code.md5sum] = "ea050e3b44a2b7aa805619042f5448df"
SRC_URI[code.sha256sum] = "7ee42be2fe26dde20c58f54bf678fb136d2706250e4aa74f06fc97268a46e90f"
SRC_URI[data.md5sum] = "21af977bfc4e7a705d318e88d7a63494"
SRC_URI[data.sha256sum] = "584d362bc0060c02016edaac7b05ebd6558d5dcbdf14f1ae6d0ec9630265a982"
SRC_URI[device_reference.md5sum] = "080bac4145f002466b1cbd1b973bba44"
SRC_URI[device_reference.sha256sum] = "07d32333eda6b8c19572490e01ab6e7b6912fb07a4b2f0f491ddb80ad7eceed3"



# inherit 


# S = "${WORKDIR}/"


EXTRA_OEMAKE = "'PREFIX=${D}${prefix}' \
'CC=${CC}' \
'CXX=${CXX}' \
'DEST_DIR=${D}' \
'TCL=/usr/bin/tclsh' \
"

do_install () {
    oe_runmake \
	'DESTDIR=${D}' \
	'PREFIX=${D}/${prefix}' \
	install

    cd ${WORKDIR}/usb-modeswitch-data-20151101

    oe_runmake \
	'DESTDIR=${D}' \
	'PREFIX=${D}/${prefix}' \
	install

    cp ${WORKDIR}/device_reference.txt ${D}${share}/usb_modeswitch
    rm ${D}/usb_modeswitch
}


RDEPENDS_${PN} = "tcl"

FILES_${PN} = "/usr/share/usb_modeswitch/*  \
/var/lib/usb_modeswitch  \
/etc/usb_modeswitch.conf  \
/etc/usb_modeswitch.d  \
/usr/sbin/usb_modeswitch  \
/usr/sbin/usb_modeswitch_dispatcher \ 
/lib/udev/usb_modeswitch  \
/lib/udev/rules.d/40-usb_modeswitch.rules  \
"
