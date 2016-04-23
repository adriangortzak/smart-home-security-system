<?php

namespace App\Http\Controllers;

use App\Http\Requests;
use Illuminate\Http\Request;
use Auth;

class HomeController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        $this->middleware('auth');
    }

    /**
     * Show the application dashboard.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        return view('home');
    }


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
	//User
	$user = Auth::user()->name;;
   


	$message = $user . ":" . $message;

		if(!socket_send($sock, $message, strlen($message), 0))
		{
	       		 $errorcode = socket_last_error();
	       		 $errormsg = socket_sterror($errorcode);
	        	die("Could not send data: [$errorcode] $errormsg \n");
		}
			echo "Message send successfully \n";
		}
	}





public function checkAlarmStatus(){
if(!($sock = socket_create(AF_INET, SOCK_STREAM, 0)))
{
	$errorcode = socket_last_error();
	$errormsg = socket_strerror($errorcode);
	die("Couldn't create socket: [$errorcode] $errormsg \n");
}
//Connect socket to remote server
if(!socket_connect($sock, '127.0.0.1', 1967))
{
	$errorcode = socet_last_error();
	$errormsg = socket_strerror($errorcode);
	die("Could not connect: [$errorcode] $errormsg \n");
}
$message = "check alarm status\n";
$user = Auth::user()->name;;
$message = $user . ":" . $message;

$status = socket_sendto($sock, $message, strlen($message), MSG_EOF, '127.0.0.1', '1967');
if($status !== FALSE)
{
    $message = '';
    $next = '';
    while ($next = socket_read($sock, 4096))
    {
        $message .= $next;
    }
    echo $message;
}
else
{
    echo "Failed";
}
socket_close($sock);
 }
}
