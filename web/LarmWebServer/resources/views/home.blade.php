
@extends('master')
@section('dashboard_active')
	active 
@stop

@section('main_content')
                  
                  	<div class="row mtbox">
                  		<div class="col-md-2 col-sm-2 col-md-offset-1 box0">
                  			<div class="box1">
					  			<span class="li_world"></span>
					  			<h3 id="internetConnection">
									Failed
								</h3>
                  			</div>
					  			<p>Check if you're able to connect to the Internet</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_megaphone"></span>
					  			<h3 id="megaphone">OFF!</h3>
                  			</div>
					  			<p>Siren is state.</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_eye"></span>
					  			<h3 id="triggerCount">0</h3>
                  			</div>
					  			<p>Sensors that is active.</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_user"></span>
					  			<h3 id="userCount">0</h3>
                  			</div>
					  			<p>Users on this system</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_data"></span>
					  			<h3 id="serverStatus">FALSE!</h3>
                  			</div>
					  			<p>Check connection to Server!</p>
                  		</div>
                  	
                  	</div><!-- /row mt -->	
                  <script>

					function updateDashboard(){
						$.get( "ServerCheck", function( data ){
							document.getElementById('serverStatus').innerText=data;
						});
						$.get( "userCount", function( data ){
							document.getElementById('userCount').innerText=data;
						});
						$.get( "alarmStatus", function( data ){
							document.getElementById('megaphone').innerText=data;
						});
						$.get( "internetStatus", function( data ){
							document.getElementById('internetConnection').innerText=data;
						});
						$.get( "triggerCount", function( data ){
							document.getElementById('triggerCount').innerText=data;
						});
						$.get( "getWeather", function( data ){
							var obj = JSON.parse(data);
							document.getElementById('temp').innerText=obj.temp;
							document.getElementById('city').innerText=obj.city;
						});


					}
					updateDashboard();


				  </script>
                      
                      <div class="row mt">
                      <!-- SERVER STATUS PANELS -->
						  @if(checkControlGroup() == 1)
                      	<div class="col-md-4 col-sm-4 mb">
                      		<div class="white-panel pn donut-chart">
                      			<div class="white-header">
						  			<h5>Alarm Status</h5>
                      			</div>
								<div class="row">
									<div class="col-sm-6 col-xs-6 goleft">
										<p><i class="fa fa-database"></i><i id="alarm_text_status">ON</i></p>
									</div>
	                      		</div>
								<button id="alarm_status" onclick="changeAlarmStatus()" type="button" style="width:80%; height:60%" class="btn btn-round btn-success"  >Turn off Alarm</button>
								<script>
		function changeAlarmStatus(){
		var button = document.getElementById('alarm_status');
		var textStatus = document.getElementById('alarm_text_status');
		if(button.className == "btn btn-round btn-success"){
		$.get("alarm/ON");
		button.className ="btn btn-round btn-danger";
		button.textContent="Turn off Alarm";
		textStatus.innerHTML ="ON";
		}else{
		$.get("alarm/OFF");
		button.className = "btn btn-round btn-success";
		button.textContent = "Turn on Alarm";
		textStatus.innerHTML ="OFF";
		}
		}

		   function setStartAlarmStatus(){
		var button = document.getElementById('alarm_status');
		var textStatus = document.getElementById('alarm_text_status');
                       $.get( "alarmStatus", function( data ) {
                        if(data === "ON\n"){
	                        button.className ="btn btn-round btn-danger";
        	button.textContent="Turn off Alarm";
		textStatus.innerHTML ="ON";
                        }else if(data === "OFF\n"){
        	                button.className = "btn btn-round btn-success";
	        button.textContent = "Turn on Alarm";
		textStatus.innerHTML ="OFF";
                        }
                        });
                }

		setStartAlarmStatus();

	</script>
								
	                      	</div><! --/grey-panel -->
                      	</div><!-- /col-md-4-->
                      	@else
						  @endif
                  			<!-- WEATHER PANEL -->
                      	<div class="col-md-4 col-sm-4 mb">
							<div class="weather pn">
								<i class="fa fa-cloud fa-4x"></i>
								<h2 id=temp>Temp</h2>
								<h4 id=city>City</h4>
							</div>
						</div><!-- /col-md-4-->

						  @if(checkControlGroup() == 1)
                  <div class="col-md-4 col-sm-4 mb">
					  		<div class="green-panel pn">
					  			<div class="green-header">
						  			<h5>Alarm</h5>
					  			</div>
                                  <button type="button"  onclick="startTrigger()" style="width:99%; height:70%;" class="btn btn-danger">Trigger</button>
								<script>
									function startTrigger(){
										$.get("trigger");
									}
								</script>
					  		</div>
					  	</div><! --/col-md-4 -->
						  @else
						  @endif
                    </div><!-- /row -->
                    
                    				
					
					
					<div class="row mt">
                      <!--CUSTOM CHART START -->
                      <div class="border-head">
                          <h3>Alarms triggerd</h3>
                      </div>
                      <div class="custom-bar-chart">
                          <ul class="y-axis">
                              <li><span>10.000</span></li>
                              <li><span>8.000</span></li>
                              <li><span>6.000</span></li>
                              <li><span>4.000</span></li>
                              <li><span>2.000</span></li>
                              <li><span>0</span></li>
                          </ul>
                          <div class="bar">
                              <div class="title">JAN</div>
                              <div class="value tooltips" data-original-title="8.500" data-toggle="tooltip" data-placement="top">85%</div>
                          </div>
                          <div class="bar ">
                              <div class="title">FEB</div>
                              <div class="value tooltips" data-original-title="5.000" data-toggle="tooltip" data-placement="top">50%</div>
                          </div>
                          <div class="bar ">
                              <div class="title">MAR</div>
                              <div class="value tooltips" data-original-title="6.000" data-toggle="tooltip" data-placement="top">60%</div>
                          </div>
                          <div class="bar ">
                              <div class="title">APR</div>
                              <div class="value tooltips" data-original-title="4.500" data-toggle="tooltip" data-placement="top">45%</div>
                          </div>
                          <div class="bar">
                              <div class="title">MAY</div>
                              <div class="value tooltips" data-original-title="3.200" data-toggle="tooltip" data-placement="top">32%</div>
                          </div>
                          <div class="bar ">
                              <div class="title">JUN</div>
                              <div class="value tooltips" data-original-title="6.200" data-toggle="tooltip" data-placement="top">62%</div>
                          </div>
                          <div class="bar">
                              <div class="title">JUL</div>
                              <div class="value tooltips" data-original-title="7.500" data-toggle="tooltip" data-placement="top">75%</div>
                          </div>
                      </div>
                      <!--custom chart end-->
					</div><!-- /row -->	
					
@stop
