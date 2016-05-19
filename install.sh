#!/bin/bash
echo "---------------------------------------------"
echo "| starting to install Smart Home Security System |"
echo "---------------------------------------------"
echo ""
#echo "Moving web config"
#cp   web/config/SmartHomeLarmSystem.conf /etc/apache2/conf-enabled/
#echo "IncludeOptional conf-enable/SmartHomeLarmSystem.conf" >> /etc/apache2/apache2.conf 
#echo "Restarting webserver"
#systemctl status apache2.service
echo "Installing net.jstick.api as local maven repository"
mvn install:install-file -Dfile=Server/jstick-api-1.7.jar -DgroupId=net.jstick.api -DartifactId=jstick -Dversion=1.0 -Dpackaging=jar
