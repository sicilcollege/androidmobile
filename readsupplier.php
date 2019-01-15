<?php
	$mysql_hostname = "localhost";
    $mysql_user = "root";
    $mysql_password = "";
    $mysql_database = "uasandroidsicil";
    $db = mysqli_connect($mysql_hostname, $mysql_user, $mysql_password,$mysql_database) or die("Opps some thing went wrong");
     
	//Membuat SQL Query
	$sql = "SELECT * FROM sclsupplier";

	//Mendapatkan Hasil
	$r = mysqli_query($db,$sql);

	//Membuat Array Kosong
	$result = array();

	while($row = mysqli_fetch_array($r)){

		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat
		array_push($result,array(
		"id_supplier"=>$row['id_supplier'],
		"nama_supplier"=>$row['nama_supplier'],
        "telp_supplier"=>$row['telp_supplier'],
        "alamat_supplier"=>$row['alamat_supplier'],
		));
	}

	//Menampilkan Array dalam Format JSON
	echo json_encode(array('result'=>$result));

	mysqli_close($db);
?>