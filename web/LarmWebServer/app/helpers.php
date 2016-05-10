<?php

function checkInternetConnections(){
   $connected = @fsockopen("www.google.com", 80);
             //website, port  (try 80 or 443)
              if ($connected){
              echo "OK!"; //action when connected
              fclose($connected);
              }else{
              echo "FAILED"; //action in connection failure
              }
}

function getLastFiveMessageFromHistory(){
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
}


function getAllMessageFromHistory(){

$history = App\history::orderBy('date','desc')->get();
echo '<div class="col-md-12">';
	              echo '<div class="content-panel">';
	                  echo  '<h4><i class="fa fa-angle-right"></i>Table</h4>';
	                   	 echo  '<hr>';
		                      echo '<table class="table">';
		                         echo  '<thead>';
		                          echo '<tr>';
		                             echo '<th>#</th>';
		                             echo  '<th>Name</th>';
		                             echo '<th>Message</th>';
		                             echo '<th>Timestamp</th>';
		                          echo '</tr>';
		                          echo '</thead>';
		                          echo '<tbody>';
					foreach($history  as $story){
		                          echo '<tr>';
		                            echo '<td>' . $story->id  .  '</td>';
		                            echo  '<td>' .$story->user . '</td>';
		                            echo  '<td>' . $story->message   . '</td>';
		                            echo  '<td>' . $story->date . '</td>';
		                         echo '</tr>';
					}
		                        echo  '</tbody>';
		                      echo '</table>';
	                  	echo  '</div><!-- --/content-panel ---->';
	                echo  '</div>';
}


function getUserCount(){
echo App\User::all()->count();
}

function getMyNotifications(){
$notifications = App\notifications::all();
foreach($notifications  as $notification){

echo '<li>';
            echo  '<div class="task-checkbox">';
              echo '<input type="checkbox" class="list-child" ';
              if ($notification->active == 1){
              echo "checked";
              }
              echo '>';
		echo '</div>';
                echo '<div class="task-title">';
                echo '<span class="task-title-sp">' . $notification->name . '</span>';
                echo '<span class="badge bg-info">' . $notification->type . '</span>';
		echo '<span><a style="padding-left:20px;">Token: </a></span>';
		echo '<span><input style="width:40%; padding:10px; border-radius:5px;" readonly="readonly" type="text" value="'. $notification->token . '">';
                echo '<div class="pull-right hidden-phone">';
                echo '<button class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i></button>';
                echo '<button class="btn btn-danger btn-xs" onclick="removeNotification(' . $notification->id . ')"><i class="fa fa-trash-o "></i></button>';
		echo '</div>';
                echo '</div>';
                echo '</li>';
}
}

function getMyTriggers(){
    $triggers = App\triggers::all();
    foreach($triggers  as $trigger){

        echo '<li>';
        echo  '<div class="task-checkbox">';
        echo '<input id="' . $trigger->id . '-sensorCheckbox"' . ' disabled type="checkbox" class="list-child" ';
        if ($trigger->active == 1){
            echo "checked";
        }
        echo '>';
        echo '</div>';
        echo '<div class="task-title">';
        echo '<span class="task-title-sp">' . $trigger->name . '</span>';
        echo '<span class="badge bg-info">' . $trigger->type . '</span>';
        echo '<span><a style="padding-left:20px;">ID: </a></span>';
        echo '<span><input id="' . $trigger->id . '-sensorID" style="width:40%; padding:10px; border-radius:5px;" readonly="readonly" type="text" value="'. $trigger->sensor
            . '">';
        echo '<div class="pull-right hidden-phone">';
	echo '<button id="' . $trigger->id . '-check" style="display:none;" class="btn btn-success btn-xs" onclick="confirmSensorEdit(' . $trigger->id . ')"><i class="fa fa-check"></i></button>';
        echo '<button id="' . $trigger->id . '-cancel" style="display:none;" class="btn btn-danger btn-xs" onclick="cancelSensorEdit(' . $trigger->id . ')"><i class="fa fa-times "></i></button>';
        echo '<button id="' . $trigger->id . '-edit" style="display:inline;" class="btn btn-primary btn-xs" onclick="editSensor(' . $trigger->id . ')"><i class="fa fa-pencil"></i></button>';
        echo '<button id="' . $trigger->id . '-thrash" style="display:inline;" class="btn btn-danger btn-xs" onclick="removeSensor(' . $trigger->id . ')"><i class="fa fa-trash-o "></i></button>';
        echo '</div>';
        echo '</div>';
        echo '</li>';
    }
}





function userConfigList(){
$users = App\User::all();
foreach ($users as $user) {
    echo '<li>';
    echo '<div class="task-title" >';
    echo '<span class="task-title-sp">' . $user->name . '</span>';
    echo '<span class="badge bg-info">User</span>';
    echo '<div class="pull-right hidden-phone">';
    echo '<button onclick="removeUser(' . "'" .$user->email . "'" . ')" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></button>';
                          echo '</div>';
            echo '</div>';
  echo '</li>';
    }
}

function getUsers()
{
    $users = App\User::all();
    foreach ($users as $user) {
        echo '<div class="desc">';
        echo '<div class="thumb">';
        echo '<img class="img-circle" src="assets/img/ui-divya.jpg" width="35px" height="35px" align="">';
        echo '</div>';
        echo '<div class="details">';
        echo '<p>' . $user->name . '<br>';
        echo '<muted><b>Email: </b>' . $user->email . '</muted>';
        echo '</p>';
        echo '</div>';
        echo '</div>';
    }
}
function getTriggerCount()
{
    $triggers = App\triggers::all()->count();
    echo $triggers;
}

function updateSensors($id, $value, $checkbox){
   App\triggers::where('id',$id)->update(array('sensor' => $value)); 
   App\triggers::where('id',$id)->update(array('active' => $checkbox));
}

?>
