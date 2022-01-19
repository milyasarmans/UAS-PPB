package com.milyasarmans.bukuid.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.milyasarmans.bukuid.API.APIRequesetData;
import com.milyasarmans.bukuid.API.RetroServer;
import com.milyasarmans.bukuid.Model.ResponseModel;
import com.milyasarmans.bukuid.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Insert extends AppCompatActivity {

    private EditText etJudul, etPenulis, etHarga, etDetail;
    private Button btnInsert, btnReset;
    private String judul, penulis, harga, detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        etJudul = findViewById(R.id.tv_judul);
        etPenulis = findViewById(R.id.tv_penulis);
        etDetail = findViewById(R.id.tv_detail);
        etHarga = findViewById(R.id.tv_harga);
        btnInsert = findViewById(R.id.btn_add);
        btnReset = findViewById(R.id.btn_reset);

        btnReset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                etJudul.setText("");
                etPenulis.setText("");
                etDetail.setText("");
                etHarga.setText("");
            }
        });

        btnInsert.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                judul = etJudul.getText().toString();
                penulis = etPenulis.getText().toString();
                detail = etDetail.getText().toString();
                harga = etHarga.getText().toString();

                if(judul.trim().equals("")){
                    etJudul.setError("Harus Diisi!");
                }
                else if(penulis.trim().equals("")){
                    etPenulis.setError("Harus Diisi!");
                }
                else if(detail.trim().equals("")){
                    etDetail.setError("Harus Diisi!");
                }
                else if(harga.trim().equals("")){
                    etHarga.setError("Harus Diisi!");
                }
                else{
                    createData();
                }
            }
            public void createData(){
                APIRequesetData ardData = RetroServer.konekRetrofit().create(APIRequesetData.class);
                Call<ResponseModel> simpanData = ardData.ardCreateData(judul,penulis,detail,harga);
                simpanData.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        int kode = response.body().getKode();
                        String pesan = response.body().getPesan();

                        Toast.makeText(Insert.this, "Kode: "+kode+"| Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(Insert.this, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.kontak_id) {
            Intent moveIntent = new Intent(Insert.this, MainActivity.KontakActivity.class);
            startActivity(moveIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    public static class KontakActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_kontak);
        }
    }
}