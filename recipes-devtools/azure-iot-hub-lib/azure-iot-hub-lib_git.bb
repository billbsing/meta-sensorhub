
DESCRIPTION = "Azure IotHub client"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/sdk/LICENSE;md5=4283671594edec4c13aeb073c219237a"
HOMEPAGE = "https://github.com/Azure/azure-iot-sdks"

PR = "r0"


DEPENDS = "curl"

# SRCREV = "${AUTOREV}"
SRCREV_sdk = "e06ad8be39e4c3bb0abe3e6ebaeed7e3bdb4edd2"
SRCREV_shared = "75fc093c63ab4c9c808eca63c6ec658dae68217b" 
SRCREV_amqp = "cec604402be8c3b18f643d706d2133cab8ab3e0d"
SRCREV_mqtt = "d16b151c5515e801dfd88a7e573f84ec10c6406e"



SRC_URI = "git://github.com/Azure/azure-iot-sdks.git;name=sdk;destsuffix=sdk \
	git://github.com/Azure/azure-c-shared-utility.git;name=shared;destsuffix=shared \
	git://github.com/Azure/azure-uamqp-c.git;name=amqp;destsuffix=amqp \
	git://github.com/Azure/azure-umqtt-c.git;name=mqtt;destsuffix=mqtt \
	file://azure-iot-hub.pc \
"
inherit cmake


# INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
# INHIBIT_PACKAGE_STRIP = "1"

SRC_URI[md5sum] = "b8ee7562aeea1df2876c2cc9938025ca"
SRC_URI[sha256sum] = "e55f43331f87c693e71bcaa9bd3e2e5002a8b0228908381a49c1f56d22746e0a"

S = "${WORKDIR}/sdk/c"
SYSROOTS = "${TMPDIR}/sysroots/${MACHINE}"
# SYSROOT_LIB = "${SYSROOTS}${libdir}/azure-iot-hub"
# SYSROOT_INC = "${SYSROOTS}${includedir}/azure-iot-hub"
INSTALL_LIB = "${D}${libdir}/azure-iot-hub"
INSTALL_INC = "${D}${includedir}/azure-iot-hub"

EXTRA_OECMAKE="-DcompileOption_C:STRING="-fPIC" \
    -Drun_e2e_tests:BOOL=OFF\
    -Drun_longhaul_tests=OFF \
    -Duse_amqp:BOOL=ON \
    -Duse_http:BOOL=ON \
    -Duse_mqtt:BOOL=ON \ 
    -Dskip_unittests:BOOL=OFF \
    -Dno_logging:BOOL=ON \
"

do_patch () {
    cp -r ${WORKDIR}/shared/* ${WORKDIR}/sdk/c/azure-c-shared-utility/
    cp -r ${WORKDIR}/amqp/* ${WORKDIR}/sdk/c/azure-uamqp-c/
    cp -r ${WORKDIR}/shared/* ${WORKDIR}/sdk/c/azure-uamqp-c/azure-c-shared-utility/
    cp -r ${WORKDIR}/mqtt/* ${WORKDIR}/sdk/c/azure-umqtt-c/
    cp -r ${WORKDIR}/shared/* ${WORKDIR}/sdk/c/azure-umqtt-c/azure-c-shared-utility/
}

do_install () {

    install -d ${INSTALL_LIB}

    cp ${WORKDIR}/build/azure-umqtt-c/libumqtt.a ${INSTALL_LIB}
    cp ${WORKDIR}/build/azure-c-shared-utility/c/libaziotsharedutil.a ${INSTALL_LIB}
    cp ${WORKDIR}/build/azure-c-shared-utility/c/testtools/umock_c/libumock_c.a ${INSTALL_LIB}
    cp ${WORKDIR}/build/azure-c-shared-utility/c/testtools/micromock/libmicromock_ctest.a ${INSTALL_LIB}
    cp ${WORKDIR}/build/azure-c-shared-utility/c/testtools/ctest/libctest.a ${INSTALL_LIB}
    cp ${WORKDIR}/build/azure-uamqp-c/libuamqp.a ${INSTALL_LIB}
    cp ${WORKDIR}/build/serializer/libserializer.a ${INSTALL_LIB}
    cp ${WORKDIR}/build/iothub_client/libiothub_client_amqp_transport.a ${INSTALL_LIB}
    cp ${WORKDIR}/build/iothub_client/libiothub_client_http_transport.a ${INSTALL_LIB}
    cp ${WORKDIR}/build/iothub_client/libiothub_client.a ${INSTALL_LIB}
    cp ${WORKDIR}/build/iothub_client/libiothub_client_mqtt_transport.a ${INSTALL_LIB}

    install -d ${INSTALL_INC}/iothub_client
    cp -r ${S}/iothub_client/inc ${INSTALL_INC}/iothub_client

    install -d ${INSTALL_INC}/azure-c-shared-utility/c
    cp -r ${S}/azure-c-shared-utility/c/inc ${INSTALL_INC}/azure-c-shared-utility/c

    install -d ${INSTALL_INC}/serializer
    cp -r ${S}/serializer/inc ${INSTALL_INC}/serializer

    install -d ${INSTALL_INC}/azure-uamqp-c
    cp -r ${S}/azure-uamqp-c/inc ${INSTALL_INC}/azure-uamqp-c

    install -d ${INSTALL_INC}/azure-umqtt-c
    cp -r ${S}/azure-umqtt-c/inc ${INSTALL_INC}/azure-umqtt-c


}    
# PACKAGES = "${PN}-staticdev"

ALLOW_EMPTY_${PN}-dev = "1"

FILES = ""

FILES_${PN}-staticdev = "/usr/lib/azure-iot-hub \
"

