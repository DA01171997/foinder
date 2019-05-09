<?php
//Used to check if email the user inputs is already used or not in order to sign up
require "conn.php";
$user_email = isset($_POST['email']) ? $_POST['email'] : '';
$mysql_query = "SELECT u.name, u.userID FROM user_data AS u  
                WHERE u.email = '$user_email'";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name);
$result=mysqli_query($conn, $mysql_query);
if (mysqli_num_rows($result)>0) {
    echo "Exists";
}
else{
    //die("Database query failed.");
    echo "Empty"; 
}
$num=mysqli_num_rows($result);
mysqli_close($conn);  
?>
