#!/bin/sh


RESOLV_FILE=/var/run/connman/resolv.conf
if [ ! -f $RESOLV_FILE ]; then
		echo "nameserver 8.8.8.8
		nameserver 4.4.4.4" > $RESOLV_FILE
fi

iptables -A FORWARD -i tether -o ppp0 -j ACCEPT
iptables -t nat -A POSTROUTING -o ppp0 -j MASQUERADE
iptables -A FORWARD -m conntrack --ctstate RELATED,ESTABLISHED -j ACCEPT

iptables -A OUTPUT -p udp --dport 53 -j ACCEPT
iptables -A INPUT  -p udp --sport 53 -j ACCEPT
iptables -A OUTPUT -p tcp --dport 53 -j ACCEPT
iptables -A INPUT  -p tcp --sport 53 -j ACCEPT

