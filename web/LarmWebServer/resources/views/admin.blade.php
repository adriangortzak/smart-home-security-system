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
                        {!! csrf_field() !!}
<div>
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
                                <button type="button" onclick="registerUser()" class="btn btn-primary">
                                    <i class="fa fa-btn fa-user"></i>Register
                                </button>
                            </div>
                        </div>
</div>
</div>
</div>


<script>
    function registerUser() {
        $.post("newUser", {name:$("#name").attr('value'),email: $("#email").attr('value'), password:$("#password").attr('value'),_token:$('meta[name=csrf-token]').attr('content')}, function(data){
            alert("Data: " + data);
        })
    }
</script>


<div class="col-lg-12">
          			<div class="form-panel">
                  	  <h4 class="mb">Manage User</h4>
					
						
						<hr>
						<br>
						<select multiple="" class="form-control" style="height:300px;">
						  <option>Adrian</option>
						  <option>Maja</option>
						  <option>Jimmy</option>
						  <option>Olle</option>
						</select>        		
          			</div><!-- /form-panel -->
          		</div>








<div class="col-lg-12">

                      <section class="task-panel tasks-widget">
	                	<div class="panel-heading">
	                        <div class="pull-left"><h4>Sensors</h4></div>
	                        <br>
	                 	</div>
                          <div class="panel-body">
                              <div class="task-content">
                                  <ul class="task-list">

                                      <li>
                                          <div class="task-checkbox">
                                              <input type="checkbox" class="list-child" value="">
                                          </div>
                                          <div class="task-title" >
                                              <span class="task-title-sp">Motionsensors</span>
                                              <span class="badge bg-info">Tellstick</span>
                                              <div class="pull-right hidden-phone">
                                                  <button class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i></button>
                                                  <button class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></button>
                                              </div>
                                          </div>
                                      </li>
				      
                                  </ul>
                              </div>

                              <div class=" add-task-row">
                                  <a class="btn btn-success btn-sm pull-left" href="todo_list.html#">Add New Sensor</a>
                              </div>
                          </div>
                      </section>

</div>


@stop
