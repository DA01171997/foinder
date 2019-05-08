<?php
//this is used to get user information, when they they pressed the update option in the menu, it will pull and display what their info is
require "conn.php";
$user_id = isset($_POST['userID']) ? $_POST['userID'] : '';
$option = isset($_POST['option']) ? $_POST['option'] : '';



$mysql_query = "SELECT * FROM user_data WHERE userID= '$user_id';";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name);
$result=mysqli_query($conn, $mysql_query);


if (mysqli_num_rows($result)>0) {
    $assoarray = mysqli_fetch_assoc($result);
    if ($option=="name"){
        $optionResult = $assoarray['name'];
        echo $optionResult;
    }
    if ($option=="password"){
        $optionResult = $assoarray['password'];
        echo $optionResult;
    }
    if ($option=="birthday"){
        $optionResult = $assoarray['birthday'];
        echo $optionResult;
    }
}
else{
    //die("Database query failed.");
    echo "Get Info Failed";
}
$num=mysqli_num_rows($result);
mysqli_close($conn);  
?>
