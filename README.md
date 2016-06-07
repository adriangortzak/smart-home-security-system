# Smart Home Security System

This solution makes it possible for a user to control and check the status of various
security peripherals through a web based interface. The interface may be accessed via
a computer inside the user’s home (e.g. a touchscreen) or a remotely connected client
(smartphone, laptop or otherwise), which would allow the user to control and monitor
the security system from any device (with a browser and an Internet connection) .
The system is a framework for intelligent security applications and may be
implemented in an almost infinite number of ways. In our implementation, which
serves as a proof of concept, it should be possible for the user to e.g. be notified via
mail and/or smartphone notification whenever any of the sensors are triggered and the
security system is active. It should also be possible to activate and deactivate the
system via the web interface.

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
2. [Set static IP-adress](https://github.com/Adddrian/SmartHomeSecuritySystem/wiki/Set-static-IP-adress-on-your-Raspberry-Pi)
3. [Install web server](https://github.com/Adddrian/SmartHomeSecuritySystem/wiki/Setting-up-an-Apache-web-server-on-a-Raspberry-Pi)
4. [Set Mod-Rewrite](https://github.com/Adddrian/SmartHomeSecuritySystem/wiki/Mod-Rewrite)
5. [Install database](https://github.com/Adddrian/SmartHomeSecuritySystem/wiki/MySQL-setup)
6. Install git -> sudo apt-get install git

###

1. `cd /srv/`
2. `git clone https://github.com/Adddrian/SmartHomeSecuritySystem.git`
3. `cd SmartHomeSecuritySystem/web/LarmWebServer `
4. `composer install`
5. `php artisan key:generate`
6. `uncomment ;extension=php_sockets.dll from php.ini (windows only)`
7. `Change from java 7 to java 8`
8. `point webserber to /srv/SmartHomeSecuritySystem/web/LarmWebServer/public`
9. `cp .env.example .env` 
10. `nano /srv/SmartHomeSecuritySystem/web/LarmWebServer/.env `
11. Add database login and email and password
 


## Usage

Visit you ip/Domain and login with the standard username "root@example.com" and password "awq12ersdsa"

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin  my-new-feature`
5. Submit a pull request :D

## History

The possibilities to remotely control and configure electrical appliances and other
devices in the home have increased with modern ICT technology . There is a market
for easily operated and intuitive solutions for appliance surveillance, comfort­ and
security accommodation as well as energy saving. Much of this is exists in the
concept known as Internet of Things (IoT).

Students invest in this project to learn about modern project methods for ICT­projects,
but also to develop their technical capabilities.

Sweden receives modern ICT­engineers that cater to the future competence need and
increase the country's competitiveness, internal market and export.


## Credits 

- [Laravel](https://github.com/laravel/laravel)
- [Tellstick api - jstick](https://github.com/juppinet)

## License.

The MIT License (MIT)
Copyright (c) 2016 Grupp 13

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE..
