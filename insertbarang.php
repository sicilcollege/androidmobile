<?php

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "";
$mysql_database = "uasandroidsicil";
$db = mysqli_connect($mysql_hostname, $mysql_user, $mysql_password,$mysql_database) or die("Opps some thing went wrong");
 
    if(isset($_SERVER['REQUEST_METHOD'])){
        $id_barang = $_POST['id_barang'];
        $nama_barang = $_POST['nama_barang'];
        $satuan_barang = $_POST['satuan_barang'];
        $stok_barang = $_POST['stok_barang'];
        $harga_beli_barang = $_POST['harga_beli_barang'];
        $harga_jual_barang = $_POST['harga_jual_barang'];
        $get_result = mysqli_query($db,"INSERT INTO sclbarang(id_barang,nama_barang,satuan_barang,stok_barang,harga_beli_barang,harga_jual_barang) VALUES ('$id_barang','$nama_barang','$satuan_barang','$stok_barang','$harga_beli_barang','$harga_jual_barang')"); 
 
        if($get_result === true){
            echo "Successfully Registered";
        }else{
        echo $kode."Not Registered";
            }
                }else{
                echo 'error';
            }
?>
