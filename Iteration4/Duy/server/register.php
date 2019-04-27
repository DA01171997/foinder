<?php
require "conn.php";
$name = isset($_POST['name']) ? $_POST['name'] : '';
$user_email = isset($_POST['email']) ? $_POST['email'] : '';
$user_password = isset($_POST['password']) ? $_POST['password'] : '';
$salt = 12;
$user_password= $user_password.$salt;
$hashedPassword = md5($user_password);
$birthday = isset($_POST['birthday']) ? $_POST['birthday'] : '';
$mysql_query = "INSERT INTO user_data (name, email, password, birthday)
                VALUES ('$name','$user_email','$hashedPassword','$birthday')";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name);
if ($conn->query($mysql_query) === TRUE) {
    echo "Insert success";
}
else{
    //die("Database query failed.");
    echo "Insert Fail";
    echo "Error: ". $conn->error;
}
mysqli_close($conn);  #
?>