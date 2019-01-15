<?php

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "";
$mysql_database = "uasandroidsicil";
$db = mysqli_connect($mysql_hostname, $mysql_user, $mysql_password,$mysql_database) or die("Opps some thing went wrong");
 

    if(isset($_SERVER['REQUEST_METHOD'])){
        $id_supplier = $_GET['id_supplier'];
        $get_result = mysqli_query($db,"DELETE FROM sclsupplier WHERE id_supplier='$id_supplier'"); 
 
        if($get_result === true){
            echo "Successfully Deleted";
        }else{
        echo $kode."Failed";
            }
                }else{
                echo 'error';
            }
?>
