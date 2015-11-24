--- luasocket-3.0-rc1/src/makefile	2014-08-24 18:14:26.920608924 +0800
+++ luasocket-3.0-rc1/src/makefile	2014-08-24 18:11:02.328593343 +0800
@@ -144,7 +144,7 @@
 # for Linux
 SO_linux=so
 O_linux=o
-CC_linux=gcc
+CC_linux=${GCC_linux}
 DEF_linux=-DLUASOCKET_$(DEBUG) -DLUA_$(COMPAT)_MODULE \
 	-DLUASOCKET_API='__attribute__((visibility("default")))' \
 	-DUNIX_API='__attribute__((visibility("default")))' \
@@ -152,7 +152,7 @@
 CFLAGS_linux= -I$(LUAINC) $(DEF) -pedantic -Wall -Wshadow -Wextra \
 	-Wimplicit -O2 -ggdb3 -fpic -fvisibility=hidden
 LDFLAGS_linux=-O -shared -fpic -o 
-LD_linux=gcc
+LD_linux=${GCC_linux}
 SOCKET_linux=usocket.o
 
 #------
