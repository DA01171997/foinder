<?php
require "conn.php";
$name = isset($_POST['name']) ? $_POST['name'] : '';
$user_id = isset($_POST['userID']) ? $_POST['userID'] : '';
$user_password = isset($_POST['password']) ? $_POST['password'] : '';
$birthday = isset($_POST['birthday']) ? $_POST['birthday'] : '';
$salt = 12;
$user_password= $user_password.$salt;
$hashedPassword = md5($user_password);
$mysql_query = "UPDATE user_data SET name = '$name', password = '$hashedPassword', birthday='$birthday' WHERE userID = '$user_id';";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name);
if ($conn->query($mysql_query) === TRUE) {
    echo "Update Successed";
}
else{
    //die("Database query failed.");
    echo "Update Failed";
}
mysqli_close($conn);
?>
