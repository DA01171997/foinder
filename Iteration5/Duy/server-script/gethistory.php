<?php
//This is used to get all the places that the user swiped right from
require "conn.php";
$user_id = isset($_POST['userID']) ? $_POST['userID'] : '';
$option = isset($_POST['option']) ? $_POST['option'] : '';
if ($option=="rname"){
	$mysql_query = "SELECT $option FROM history_data3 WHERE userID='$user_id'";
}
if ($option=="placeID"){
	$rname = isset($_POST['rname']) ? $_POST['rname'] : '';
	$mysql_query = "SELECT $option FROM history_data3 WHERE userID='$user_id' AND rname ='$rname' ";
}
if ($option=="photoref"){
	$rname = isset($_POST['rname']) ? $_POST['rname'] : '';
	$mysql_query = "SELECT $option FROM history_data3 WHERE userID='$user_id' AND rname ='$rname' ";
}
//echo $mysql_query;
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name);
$result=mysqli_query($conn, $mysql_query);
$num = mysqli_num_rows($result);
if ($num>0) {
	if ($option=="rname") {
		$matchedResult="";
		while ($assoarray = mysqli_fetch_assoc($result)){
        		$matchedResult.=$assoarray['rname']."*";
		}
		echo $matchedResult;
	}
	if ($option=="placeID"){
		$assoarray = mysqli_fetch_assoc($result);
    		$matchedResult = $assoarray['placeID'];
		echo $matchedResult;
	}
	if ($option=="photoref"){
		$assoarray = mysqli_fetch_assoc($result);
    		$matchedResult = $assoarray['photoref'];
		echo $matchedResult;
	}

}
else{
    echo "No History";
}
mysqli_close($conn);  #
?>
