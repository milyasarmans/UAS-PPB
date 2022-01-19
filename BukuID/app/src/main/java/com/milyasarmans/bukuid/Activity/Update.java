package com.milyasarmans.bukuid.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

public class Update extends AppCompatActivity {

    private int xId;
    private String xJudul, xPenulis, xDetail, xHarga;

    private EditText etJudul, etPenulis, etDetail, etHarga;
    private Button btnUpdate, btnReset;
    private String yJudul, yPenulis, yDetail, yHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent terima = getIntent();
        xId = terima.getIntExtra("xId", -1);
        xJudul = terima.getStringExtra("xJudul");
        xPenulis = terima.getStringExtra("xPenulis");
        xDetail = terima.getStringExtra("xDetail");
        xHarga = terima.getStringExtra("xHarga");

        etJudul = findViewById(R.id.tv_judul);
        etPenulis = findViewById(R.id.tv_penulis);
        etDetail = findViewById(R.id.tv_detail);
        etHarga = findViewById(R.id.tv_harga);
        btnUpdate = findViewById(R.id.btn_update);

        etJudul.setText(xJudul);
        etPenulis.setText(xPenulis);
        etDetail.setText(xDetail);
        etHarga.setText(xHarga);
        btnReset = findViewById(R.id.btn_reset);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etJudul.setText(xJudul);
                etPenulis.setText(xPenulis);
                etDetail.setText(xDetail);
                etHarga.setText(xHarga);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yJudul = etJudul.getText().toString();
                yPenulis = etPenulis.getText(). toString();
                yDetail = etDetail.getText().toString();
                yHarga = etHarga.getText().toString();
                updateData();
            }

            private void updateData() {
                APIRequesetData ardData = RetroServer.konekRetrofit().create(APIRequesetData.class);
                Call<ResponseModel> updateData = ardData.ardUpdateData(xId,yJudul,yPenulis,yDetail,yHarga);
                updateData.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        int kode = response.body().getKode();
                        String pesan = response.body().getPesan();

                        Toast.makeText(Update.this, "Kode: "+kode+"| Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(Update.this, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.kontak_id) {
            Intent moveIntent = new Intent(Update.this, MainActivity.KontakActivity.class);
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