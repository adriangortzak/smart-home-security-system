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

Route::get('home', function () {
$alarmStatus = HomeController@checkAlarmStatus;
   return view('home');
});

Route::get('alarm/{state}', 'HomeController@changeAlarmStatus');
Route::get('alarmStatus', 'HomeController@checkAlarmStatus');

