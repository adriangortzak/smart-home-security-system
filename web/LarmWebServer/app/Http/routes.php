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
Route::get('/',['middleware' => 'auth', function () {
   return view('home');
}]);

use App\Http\Controllers\HomeController;
Route::group(['middleware' => ['web']], function () {

//Dashboard page
	Route::get('home', function () {
		return view('home');
	});
	//Dashboard settings
	Route::get('alarmStatus', 'HomeController@checkAlarmStatus');
	Route::get('alarm/{state}', 'HomeController@changeAlarmStatus');
	Route::get('trigger', function () {
		$controller = new HomeController();
		$controller->trigger();
	});
	Route::get('alarm/{state}', 'HomeController@changeAlarmStatus');

		//Dasboard Update
	Route::get('alarmStatus', 'HomeController@checkAlarmStatus');
	Route::get('internetStatus', 'HomeController@internetStatus');
	Route::get('userCount', 'HomeController@userCount');
	Route::get('ServerCheck', 'HomeController@ServerCheck');
	Route::get('triggerCount', 'HomeController@triggerCount');
	Route::get('getWeather', 'HomeController@getWeather');


	Route::post('newUser', 'HomeController@createUser');

//Settings page
	Route::get('settings', function () {
		return view('settings');
	});

//History page
	Route::get('histories', function () {
		return view('history');
	});
//Admin page
	Route::get('admin', function () {
		return view('admin');
	});
	   Route::get('userManagement', function () {
                userConfigList();
        });
	Route::get('getSensors', function () {
		getMyTriggers();
	});


	Route::get('removeUser/{email}', 'HomeController@removeUser');

//Lock screen
	Route::get('lock/{email}', function ($email) {
		Auth::logout();
		return View::make('lock',['email' => $email]);
	});
});

// Authentication Routes...
	Route::get('login', 'Auth\AuthController@showLoginForm');
	Route::post('login', 'Auth\AuthController@login');
	Route::get('logout', 'Auth\AuthController@logout');

// Registration Routes...
//	Route::post('register', 'Auth\AuthController@register');

// Password Reset Routes...
	Route::get('password/reset/{token?}', 'Auth\PasswordController@showResetForm');
	Route::post('password/email', 'Auth\PasswordController@sendResetLinkEmail');
	Route::post('password/reset', 'Auth\PasswordController@reset');

