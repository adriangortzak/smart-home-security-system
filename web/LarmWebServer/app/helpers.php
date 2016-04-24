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


function getUsers(){
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
}


?>
