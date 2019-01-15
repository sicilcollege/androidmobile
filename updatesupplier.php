<?php

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "";
$mysql_database = "uasandroidsicil";
$db = mysqli_connect($mysql_hostname, $mysql_user, $mysql_password,$mysql_database) or die("Opps some thing went wrong");
 
    if(isset($_SERVER['REQUEST_METHOD'])){
        $id_supplier = $_POST['id_supplier'];
        $nama_supplier = $_POST['nama_supplier'];
        $telp_supplier = $_POST['telp_supplier'];
        $alamat_supplier = $_POST['alamat_supplier'];
        $get_result = mysqli_query($db,"UPDATE sclsupplier SET nama_supplier='$nama_supplier', telp_supplier='$telp_supplier', alamat_supplier='$alamat_supplier' WHERE id_supplier='$id_supplier'"); 
 
        if($get_result === true){
            echo "Successfully Registered";
        }else{
        echo $kode."Not Registered";
            }
                }else{
                echo 'error';
            }
?>
