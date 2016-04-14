<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

        <link rel="stylesheet" type="text/css" href="css/alarm-status.css">
    	<link rel="stylesheet" type="text/css" href="css/login-style.css">
    </head>
    <body>
 <p>

      <button id="alarm_status" onclick="changeAlarmStatus()" class="alarm_off">turn on Alarm</button>
	<script>
		function changeAlarmStatus(){
		var button = document.getElementById('alarm_status');
		if(button.className == "alarm_off"){
		<meta name="csrf-token" content="{{ csrf_token() }}" />

        var CSRF_TOKEN = $('meta[name="csrf-token"]').attr('content');

		    $.ajax({
                url: 'test',
                type: 'POST',
                data: {_token: CSRF_TOKEN},
                dataType: 'JSON',
                success: function (data) {
                    console.log(data);
                }});
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
