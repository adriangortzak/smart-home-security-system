#!/bin/bash
echo "---------------------------------------------"
echo "| starting to install Smart Home Security System |"
echo "---------------------------------------------"
echo ""
echo "Installing net.jstick.api as local maven repository"
mvn install:install-file -Dfile=Server/jstick-api-1.7.jar -DgroupId=net.jstick.api -DartifactId=jstick -Dversion=1.0 -Dpackaging=jar

#Install Laravel
cd /srv/SmartHomeSecuritySystem/web/LarmWebServer/
sed -i 's/\/usr\/sbin\/nologin/\/bin\/bash/' /etc/passwd
su -c 'composer install' www-data
su -c 'php artisan key:generate' www-data
sed -i 's/\/bin\/bash/\/usr\/sbin\/nologin/' /etc/passwd

#Install mysql database
cd /srv/SmartHomeSecuritySystem/Server
mysql --user=root -e "CREATE DATABASE SHSS;"
echo -n "Enter password for shss mysql account and press [ENTER]: "
read password
mysql --user=root -e "GRANT ALL PRIVILEGES ON SHSS.* To 'shss'@'localhost' IDENTIFIED BY '$password';"
mysql -u root SHSS < SHSS.sql
sed -i 's/DB_PASSWORD=.*$/DB_PASSWORD=$password/' ../web/LarmWebServer/.env

#Secure mysql installation
echo -n "Enter password for root mysql account and press [ENTER]: "
read MYSQL_ROOT_PASSWORD
SECURE_MYSQL=$(expect -c "
set timeout 10
spawn mysql_secure_installation
expect \"Enter current password for root (enter for none):\"
send \"\r\"
expect \"Change the root password?\"
send \"y\r\"
expect \"New password:\"
send \"$MYSQL_ROOT_PASSWORD\r\"
expect \"Re-enter new password:\"
send \"$MYSQL_ROOT_PASSWORD\r\"
expect \"Remove anonymous users?\"
send \"y\r\"
expect \"Disallow root login remotely?\"
send \"y\r\"
expect \"Remove test database and access to it?\"
send \"y\r\"
expect \"Reload privilege tables now?\"
send \"y\r\"
expect eof
")
echo "Updated mysql settings"
echo "configuring phpMyAdmin"
DEBIAN_FRONTEND=noninteractive apt-get install phpmyadmin -y
/usr/sbin/pma-configure
sed -i "s/\$dbuser='';/\$dbuser='phpmyadmin';/" /etc/phpmyadmin/config-db.php
sed -i "s/\$dbpass='';/\$dbpass='$MYSQL_ROOT_PASSWORD';/" /etc/phpmyadmin/config-db.php
sed -i "s/\$dbname='';/\$dbname='phpmyadmin';/" /etc/phpmyadmin/config-db.php
ln -s /etc/phpmyadmin/apache.conf /etc/apache2/conf-available/phpmyadmin.conf
ln -s /etc/apache2/conf-available/phpmyadmin.conf /etc/apache2/conf-enabled/phpmyadmin.conf
/usr/sbin/pma-secure
echo "Updated phpmyadmin settings"
echo "Restarting apache"
sv restart apache
echo "Done"
