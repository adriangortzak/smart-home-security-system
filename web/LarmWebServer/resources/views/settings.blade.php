@extends('master')
@section('settings_active')
        active
@stop


@section('main_content')
<h1>Settings</h1>



<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">
				<h4>Change profile picture</h4>
		        	<form action="demo_form.asp">
				<table>
				<tr>
				<td> <input type="file" id="pic" accept="image/*"></td>
  	 	     		<td>  <input class="btn btn-success btn-sm pull-left" type="submit" value="Upload"></td>
				</tr>
				</table>
				</form>
          			</div><!-- /form-panel -->
          		</div><!-- /col-lg-12 -->
          	</div>


<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">
                  	  <h4 class="mb">Change password</h4>
                      <form class="form-inline" role="form">
                          <div class="form-group">
                              <label class="sr-only" for="exampleInputEmail2">New Password</label>
                              <input type="password" class="form-control" id="password1" placeholder="Password">
                          </div>
                          <div class="form-group">
                              <label class="sr-only" for="exampleInputPassword2">New Password again</label>
                              <input type="password" class="form-control" id="password2" placeholder="Password">
                          </div>
                          <button type="button" onclick="changePassword()" class="btn btn-theme">Change</button>
                      </form>
          			</div><!-- /form-panel -->
          		</div><!-- /col-lg-12 -->
          	</div>

<script>
	function changePassword() {
		var password1 = document.getElementById('password1').value;
		var password2 = document.getElementById('password2').value;
	if(password1 == ""){
		alert("More information is required");
	}
	else if(password1 != password2){
			alert("Password is not the same");
		}else{
		alert("Password has changed");
			password1="";
			password2="";
		}
	}

</script>

<!-- Notification -->
                      <section class="task-panel tasks-widget">
	                	<div class="panel-heading">
	                        <div class="pull-left"><h5><i class="fa fa-tasks"></i> Notification</h5></div>
	                        <br>
	                 	</div>
                          <div class="panel-body">
                              <div class="task-content">
                                  <ul class="task-list" id="notificationList">


				      
                                  </ul>
                              </div>
				<div>
					<table><tr>
							<td>Name:<input id="notificationName" type="text"></td>
						<td>Type:
							<select id="notificationTyp">
							  <option value="pushbullet">PushBullet</option>
							  <option value="email">email</option>
							  <option value="tellstickaction">TellstickAction</option>
							</select>
						</td>
						<td> Token:<input id="notificationId" type="text"></td>
					</tr></table>
				</div>
							  <script>
								  function getNotifications(){
									  $.get( "getNotifications", function( data ) {
										  document.getElementById('notificationList').innerHTML = data;
									  })
								  }
								  
								  getNotifications();

								  function removeNotification(id){
									  $.get( "removeNotification/"+id, function() {
										  getNotifications();
									  })
								  }

								  function addNotification() {

							

									  var name = document.getElementById('notificationName').value;
									  var type = document.getElementById('notificationTyp').value;
									  var token = document.getElementById('notificationId').value;
									  if(name == "" && token == ""){
										  $.gritter.add({
											  title: 'Error',
											  text: "More information is required for adding new notification"
										  });
									  }else{

										  $.ajax({
											  url: "addNotification",
											  type: 'POST',
											  data:  {name:$("#notificationName").attr('value'),type: $("#notificationTyp").attr('value'), sensor:$("#notificationId").attr('value'), _token:$('meta[name=csrf-token]').attr('content')},
											  success: function(){
												  // Success...
												  $("#notificationId").val("");
												  $("#notificationName").val("");
												  getNotifications();
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

                              <div class=" add-task-row">
								  <tr>
									  <td>
										  <a class="btn btn-success btn-sm pull-left" onclick="addNotification()">Add New Notification</a>
									  </td>
								  </tr>
                              </div>
                          </div>
                      </section>
@stop
