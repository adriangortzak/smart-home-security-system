<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>SmartHomeSecuritySystem</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="assets/css/zabuto_calendar.css">
    <link rel="stylesheet" type="text/css" href="assets/js/gritter/css/jquery.gritter.css" />
    <link rel="stylesheet" type="text/css" href="assets/lineicons/style.css">    
    
    <!-- Custom styles for this template -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/style-responsive.css" rel="stylesheet">

    <script src="assets/js/chart-master/Chart.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

  <section id="container" >
      <!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
      <!--header start-->
      <header class="header black-bg">
              <div class="sidebar-toggle-box">
                  <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
              </div>
            <!--logo start-->
            <a href="index.html" class="logo"><b>SHSS</b></a>
            <!--logo end-->
            <div class="nav notify-row" id="top_menu">
            </div>
            <div class="top-menu">
            	<ul class="nav pull-right top-menu">
                    <li><a class="logout" href="logout">Logout</a></li>
            	</ul>
            </div>
        </header>
      <!--header end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
      <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu" id="nav-accordion">
              
              	  <p class="centered"><img src="assets/img/ui-sam.jpg" class="img-circle" width="60"></p>
              	  <h5 class="centered"><?php echo Auth::user()->name;?></h5>
              	  	
                  <li class="mt">
                      <a class="active" href="index.html">
                          <i class="fa fa-dashboard"></i>
                          <span>Dashboard</span>
                      </a>
                  </li>

                  <li class="sub-menu">
                       <a  href="index.html">
                          <i class="fa fa-cogs"></i>
                          <span>Settings</span>
                      </a>               
                  </li>
                        <li class="sub-menu">
                      <a href="index.html" >
                          <i class="fa fa-tasks"></i>
                          <span>Admin</span>
                      </a>                
                   </li>
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-book"></i>
                          <span>About</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="blank.html">Github</a></li>
                          <li><a  href="login.html">Website</a></li>
                          <li><a  href="lock_screen.html">Wiki</a></li>
                      </ul>
                  </li>
                  </li>             
              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">

              <div class="row">
                  <div class="col-lg-9 main-chart">
                  
                  	<div class="row mtbox">
                  		<div class="col-md-2 col-sm-2 col-md-offset-1 box0">
                  			<div class="box1">
					  			<span class="li_world"></span>
					  			<h3>
								  
								  <?php
								     $connected = @fsockopen("www.google.com", 80); 
								     //website, port  (try 80 or 443)
								     if ($connected){
								     echo "OK!"; //action when connected
								     fclose($connected);
								     }else{
								     echo "FAILED"; //action in connection failure
								     }    
								    ?>

								</h3>
                  			</div>
					  			<p>Check if you're able to connect to the Internet</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_megaphone"></span>
					  			<h3>OFF</h3>
                  			</div>
					  			<p>Siren is turned OFF.</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_eye"></span>
					  			<h3>7</h3>
                  			</div>
					  			<p>You have 7 sensors active.</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_user"></span>
					  			<h3>10</h3>
                  			</div>
					  			<p>There are 10 users on this system</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_data"></span>
					  			<h3>OK!</h3>
                  			</div>
					  			<p>Your database connection is OK!</p>
                  		</div>
                  	
                  	</div><!-- /row mt -->	
                  
                      
                      <div class="row mt">
                      <!-- SERVER STATUS PANELS -->
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
		textStatis.innerHTML ="ON";
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
                      	
                  			<!-- WEATHER PANEL -->
                      	<div class="col-md-4 col-sm-4 mb">
							<div class="weather pn">
								<i class="fa fa-cloud fa-4x"></i>
								<h2>11º C</h2>
								<h4>Stockholm</h4>
							</div>
						</div><!-- /col-md-4-->
                  <div class="col-md-4 col-sm-4 mb">
					  		<div class="green-panel pn">
					  			<div class="green-header">
						  			<h5>Alarm</h5>
					  			</div>
                                  <button type="button"  style="width:99%; height:37%;" class="btn btn-danger">Trigger</button>
								  <button type="button"  style="width:99%; height:37%; padding-bottom:20px;" class="btn btn-success">Stop</button>
						      
                            
					  		</div>
					  	</div><! --/col-md-4 -->
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
					
                  </div><!-- /col-lg-9 END SECTION MIDDLE -->
                  
                  
      <!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT
      *********************************************************************************************************************************************************** -->                  
                  
                  <div class="col-lg-3 ds">
                    <!--COMPLETED ACTIONS DONUTS CHART-->
						<h3>History</h3>
                                        
   <?php
   $history = App\history::orderBy('date','desc')->take(5)->get();
foreach($history  as $story){
                echo '<div class="desc">';
                      echo '<div class="thumb">';
                                echo '<span class="badge bg-theme"><i class="fa fa-clock-o"></i></span>';
                        echo '</div>';
                        echo '<div class="details">';
                                echo '<p><muted>' . $story->date . '</muted><br>';
                                 echo ' <b>' . $story->user . ' </b> <br>';
				echo '<muted>' . $story->message   . '</muted>';
                                echo '</p>';
                      echo '</div>';
                      echo '</div>';

}
?>
   
                       <!-- USERS ONLINE SECTION -->
						<h3>TEAM MEMBERS</h3>
<?php
                         $users = App\User::all(); 
		foreach($users  as $user){
		echo '<div class="desc">';
                        echo '<div class="thumb">';
                                echo '<img class="img-circle" src="assets/img/ui-divya.jpg" width="35px" height="35px" align="">';
                        echo '</div>';
                        echo '<div class="details">';
                                echo '<p>' . $user->name . '<br>';
                                   echo '<muted><b>Email: </b>' .$user->email . '</muted>';
                                echo '</p>';
                        echo '</div>';
                      echo '</div>';
}
?>
                           
                  </div><!-- /col-lg-3 -->
              </div><! --/row -->
          </section>
      </section>

      <!--main content end-->
      <!--footer start-->
      <footer class="site-footer">
          <div class="text-center">
              Copyright © Grupp13
              <a href="index.html#" class="go-top">
                  <i class="fa fa-angle-up"></i>
              </a>
          </div>
      </footer>
      <!--footer end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/jquery-1.8.3.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="assets/js/jquery.scrollTo.min.js"></script>
    <script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="assets/js/jquery.sparkline.js"></script>


    <!--common script for all pages-->
    <script src="assets/js/common-scripts.js"></script>
    
    <script type="text/javascript" src="assets/js/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="assets/js/gritter-conf.js"></script>

    <!--script for this page-->
    <script src="assets/js/sparkline-chart.js"></script>    
	<script src="assets/js/zabuto_calendar.js"></script>
	
	<script type="application/javascript">
        $(document).ready(function () {
            $("#date-popover").popover({html: true, trigger: "manual"});
            $("#date-popover").hide();
            $("#date-popover").click(function (e) {
                $(this).hide();
            });
        
            $("#my-calendar").zabuto_calendar({
                action: function () {
                    return myDateFunction(this.id, false);
                },
                action_nav: function () {
                    return myNavFunction(this.id);
                },
                ajax: {
                    url: "show_data.php?action=1",
                    modal: true
                },
                legend: [
                    {type: "text", label: "Special event", badge: "00"},
                    {type: "block", label: "Regular event", }
                ]
            });
        });
        
        
        function myNavFunction(id) {
            $("#date-popover").hide();
            var nav = $("#" + id).data("navigation");
            var to = $("#" + id).data("to");
            console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
        }
    </script>
  

  </body>
</html>
