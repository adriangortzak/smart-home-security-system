<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/alarm-status.css">
    	<link rel="stylesheet" type="text/css" href="css/login-style.css">
    </head>
    <body>
    <?php echo csrf_field(); ?>

 <p>

      <button id="alarm_status" onclick="changeAlarmStatus()" class="alarm_off">turn on Alarm</button>
	<script>
		function changeAlarmStatus(){
		var button = document.getElementById('alarm_status');
		if(button.className == "alarm_off"){
		    $.ajax({
                url: 'alarm',
                type: 'POST',
                data: {_token: "<?php echo csrf_token(); ?>",
                dataType: 'JSON',
                success: function (data) {
                    console.log(data);
                }}});
			button.className ="alarm_on";
			button.textContent="Turn off Alarm";
		}else{
			button.className = "alarm_off"
			button.textContent = "Turn on Alarm";
		}	
			
		}
	</script>
    </p>
</body>
</html>
