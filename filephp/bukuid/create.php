<?php
    require("koneksi.php");

    $response = array();

    if($_SERVER['REQUEST_METHOD']=='POST'){
        $judul = $_POST["judul"];
        $penulis = $_POST["penulis"];
        $harga = $_POST["harga"];  
        $detail = $_POST["detail"];         

        $perintah = "INSERT INTO tbl_buku(judul, penulis, harga, detail) VALUES('$judul', '$penulis', '$harga', '$detail')";
        $eksekusi = mysqli_query($konek,$perintah);
        $cek = mysqli_affected_rows($konek);

        if($cek>0){
            $response["kode"]=1;
            $response["pesan"]="Simpan Data Berhasil";
        }
        else{
            $response["kode"]=0;
            $response["pesan"]="Simpan Data Gagal";
        }
    }

    else{
        $response["kode"]=0;
        $response["pesan"]="Tidak Ada Post Data";
    }

    echo json_encode($response);
    mysqli_close($konek);