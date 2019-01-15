<?php
	$mysql_hostname = "localhost";
    $mysql_user = "root";
    $mysql_password = "";
    $mysql_database = "uasandroidsicil";
    $db = mysqli_connect($mysql_hostname, $mysql_user, $mysql_password,$mysql_database) or die("Opps some thing went wrong");
	 
	$ambil_id_karyawan = $_GET['id_karyawan'];
	
	//Membuat SQL Query
	$sql = "SELECT * FROM sclkaryawan WHERE id_karyawan=$ambil_id_karyawan";

	//Mendapatkan Hasil
	$r = mysqli_query($db,$sql);

	//Membuat Array Kosong
	$result = array();

	while($row = mysqli_fetch_array($r)){

		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat
		array_push($result,array(
		"id_karyawan"=>$row['id_karyawan'],
		"nama_karyawan"=>$row['nama_karyawan'],
        "telp_karyawan"=>$row['telp_karyawan'],
        "alamat_karyawan"=>$row['alamat_karyawan'],
        "password_karyawan"=>$row['password_karyawan'],
		));
	}

	//Menampilkan Array dalam Format JSON
	echo json_encode(array('result'=>$result));

	mysqli_close($db);
?>