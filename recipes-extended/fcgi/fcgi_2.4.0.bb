DESCRIPTION = "FastCGI is a protocol for interfacing interactive programs with a web server."
HOMEPAGE = "https://fastcgi-archives.github.io"
LICENSE = "OSL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE.TERMS;md5=e3aacac3a647af6e7e31f181cda0a06a"

# SRC_URI = "http://fossies.org/linux/www/old/${BP}.tar.gz 


SRC_URI = "https://github.com/LuaDist/fcgi/archive/${PV}.tar.gz \
           file://Fix_EOF_not_declared_issue.patch \
           file://add_foreign_to_AM_INIT_AUTOMAKE.patch \
"

SRC_URI[md5sum] = "426e1325c3df2b91eee2dba1988d9bf0"
SRC_URI[sha256sum] = "6596975a3bd3ddd1995cdeb66a503d2b5d7277f71038511634d7d3609e4588bb"

inherit autotools

EXTRA_OECONF = "CXXFLAGS='--whole-archive'"

PARALLEL_MAKE = ""
