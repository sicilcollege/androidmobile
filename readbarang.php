<?php
	$mysql_hostname = "localhost";
    $mysql_user = "root";
    $mysql_password = "";
    $mysql_database = "uasandroidsicil";
    $db = mysqli_connect($mysql_hostname, $mysql_user, $mysql_password,$mysql_database) or die("Opps some thing went wrong");
     
	//Membuat SQL Query
	$sql = "SELECT * FROM sclbarang";

	//Mendapatkan Hasil
	$r = mysqli_query($db,$sql);

	//Membuat Array Kosong
	$result = array();

	while($row = mysqli_fetch_array($r)){

		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat
		array_push($result,array(
		"id_barang"=>$row['id_barang'],
		"nama_barang"=>$row['nama_barang'],
		"satuan_barang"=>$row['satuan_barang'],
		"stok_barang"=>$row['stok_barang'],
        "harga_beli_barang"=>$row['harga_beli_barang'],
        "harga_jual_barang"=>$row['harga_jual_barang'],
		));
	}

	//Menampilkan Array dalam Format JSON
	echo json_encode(array('result'=>$result));

	mysqli_close($db);
?>