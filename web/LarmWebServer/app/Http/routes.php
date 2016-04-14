<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/

Route::get('/', function () {
   return view('login');
});


Route::get('start', function () {
   return view('start');
});


Route::post('alarm', function () {
if(!($sock = socket_create(AF_INET, SOCK_STREAM, 0)))
{
        $errorcode = socket_last_error();
        $errormsg = socket_strerror($errorcode);

        die("Couldn't create socket: [$errorcode] $errormsg \n");
}
 echo "socket created \n";

//Connect socket to remote server
if(!socket_connect($sock, '127.0.0.1', 1967))
{
        $errorcode = socet_last_error();
        $errormsg = socket_strerror($errorcode);

        die("Could not connect: [$errorcode] $errormsg \n");
}
 echo "Connection established \n";

//if ($state == ON) {
    $message = "turn on alarm";
//} elseif ($state == OFF) {
//    $message = "turn off alarm";
//} else {
//    $message =  "test";
//}


//Send the message to the server
if(!socket_send($sock, $message, strlen($message), 0))
{
        $errorcode = socket_last_error();
        $errormsg = socket_sterror($errorcode);

        die("Could not send data: [$errorcode] $errormsg \n");
}

echo "Message send successfully \n";




});

