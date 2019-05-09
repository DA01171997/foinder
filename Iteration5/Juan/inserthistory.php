<?php
//This is used to insert the user swipe right option into history
require "conn.php";
$restaurant_name = isset($_POST['rName']) ? $_POST['rName'] : ''; // Restaurants name
$user_id = isset($_POST['userID']) ? $_POST['userID'] : ''; // UserID associated with user in the DB
$place_id = isset($_POST['placeID']) ? $_POST['placeID'] : ''; // Place ID in the DB
photoref = isset($_POST['photoref']) ? $_POST['photoref'] : ''; // Photo for the location
$mysql_query = "SELECT * FROM history_data3 WHERE rname = '$restaurant_name' AND  placeID ='$place_id' AND userID='$user_id'";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name);
$result=mysqli_query($conn, $mysql_query);
if (mysqli_num_rows($result)>0) {
	echo "Already Exist";
}
else{
    $mysql_query = "INSERT INTO history_data3 (rname, placeID, userID,photoref)
                VALUES ('$restaurant_name','$place_id','$user_id','$photoref')";
    $conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name);
    if ($conn->query($mysql_query) === TRUE) {
        echo "Insert History Successed";
    }
    else{
        //die("Database query failed.");
	    echo "Insert History Failed ";
    }
}
mysqli_close($conn);  #
?>
