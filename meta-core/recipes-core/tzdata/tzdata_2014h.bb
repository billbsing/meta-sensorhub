DESCRIPTION = "TimeZone Database"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README;md5=d0ff93a73dd5bc3c6e724bb4343760f6"
HOMEPAGE = "http://www.iana.org/time-zones"

PR = "r0"

depends="tzcode-native_${PV}"

SRC_URI = "http://www.iana.org/time-zones/repository/releases/tzdata2014h.tar.gz;subdir=${BPN}-${PV};name=tzdata \
	http://www.iana.org/time-zones/repository/releases/tzcode${PV}.tar.gz;subdir=${BPN}-${PV};name=tzcode \
	file://tzdata.pc \
"


SRC_URI[tzdata.md5sum] = "ed05111948beba8a0f30956baa46b272"
SRC_URI[tzdata.sha256sum] = "e78152f616fb07c1dea124215ffca57d0de66d8897e00896086542e3de30f69e"

SRC_URI[tzcode.md5sum] = "8e7741fc769ebdd94d95e5f2c3adbb60"
SRC_URI[tzcode.sha256sum] = "a4d9788a1bb0aa314eae4986ee991425b83ecc47da0e84f626735846be1dbf44"

TZCODEDIR="${WORKDIR}/tzcode-${PV}"
SYSTOOLSDIR="${TMPDIR}/sysroots/${MACHINE}/usr/share/tzcode"





MAKE_FLAGS="'cc=${CC}' \
	'DESTDIR=${D}' \
        'TOPDIR=/usr' \
        'TZDIR=${datadir}/zoneinfo' \
	'zic=${STAGING_BINDIR_NATIVE}/zic' \
"


do_compile_prepend() {
#	cp ${TZCODEDIR}/tzselect.ksh ${S}
#	cp ${TZCODEDIR}/private.h ${S}
#	cp ${TZCODEDIR}/tzfile.h ${S}
#	cp ${SYSTOOLSDIR}/*.o ${S}
#	cp ${TZCODEDIR}/*.3 ${S}
#	cp ${TZCODEDIR}/*.5 ${S}
#	cp ${TZCODEDIR}/*.8 ${S}
}

do_compile () {
    oe_runmake ${MAKE_FLAGS}
}


do_install () {
    oe_runmake ${MAKE_FLAGS} install
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/tzdata.pc ${D}${libdir}/pkgconfig/
    rm -rf ${D}/usr/etc
    rm -rf ${D}/usr/man

}

FILES_${PN} = "${datadir}/zoneinfo-posix \
	${datadir}/zoneinfo    \
	${datadir}/zoneinfo-leaps  \
"

