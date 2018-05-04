#!/bin/sh

DAEMON=/usr/bin/zipgatewayd
PIDFILE=/var/run/ofonod.pid
DESC="ZIP Gateway daemon"
CONFIG_FOLDER=/etc/zipgateway
DATA_FOLDER=/var/run/zipgateway

if [ -f /etc/default/zipgateway ] ; then
        . /etc/default/zipgateway
fi

set -e

do_start() {

	if [[ -f ${DAEMON} ]]; then
	    echo "Starting ZIP Gateway"
	    iptables --policy INPUT ACCEPT
	    systemctl stop serial-getty@ttyS1.service
	    #To handle the case when eeprom.dat is not accessible on hard reboot
	    echo -n >> ${CONFIG_FOLDER}/eeprom.dat
	    if [ $? -eq 0 ];then
		  ifconfig br-lan > /dev/null 2>&1 || {
			brctl addbr br-lan
		  }
		  ifconfig br-lan up
		  ifconfig br-lan 192.168.168.1
		  if [ ! -d ${DATA_FOLDER} ]; then
		      mkdir ${DATA_FOLDER}
		  fi
		  /usr/sbin/udhcpd ${CONFIG_FOLDER}/udhcpd.conf
		  ${DAEMON} -c ${CONFIG_FOLDER}/zipgateway.cfg
	    else
		  echo "Not able to open eeprom.dat file"
	    fi
	else
	    echo "Zipgateway is not present!"
	fi
}

do_stop() {
	killall ${DAEMON} 
	killall udhcpd
	ifconfig br-lan down
	brctl delbr br-lan
	systemctl start serial-getty@ttyS1.service
	ifconfig tap0 down
}

case "$1" in
  start)
        echo "Starting $DESC"
        do_start
        ;;
  stop)
        echo "Stopping $DESC"
        do_stop
        ;;
  restart|force-reload)
        echo "Restarting $DESC"
        do_stop
        sleep 1
        do_start
        ;;
  *)
        echo "Usage: $0 {start|stop|restart|force-reload}" >&2
        exit 1
        ;;
esac

exit 0

