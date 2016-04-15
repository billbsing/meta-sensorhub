require gnutls.inc

SRC_URI += "file://correct_rpl_gettimeofday_signature.patch \
            file://use-pkg-config-to-locate-zlib.patch \
           "

# file://configure.ac-fix-sed-command.patch

SRC_URI[md5sum] = "fe9a0dc5adf205122f01a3e7dac5f8dd"
SRC_URI[sha256sum] = "6a32c2b4acbd33ff7eefcbd1357009da04c94c60146ef61320b6c076b1bdf59f"

