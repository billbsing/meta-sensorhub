#!/bin/sh
PPP_ERROR_FILE=/etc/ppp/connect-errors

if [ -f $PPP_ERROR_FILE ]; then
	echo "removing old ppp error file"
	rm $PPP_ERROR_FILE
fi

RESOLV_FILE=/var/run/connman/resolv.conf
if [ ! -f $RESOLV_FILE ]; then
		echo "nameserver 8.8.8.8
		nameserver 4.4.4.4" > $RESOLV_FILE
fi


echo "setting up iptables"


iptables -P INPUT DROP
iptables -P FORWARD DROP
iptables -P OUTPUT ACCEPT

iptables -A INPUT -i lo -j ACCEPT
iptables -A INPUT -p udp --dport 53 -j ACCEPT
iptables -A INPUT -p tcp --dport 53 -j ACCEPT
iptables -A INPUT -p udp --dport 67 -j ACCEPT
iptables -A INPUT -p tcp --dport 67 -j ACCEPT
iptables -A INPUT -p udp --dport 5353 -j ACCEPT
iptables -A INPUT -p udp --dport 53791 -j ACCEPT
iptables -A INPUT -m state --state ESTABLISHED,RELATED -j ACCEPT


iptables -A FORWARD -i tether -o eth0 -m conntrack --ctstate RELATED,ESTABLISHED,NEW -j ACCEPT
iptables -A FORWARD -i eth0 -o tether -m conntrack --ctstate RELATED,ESTABLISHED -j ACCEPT
iptables -A POSTROUTING -t nat -o eth0 -j MASQUERADE

iptables -A FORWARD -i tether -o ppp0 -m conntrack --ctstate RELATED,ESTABLISHED,NEW -j ACCEPT
iptables -A FORWARD -i ppp0 -o tether -m conntrack --ctstate RELATED,ESTABLISHED -j ACCEPT
iptables -A POSTROUTING -t nat -o ppp0 -j MASQUERADE

systemctl restart avahi-daemon
