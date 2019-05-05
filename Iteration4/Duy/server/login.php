<?php
require "conn.php";
$user_email = isset($_POST['email']) ? $_POST['email'] : '';
$user_password = isset($_POST['password']) ? $_POST['password'] : '';
$salt = 12;
$user_password= $user_password.$salt;
$hashedPassword = md5($user_password);
$mysql_query = "SELECT * FROM user_data AS u  
                WHERE u.email = '$user_email' 
                AND u.password = '$hashedPassword'";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name);
$result=mysqli_query($conn, $mysql_query);
if (mysqli_num_rows($result)>0) {
    echo "Success";
}
else{
    //die("Database query failed.");
    echo "Login Fail"; 
}
$num=mysqli_num_rows($result);
mysqli_close($conn);  
?>