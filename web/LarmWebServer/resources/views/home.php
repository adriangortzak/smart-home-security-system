<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/alarm-status.css">
    	<link rel="stylesheet" type="text/css" href="css/login-style.css">
    </head>
    <body>
    <?php echo csrf_field(); ?>
<style>
body {margin:0;}

ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: black;
    position: fixed;
    top: 0;
    width: 100%;
}

li {
    float: left;
}

li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover:not(.active) {
    background-color: #111;
}

li:last-child {
    border-right: none;
}

.active {
    background-color: #4CAF50;
}

footer {
    background-color:black;
    color:white;
    clear:both;
    text-align:center;
    padding:5px;
    width:100%;
    position: fixed;
    bottom: 0px;
}


</style>
<nav style="padding-bottom: 60px;">
<ul>
  <li><a class="active" href="#home">Home</a></li>
  <li><a href="#contact">Admin</a></li>
  <li><a href="#news">Settings</a></li>
  <li><a href="#contact">About</a></li>
  <li style="float:right"><a href="#news"><i>Logout</i></a></li>
  <li Style="float:right"><p style="color:white; padding-right:20px;"><b>Adddrian</b></p></li>

</ul>
</nav>

<section  align="center">
<h1 style="color:white;">Alarm Status</h1>
 <p>
      <button id="alarm_status" onclick="changeAlarmStatus()" class="alarm_off">turn on Alarm</button>
	<script>
		function changeAlarmStatus(){
		var button = document.getElementById('alarm_status');
		if(button.className == "alarm_off"){
			$.get("alarm/ON");
			button.className ="alarm_on";
			button.textContent="Turn off Alarm";
		}else{
			$.get("alarm/OFF");
			button.className = "alarm_off"
			button.textContent = "Turn on Alarm";
		}	
			
		}
	</script>
    </p>
</section>

<footer>
Copyright ©  Grupp13
</footer>
</body>
</html>
