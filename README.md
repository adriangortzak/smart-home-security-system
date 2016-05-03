# Smart Home Larm System

[![Latest Stable Version](https://poser.pugx.org/laravel/framework/v/stable.svg)](https://packagist.org/packages/laravel/framework)
[![Latest Unstable Version](https://poser.pugx.org/laravel/framework/v/unstable.svg)](https://packagist.org/packages/laravel/framework)

Ett smart hemlarm med både lokal och remote kommunikation. Larmet triggas av att en person går förbi en trådlös sensor. Om larmet går av registreras det på ett webgränssnitt, och med ljud och ljus på den lokala enheten. Sensor, ljud och lampor kopplas till en styrdator via Tellstick Duo.

## Requirements
##### Following the install guide will give you all Requirements

-  Java >= v.8
-  PHP >= 5.5.9
- OpenSSL PHP Extension
-  PDO PHP Extension
-  Mbstring PHP Extension
-  Tokenizer PHP Extension
-  git

## Installation
##### Installing requirements (If not allready installed)

1. [Install Rasbian](https://www.raspberrypi.org/documentation/installation/installing-images/)
2. [Set static IP-adress](https://github.com/Adddrian/SmartHomeLarmSystem/wiki/Set-static-IP-adress-on-your-Raspberry-Pi)
3. [Install web server](https://github.com/Adddrian/SmartHomeLarmSystem/wiki/Set-static-IP-adress-on-your-Raspberry-Pi)
4. [Set Mod-Rewrite](https://github.com/Adddrian/SmartHomeLarmSystem/wiki/Mod-Rewrite)
5. [Install database](https://github.com/Adddrian/SmartHomeLarmSystem/wiki/MySQL-setup)
6. Install git -> sudo apt-get install git

###

1. `cd /srv/`
2. `git clone https://github.com/Adddrian/SmartHomeLarmSystem.git`
3. `cd SmartHomeLarmSystem/web/LarmWebServer `
4. `composer install`
5. `sudo php artisan key:generate`
6. `uncomment ;extension=php_sockets.dll from php.ini`
7. `Change from java 7 to java 8`
8. `point webserber to /srv/SmartHomeLarm/web/LarmWebServer/public`
9. `mv .env.example .env` 
10. `nano /srv/SmartHomeLarm/web/LarmWebServer/.env `
11. Add database login and email and password
 


## Usage

Visit you ip/Domain and login with the standard username "root" and password "awq12ersdsa"

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin  my-new-feature`
5. Submit a pull request :D

## History

TODO: Write history

## Credits

TODO: Write credits

## License

Copyright 2016 Grupp 13

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
