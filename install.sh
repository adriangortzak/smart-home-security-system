#!/bin/bash
echo "---------------------------------------------"
echo "| starting to install Smart Home Larm System |"
echo "---------------------------------------------"
echo ""
echo "Moving web config"
cp   web/config/SmartHomeLarmSystem.conf /etc/apache2/conf-enabled/
echo "IncludeOptional conf-enable/SmartHomeLarmSystem.conf" >> /etc/apache2/apache2.conf 
echo "Restarting webserver"
systemctl status apache2.service
