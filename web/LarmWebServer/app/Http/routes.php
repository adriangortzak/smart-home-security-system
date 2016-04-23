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

Route::get('settings',['middleware' => 'auth', function () {
   return view('settings');
}]);

Route::get('admin',['middleware' => 'auth', function () {
   return view('admin');
}]);


Route::get('home',['middleware' => 'auth', function () {
   return view('home');
}]);

Route::get('histories',['middleware' => 'auth', function () {
   return view('history');
}]);



Route::get('users',['middleware' => 'auth', function () {
   $users = App\User::all();
foreach($users  as $user){
echo '<div class="desc">';
                      	echo '<div class="thumb">';
                      		echo '<img class="img-circle" src="assets/img/ui-divya.jpg" width="35px" height="35px" align="">';
                      	echo '</div>';
                      	echo '<div class="details">';
                      		echo '<p>' . $user->name . '<br>';
                      		   echo '<muted><b>Email:</b>' .$user->email . '</muted>';
                      		echo '</p>';
                      	echo '</div>';
                      echo '</div>';
}
}]);

Route::get('history',['middleware' => 'auth', function () {
$history = App\history::orderBy('date','desc')->take(5)->get();
foreach($history  as $story){
		echo '<div class="desc">';
                      echo '<div class="thumb">';
                      		echo '<span class="badge bg-theme"><i class="fa fa-clock-o"></i></span>';
                      	echo '</div>';
                      	echo '<div class="details">';
                      		echo '<p><muted>' . $story->date . '</muted><br>';
                      		 echo ' <b>' . $story->user . ' </b>' . $story->message . '<br>';
                      		echo '</p>';
                      echo '</div>';
                      echo '</div>';

}
}]);





Route::get('alarm/{state}', 'HomeController@changeAlarmStatus');
Route::get('alarmStatus', 'HomeController@checkAlarmStatus');


// Authentication Routes...
	Route::get('login', 'Auth\AuthController@showLoginForm');
	Route::post('login', 'Auth\AuthController@login');
	Route::get('logout', 'Auth\AuthController@logout');

// Registration Routes...
	Route::post('register', 'Auth\AuthController@register');

// Password Reset Routes...
	Route::get('password/reset/{token?}', 'Auth\PasswordController@showResetForm');
	Route::post('password/email', 'Auth\PasswordController@sendResetLinkEmail');
	Route::post('password/reset', 'Auth\PasswordController@reset');

