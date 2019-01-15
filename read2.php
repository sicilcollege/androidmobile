<?php
	$mysql_hostname = "localhost";
    $mysql_user = "root";
    $mysql_password = "";
    $mysql_database = "android2";
    $db = mysqli_connect($mysql_hostname, $mysql_user, $mysql_password,$mysql_database) or die("Opps some thing went wrong");
     
	//Membuat SQL Query
	$sql = "SELECT * FROM karyawan2";

	//Mendapatkan Hasil
	$r = mysqli_query($db,$sql);

	//Membuat Array Kosong
	$result = array();

	while($row = mysqli_fetch_array($r)){

		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat
		array_push($result,array(
			"kode"=>$row['kode'],
			"nama"=>$row['nama'],
      "satuan"=>$row['satuan'],
      "harga_jual"=>$row['harga_jual'],
      "harga_beli"=>$row['harga_beli'],
      "jumlah"=>$row['jumlah']
		));
	}

	//Menampilkan Array dalam Format JSON
	echo json_encode(array('result'=>$result));

	mysqli_close($db);
?>