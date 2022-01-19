package com.milyasarmans.bukuid.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.milyasarmans.bukuid.API.APIRequesetData;
import com.milyasarmans.bukuid.API.RetroServer;
import com.milyasarmans.bukuid.Activity.MainActivity;
import com.milyasarmans.bukuid.Activity.Update;
import com.milyasarmans.bukuid.Model.DataModel;
import com.milyasarmans.bukuid.Model.ResponseModel;
import com.milyasarmans.bukuid.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listdata;
    private  int idBuku;
    private List<DataModel> listBuku;


    public AdapterData(Context ctx, List<DataModel> listdata){
        this.ctx = ctx;
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listdata.get(position);
        holder.tvId.setText(String.valueOf(dm.getId()));
        holder.tvJudul.setText(dm.getJudul());
        holder.tvPenulis.setText(dm.getPenulis());
        holder.tvDetail.setText(dm.getDetail());
        holder.tvHarga.setText(dm.getHarga());
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvId,tvJudul, tvPenulis, tvDetail, tvHarga;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvPenulis = itemView.findViewById(R.id.tv_penulis);
            tvDetail = itemView.findViewById(R.id.tv_detail);
            tvHarga = itemView.findViewById(R.id.tv_harga);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                    dialogPesan.setMessage("Pilih Operasi yang akan Dilakukan");
                    dialogPesan.setCancelable(true);
                    idBuku = Integer.parseInt(tvId.getText().toString());

                    dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            deleteData();
                            dialogInterface.dismiss();
                            ((MainActivity) ctx).retrieveData();
                        }

                        private void deleteData() {
                            APIRequesetData ardData = RetroServer.konekRetrofit().create(APIRequesetData.class);
                            Call<ResponseModel> ambilData = ardData.ardDeleteData(idBuku);
                            ambilData.enqueue(new Callback<ResponseModel>() {
                                @Override
                                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                    int kode = response.body().getKode();
                                    String pesan = response.body().getPesan();

                                    Toast.makeText(ctx, "Kode : "+kode+"| Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<ResponseModel> call, Throwable t) {
                                    Toast.makeText(ctx, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                    dialogPesan.setNegativeButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getData();
                            dialogInterface.dismiss();
                        }
                    });
                    dialogPesan.show();

                    return false;
                }
            });
        }

        private void getData(){
            APIRequesetData ardData = RetroServer.konekRetrofit().create(APIRequesetData.class);
            Call<ResponseModel> hapusData = ardData.ardGetData(idBuku);
            hapusData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    listBuku = response.body().getData();

                    int varIdBuku = listBuku.get(0).getId();
                    String varJudulBuku = listBuku.get(0).getJudul();
                    String varPenulisBuku = listBuku.get(0).getPenulis();
                    String varDetailBuku = listBuku.get(0).getDetail();
                    String varHargaBuku = listBuku.get(0).getHarga();


                    Intent kirim = new Intent(ctx, Update.class);
                    kirim.putExtra("xId", varIdBuku);
                    kirim.putExtra("xJudul", varJudulBuku);
                    kirim.putExtra("xPenulis", varPenulisBuku);
                    kirim.putExtra("xDetail", varDetailBuku);
                    kirim.putExtra("xHarga", varHargaBuku);
                    ctx.startActivity(kirim);

                    Toast.makeText(ctx, "Kode : "+kode+"| Pesan : "+pesan+"| Data : "+varJudulBuku+"| "+varPenulisBuku, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
