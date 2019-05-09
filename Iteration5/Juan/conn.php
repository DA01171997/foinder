<?php
//this contains crucial login info, must contains this file where the server is hosted
$db_name="user";
$mysql_username = "";
$mysql_password = "";
$server_name ="localhost";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name);
if (mysqli_connect_errno()) {
    die("Database connection failed: " .
     mysqli_connect_error() . " (" > mysqli_connect_errno() . ")" );
}
mysqli_close($conn);
