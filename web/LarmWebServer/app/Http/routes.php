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
//Start
Route::get('/',['middleware' => 'auth', function () {
    return view('home');
}]);

//--------------------------------------//
//Admin User                            //
//--------------------------------------//
Route::group(['middleware' => ['web','admin']], function () {
    //Admin page
    Route::get('removeSensor/{id}', 'HomeController@removeSensor');
    Route::post('addSensor', 'HomeController@addSensor');
    Route::get('admin', function () {
        return view('admin');
    });
    Route::get('userManagement', function () {
        userConfigList();
    });

    //Pending time get/set methods
    Route::get('getPendingTime', function () {
        echo getSetting('pendingTime');
    });
    Route::get('setPendingTime/{value}', function ($value) {
        setSetting('pendingTime', $value);
    });

    //Notification interval get/set methods
    Route::get('getNotificationInterval', function () {
        echo getSetting('notificationInterval');
    });
    Route::get('setNotificationInterval/{value}', function ($value){
        setSetting('notificationInterval', $value);
    });

    //Thread get/set methods
    Route::get('getThreadPool', function () {
        echo getSetting('threadPool');
    });
    Route::get('setThreadPool/{value}', function ($value){
        setSetting('threadPool', $value);
    });

    Route::get('getOpenWeatherSettings', function(){
        $city = getSetting('city');
        $countryCode = getSetting('countryCode');
        $api = getSetting('openWeatherMapAPI');
        $json = '{'
               .'"city" :"'. $city .'",'
               .'"countryCode" :"' . $countryCode. '",'
               .'"owmAPI" :"' . $api
               .'"}';
        echo $json;
    });

    //Get weather information
    Route::post('updateOpenWeatherSettings', 'HomeController@updateOpenWeatherSettings');

    //Get sensors
    Route::get('getSensors', function () {
        getMyTriggers();
    });

    //Change sensor settings in database
    Route::get('updateSensors/{id}/{value}/{checkbox}', function ($id, $value, $checkbox){
        updateSensors($id,$value,$checkbox);
    });

    //Remove user
    Route::get('removeUser/{email}', 'HomeController@removeUser');
    // Create new user
    Route::post('newUser', 'HomeController@createUser');

    Route::post('updateGroupMembership', 'HomeController@updateGroupMembership');

    //Restart server
    Route::get('restart', 'HomeController@restart');

});

//---------------------------------------//
//History                                //
//---------------------------------------//
Route::group(['middleware' => ['web','history']], function () {
    //History page
    Route::get('histories', function () {
        return view('history');
    });
});
//---------------------------------------//
//Control                                //
//---------------------------------------//
Route::group(['middleware' => ['web','control']], function () {
    //Change alarm status
    Route::get('alarm/{state}', 'HomeController@changeAlarmStatus');
    //Trigger alarm on server
    Route::get('trigger', 'HomeController@trigger');
});

//---------------------------------------//
//All user                               //
//---------------------------------------//
use App\Http\Controllers\HomeController;
Route::group(['middleware' => ['web']], function () {
    //Dashboard page
    Route::get('home', 'HomeController@viewHome'); 
    //Get alarm status
    Route::get('alarmStatus', 'HomeController@checkAlarmStatus');

    //Dasboard Update
    Route::get('sirenStatus', 'HomeController@checkSiren');
    Route::get('internetStatus', 'HomeController@internetStatus');
    Route::get('userCount', 'HomeController@userCount');
    Route::get('ServerCheck', 'HomeController@ServerCheck');
    Route::get('triggerCount', 'HomeController@triggerCount');
    Route::get('getWeather', 'HomeController@getWeather');

    //Settings page
    Route::get('settings', function () {
	return view('settings');
    });
    Route::get('removeNotification/{id}', 'HomeController@removeNotification');
    Route::get('getNotifications', function () {
	getMyNotifications();
    });
    Route::post('addNotification', 'HomeController@addNotification');
    Route::get('updateNotifications/{id}/{value}/{checkbox}', function ($id, $value, $checkbox){
	updateNotifications($id,$value,$checkbox);	
    });

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

// Password Reset Routes...
Route::get('password/reset/{token?}', 'Auth\PasswordController@showResetForm');
Route::post('password/email', 'Auth\PasswordController@sendResetLinkEmail');
Route::post('password/reset', 'Auth\PasswordController@reset');

