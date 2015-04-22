#!/usr/bin/env sh
# Add ip for MicroFlex drive on $I

I='eth0'

BINDIR='/usr/bin'

SRC='192.168.100.1'
TARGET='192.168.100.2'

$BINDIR/ip a add $SRC/32 dev $I
$BINDIR/ip r add $TARGET/32 dev $I via $SRC

exit 0
