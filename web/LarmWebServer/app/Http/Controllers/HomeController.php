<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;

class HomeController extends Controller
{
    //
	public function changeAlarmStatus($state){
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
	
	if ($state == "ON") {
	    $message = "turn on alarm";
	    $valid = "true";
	} elseif ($state == "OFF") {
	    $message = "turn off alarm";
	    $valid = "true";
	} else {
	    $message =  "error";
	    $valid = "false";
	}

	//Send the message to the server
	if($valid == "true"){
		if(!socket_send($sock, $message, strlen($message), 0))
		{
	       		 $errorcode = socket_last_error();
	       		 $errormsg = socket_sterror($errorcode);

	        	die("Could not send data: [$errorcode] $errormsg \n");
		}
			echo "Message send successfully \n";
		}
	}
}
