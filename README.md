# Smart Home Security System

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

1. Install Rasbian
2. [Set static IP-adress](https://github.com/Adddrian/SmartHomeLarmSystem/wiki/Set-static-IP-adress-on-your-Raspberry-Pi)
3. [Install web server](https://github.com/Adddrian/SmartHomeLarmSystem/wiki/Set-static-IP-adress-on-your-Raspberry-Pi)
4. [Set Mod-Rewrite](https://github.com/Adddrian/SmartHomeLarmSystem/wiki/Mod-Rewrite)
5. [Install database](https://github.com/Adddrian/SmartHomeLarmSystem/wiki/MySQL-setup)
6. [Install git]()

###

1. `cd /srv/`
2. `git clone https://github.com/Adddrian/SmartHomeLarmSystem.git`
3. `cd SmartHomeLarmSystem `
6. `./install.sh `
7. go to your-ip/smartlarm/


## Usage

TODO: Write usage instructions

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin  my-new-feature`
5. Submit a pull request :D

## History

TODO: Write history

## Credits

- [Laravel](https://github.com/laravel/laravel)
- [Tellstick api - jstick](https://github.com/juppinet)

## License.

The MIT License (MIT)
Copyright (c) 2016 Grupp 13

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE..
