
DESCRIPTION = "Zware Library Code from Sigma"


do_install_append(){
        install -d ${D}${libdir}
        install -m 0750 ${S}/${PN}/src/zwave/hcapi/lib/libzip_api.so ${D}${libdir}
        install -m 0750 ${S}/${PN}/src/zwave/hcapi/src/libzip_ctl.so ${D}${libdir}
}



