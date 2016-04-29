<?php

namespace App\Http\Controllers;

use App\Http\Requests;
use Illuminate\Http\Request;
use Auth;
use App\User;
use App\ServerSetting;
use Validator;

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
	$this->socketSend($message);
    }
}
public function checkIfServerIsAlive(){
	echo $this->socketSendAndReceive("ServerStatus\n");
}
public function checkSiren(){
	echo $this->socketSendAndReceive("SirenStatus\n");
}

public function checkAlarmStatus(){
echo $this->socketSendAndReceive("check alarm status\n");
 }
public function triggerCount(){
	getTriggerCount();
} 
	
public function internetStatus(){
	checkInternetConnections();
}

public function userCount(){
	getUserCount();
}
public function ServerCheck(){
	$this->checkIfServerIsAlive();
}

public function getWeather(){
	//Get variables from database
	$city = ServerSetting::where('Setting','=','city')->first();
	$country = ServerSetting::where('Setting','=','countryCode')->first();
	$api = ServerSetting::where('Setting','=','openWeatherMapAPI')->first();

	//Retrieve data from OpenWeatherMap.org
	$url="http://api.openweathermap.org/data/2.5/weather?q=".$city->Value.",".$country->Value."&units=metric&cnt=7&lang=en&APPID=$api->Value";
	
	//Extract data and return temp and city
	$json=file_get_contents($url);
	$data=json_decode($json,true);
	echo '{"temp":"'. round($data['main']['temp'])." C".'"'.',"city":'.'"'.$city->Value.'"}';
}

public function trigger(){
	$message = "trigger\n";
	$this->socketSend($message);
}

	function socketSend($message)
	{
		if (!($sock = socket_create(AF_INET, SOCK_STREAM, 0))) {
			$errorcode = socket_last_error();
			$errormsg = socket_strerror($errorcode);
			die("Couldn't create socket: [$errorcode] $errormsg \n");
		}
		echo "socket created \n";
		//Connect socket to remote server
		if (!socket_connect($sock, '127.0.0.1', 1967)) {
			$errorcode = socet_last_error();
			$errormsg = socket_strerror($errorcode);
			die("Could not connect: [$errorcode] $errormsg \n");
		}
		echo "Connection established \n";

		//Send the message to the server
		//User
		$user = Auth::user()->name;;

		$message = $user . ":" . $message;

		if (!socket_send($sock, $message, strlen($message), 0)) {
			$errorcode = socket_last_error();
			$errormsg = socket_sterror($errorcode);
			die("Could not send data: [$errorcode] $errormsg \n");
		}
		echo "Message send successfully \n";
	}

	function socketSendAndReceive($message){
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
		}
		else
		{
			echo "Failed";
		}
		socket_close($sock);
		return $message;
	}

	public function removeUser($email)
	{
		User::where('email', $email)->delete();
	}

	public  function createUser(Request $request){

		$data[] = array(
			'name' => $request->input('name'),
			'email' => $request->input('email'),
			'password' => $request->input('password')

		);
		$valid = Validator::make($data, [
			'name' => 'required|max:255',
			'email' => 'required|email|max:255|unique:users',
			'password' => 'required|min:6|confirmed'
		]);
		if($valid){

			if(User::all()->where('name',$request->input('name'))->count() == 0) {
				$password = $request->input('password');
				User::create([
					'name' => $request->input('name'),
					'email' => $request->input('email'),
					'password' => bcrypt($password),
				]);

				echo "Success";
			}else{
				echo "Exist";
			}
		}else{
			echo "NotValid";
		}

	}
}
