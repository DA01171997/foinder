<?php
require "conn.php";
//this is the login page for the user where they input their information in order to successfully login.
$user_email = isset($_POST['email']) ? $_POST['email'] : ''; 
$user_password = isset($_POST['password']) ? $_POST['password'] : '';
$salt = 12;
$user_password= $user_password.$salt;
$hashedPassword = md5($user_password);
//Checks if user info inputted is valid
$mysql_query = "SELECT u.name, u.userID FROM user_data AS u  
                WHERE u.email = '$user_email' 
                AND u.password = '$hashedPassword'";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name);
$result=mysqli_query($conn, $mysql_query);
if (mysqli_num_rows($result)>0) {
    $assoarray = mysqli_fetch_assoc($result);
    $name = $assoarray['name'];
    $userID = $assoarray['userID'];
    echo "$name-$userID";
}
else{
    //die("Database query failed.");
    echo "Login Failed"; 
}
$num=mysqli_num_rows($result);
mysqli_close($conn);  
?>
