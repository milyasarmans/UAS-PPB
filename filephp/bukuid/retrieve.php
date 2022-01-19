<?php
	require("koneksi.php");
	$perintah = "select *from tbl_buku";
	$eksekusi = mysqli_query($konek,$perintah);
	$cek = mysqli_affected_rows($konek);

	if($cek>0){
		$response["kode"] = 1;
		$response["pesan"] = "Data Tersedia"; 
		$response["data"] = array();

		while($ambil = mysqli_fetch_object($eksekusi)){
			$Ar["id"]=$ambil->id;
			$Ar["judul"]=$ambil->judul;
			$Ar["penulis"]=$ambil->penulis;
			$Ar["harga"]=$ambil->harga;
			$Ar["detail"]=$ambil->detail;

			array_push($response["data"],$Ar);
		}
	}else{
		$response["kode"] = 0;
		$response["pesan"] = "Data Tidak Tersedia"; 
	}

	echo json_encode($response);
	mysqli_close($konek);

		