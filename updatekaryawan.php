<?php

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "";
$mysql_database = "uasandroidsicil";
$db = mysqli_connect($mysql_hostname, $mysql_user, $mysql_password,$mysql_database) or die("Opps some thing went wrong");
 
    if(isset($_SERVER['REQUEST_METHOD'])){
        $id_karyawan = $_POST['id_karyawan'];
        $nama_karyawan = $_POST['nama_karyawan'];
        $telp_karyawan = $_POST['telp_karyawan'];
        $alamat_karyawan = $_POST['alamat_karyawan'];
        $password_karyawan = $_POST['password_karyawan'];
        $get_result = mysqli_query($db,"UPDATE sclkaryawan SET nama_karyawan='$nama_karyawan', telp_karyawan='$telp_karyawan', alamat_karyawan='$alamat_karyawan', password_karyawan='$password_karyawan' WHERE id_karyawan='$id_karyawan'"); 
 
        if($get_result === true){
            echo "Successfully Registered";
        }else{
        echo $kode."Not Registered";
            }
                }else{
                echo 'error';
            }
?>
