@extends('master')
@section('admin_active')
        active
@stop


@section('main_content')
<meta name="csrf-token" content="{{ Session::token() }}">
<h1 style="text-align: center;"><b>Admin</b></h1>

<div class="col-lg-12">
	<div class="form-panel">
<h4>Create new user</h4>
<hr>
        <form class="form-horizontal" >
                        {!! csrf_field() !!}

                        <div class="form-group{{ $errors->has('name') ? ' has-error' : '' }}">
                            <label class="col-md-4 control-label">Name</label>

                            <div class="col-md-6">
                                <input type="text" class="form-control" id="name" name="name" value="{{ old('name') }}">
                            </div>
                        </div>

                        <div class="form-group{{ $errors->has('email') ? ' has-error' : '' }}">
                            <label class="col-md-4 control-label">E-Mail Address</label>

                            <div class="col-md-6">
                                <input type="email" class="form-control" id="email" name="email" value="{{ old('email') }}">
                            </div>
                        </div>

                        <div class="form-group{{ $errors->has('password') ? ' has-error' : '' }}">
                            <label class="col-md-4 control-label">Password</label>

                            <div class="col-md-6">
                                <input type="password" id="password1" class="form-control" name="password">
                            </div>
                        </div>

                        <div class="form-group{{ $errors->has('password_confirmation') ? ' has-error' : '' }}">
                            <label class="col-md-4 control-label">Confirm Password</label>

                            <div class="col-md-6">
                                <input type="password" id="password2" class="form-control" name="password_confirmation">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-4">
                                <button style="float:left"  type="button" onclick="registerUser()" class="btn btn-primary">
                                    <i class="fa fa-btn fa-user"></i>Register
                                </button><p style="float:left; padding-left:20px" id="newUserSatatus"></p>
                            </div>
                        </div>
        </form>
</div>
</div>


<script>
    function registerUser() {

$.ajax({
    url: "newUser",
    type: 'POST',
    data:  {name:$("#name").attr('value'),email: $("#email").attr('value'), password:$("#password1").attr('value'),password_confirmation:$("#password2").attr('value'), _token:$('meta[name=csrf-token]').attr('content')},
    success: function(data){
        // Success...
	$("#name").val("");
	$("#email").val("");
	$("#password1").val("");
	$("#password2").val("");
        document.getElementById('newUserSatatus').innerText = "Created a new user";
       updateUserManagement();
    },
    error: function(data){
         // Error...
    var errors = $.parseJSON(data.responseText);

    console.log(errors);

    $.each(errors, function(index, value) {
        $.gritter.add({
            title: 'Error',
            text: value
        });
    });
    }
});
    }
</script>

<div class="col-lg-12">
    <section class="task-panel tasks-widget">
        <div class="panel-heading">
            <div class="pull-left"><h4>Manage User</h4></div>
            <br>
        </div>
        <div class="panel-body">
            <div class="task-content">
                <ul class="task-list" id="userManagement">
                </ul>
            </div>

        </div>
    </section>
</div>


<div class="col-lg-12">
    <section class="task-panel tasks-widget">
        <div class="panel-heading">
            <div class="pull-left"><h4>Pending time (seconds)</h4></div>
            <br>
        </div>
        <div class="panel-body">
            <div class="task-content">
                <input id="pendingTimeValue" type="text" value=""><button style="float:right;"  class="btn btn-success btn-xs" onclick="updatePendingTime()">Update</button>
            </div>

        </div>
    </section>
</div>

<script>
 function getPendingTime(){
     document.getElementById('PendingTimeValue').value = "testing";
 }
 getPendingTime();
</script>

<div class="col-lg-12">
    <section class="task-panel tasks-widget">
        <div class="panel-heading">
            <div class="pull-left"><h4>Notification Interval (seconds)</h4></div>
            <br>
        </div>
        <div class="panel-body">
            <div class="task-content">
                <input type="text"><button style="float:right;" class="btn btn-success btn-xs">Update</button>
            </div>

        </div>
    </section>
</div>


<div class="col-lg-12">
    <section class="task-panel tasks-widget">
        <div class="panel-heading">
            <div class="pull-left"><h4>Temperature</h4></div>
            <br>
        </div>
        <div class="panel-body">
            <div class="task-content">
                <p><b>City: </b></p><input type="text">
            </div>
            <div class="task-content">
                <p><b>Country Code:</b></p><input type="text">
            </div>
            <div class="task-content">
                <p><b>openWeatherMap Api-key</b></p><input type="text">
            </div>
            <div class="task-content">
                <button class="btn btn-success btn-xs">Update</button>
            </div>
        </div>
    </section>
</div>


<script>
    function updateUserManagement(){
	
$.get( "userManagement", function( data ) {  
document.getElementById('userManagement').innerHTML= data;

});
}

updateUserManagement();

    function removeUser(email) {
        $.get("removeUser/"+email,function(){
       updateUserManagement();
});
    }
</script>

<div class="col-lg-12">

                      <section class="task-panel tasks-widget">
	                	<div class="panel-heading">
	                        <div class="pull-left"><h4>Sensors</h4></div>
	                        <br>
	                 	</div>
                          <div class="panel-body">
                              <div class="task-content">
                                  <ul class="task-list" id="sensorList">
                                  </ul>
                              </div>
                              <div class=" add-task-row">
				<div>
                    <table><tr>
                            <td>Name: <input id="sensorName" type="text"> </td>
                            <td>
                                <select id="sensorType">
                                    <option value="tellstick">Tellstick</option>
                                </select>
                            </td>
                            <td>  Token: <input id="sensorToken" type="text"></td>
                        </tr></table>
				</div>
                                  <a class="btn btn-success btn-sm pull-left" onclick="addSensor()">Add New Sensor</a>
				               </div>
                          </div>
                      </section>
</div>
<script>
 function updatePendingTime(){
     
 }
 
    function cancelSensorEdit(id) {
        document.getElementById( id + '-check').style.display = "none";
	document.getElementById( id + '-cancel').style.display = "none";
	document.getElementById( id + '-edit').style.display = "inline";
	document.getElementById( id + '-thrash').style.display = "inline";
	getSensors();
	
	//Probably do some stuff to reload values from database or restore them from local copy while editing.
	// Get only the values for this row.
    }

    function editSensor(id) {
        document.getElementById( id + '-check').style.display = "inline";
	document.getElementById( id + '-cancel').style.display = "inline";
	document.getElementById( id + '-edit').style.display = "none";
	document.getElementById( id + '-thrash').style.display = "none";
	$('#'+id+'-sensorID').prop('readonly', false);
	$('#'+id+'-sensorCheckbox').prop('disabled', false);

	// Set input for all fields. Hidden inputs?
    }

    function confirmSensorEdit(id) {
        document.getElementById( id + '-check').style.display = "none";
	document.getElementById( id + '-cancel').style.display = "none";
	document.getElementById( id + '-edit').style.display = "inline";
	document.getElementById( id + '-thrash').style.display = "inline";
	$('#'+id+'-sensorID').prop('readonly', true);
	$('#'+id+'-sensorCheckbox').prop('disabled', true);
	var value = document.getElementById(id + '-sensorID').value;
	var checked = document.getElementById( id + '-sensorCheckbox').checked;
	var checkbox = checked ? 1 : 0;
//	if (checked == false){
//		document.getElementById( id + '-sensorCheckbox').checked = true;
//	}
//	else {
//		document.getElementById( id + '-sensorCheckbox').checked = false;
//	}
	$.get( "updateSensors/"+id+"/"+value+"/"+checkbox, function() {
		
	})

	// Do some stuff to apply the changes...
	// update database with new values and remove hidden inputs?
    }

    function getSensors() {
        $.get( "getSensors", function( data ) {
            document.getElementById('sensorList').innerHTML = data;
        })
    }
    getSensors();

    function removeSensor(id) {
        $.get( "removeSensor/"+id, function() {
            getSensors();
        })
    }
    
    
    function addSensor() {
        var type = document.getElementById('sensorType').value;
        var token = document.getElementById('sensorToken').value;
        var name = document.getElementById('sensorName').value;
        
        if(token == "" && name == ""){
            alert("more info is required");
        }else{

            $.ajax({
                url: "addSensor",
                type: 'POST',
                data:  {name:$("#sensorName").attr('value'),type: $("#sensorType").attr('value'), sensor:$("#sensorToken").attr('value'), _token:$('meta[name=csrf-token]').attr('content')},
                success: function(data){
                    // Success...
                    $("#sensorToken").val("");
                    $("#sensorName").val("");
                    getSensors();
                },
                error: function(data){
                    // Error...
                    var errors = $.parseJSON(data.responseText);

                    console.log(errors);

                    $.each(errors, function(index, value) {
                        $.gritter.add({
                            title: 'Error',
                            text: value
                        });
                    });
                }
            });
        }
    }
</script>


@stop
