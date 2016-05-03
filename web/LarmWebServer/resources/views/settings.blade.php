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
				<td> <input type="file" name="pic" accept="image/*"></td>
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
                              <input type="password" class="form-control" id="exampleInputEmail2" placeholder="Password">
                          </div>
                          <div class="form-group">
                              <label class="sr-only" for="exampleInputPassword2">New Password again</label>
                              <input type="password" class="form-control" id="exampleInputPassword2" placeholder="Password">
                          </div>
                          <button type="submit" class="btn btn-theme">Change</button>
                      </form>
          			</div><!-- /form-panel -->
          		</div><!-- /col-lg-12 -->
          	</div>


<!-- Notification -->
                      <section class="task-panel tasks-widget">
	                	<div class="panel-heading">
	                        <div class="pull-left"><h5><i class="fa fa-tasks"></i> Notification</h5></div>
	                        <br>
	                 	</div>
                          <div class="panel-body">
                              <div class="task-content">
                                  <ul class="task-list">

                                      {{   getMyNotifications() }}
				      
                                  </ul>
                              </div>
				<div>
					<table><tr>
						<td>						
							<select>
							  <option value="pushbullet">PushBullet</option>
							  <option value="email">email</option>
							  <option value="tellstickaction">TellstickAction</option>
							</select>
						</td>
						<td>  Token:</td><td><input type="text"></td>
					</tr></table>
				</div>

                              <div class=" add-task-row">
                                  <a class="btn btn-success btn-sm pull-left" href="todo_list.html#">Add New Notification</a>
                              </div>
                          </div>
                      </section>
@stop
