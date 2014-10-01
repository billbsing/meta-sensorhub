DESCRIPTION = "TimeZone Code"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README;md5=d0ff93a73dd5bc3c6e724bb4343760f6"
HOMEPAGE = "http://www.iana.org/time-zones"

PR = "r0"


SRC_URI = "http://www.iana.org/time-zones/repository/releases/tzcode${PV}.tar.gz;subdir=${BPN}-${PV};name=tzcode \
	http://www.iana.org/time-zones/repository/releases/tzdata${PV}.tar.gz;subdir=${BPN}-${PV};name=tzdata \
"

SRC_URI[tzcode.md5sum] = "8e7741fc769ebdd94d95e5f2c3adbb60"
SRC_URI[tzcode.sha256sum] = "a4d9788a1bb0aa314eae4986ee991425b83ecc47da0e84f626735846be1dbf44"

SRC_URI[tzdata.md5sum] = "ed05111948beba8a0f30956baa46b272"
SRC_URI[tzdata.sha256sum] = "e78152f616fb07c1dea124215ffca57d0de66d8897e00896086542e3de30f69e"


inherit native

do_install () {
        install -d ${D}${bindir}/
        install -m 755 zic ${D}${bindir}/
        install -m 755 zdump ${D}${bindir}/
        install -m 755 tzselect ${D}${bindir}/
}

 

