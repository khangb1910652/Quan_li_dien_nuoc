package com.example.quan_li_dien_nuoc;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DienNuocRVAdapter extends RecyclerView.Adapter<DienNuocRVAdapter.ViewHolder> {
    // variable for our array list and context
    private ArrayList<DienNuocModel> dienNuocModalArrayList;
    private Context context;
    private DBHandler dbHandler;
    private String maDienNuoc;

    // constructor
    public DienNuocRVAdapter(ArrayList<DienNuocModel> dienNuocModalArrayList, Context context) {
        this.dienNuocModalArrayList = dienNuocModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_xem_dien_nuoc,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // on below line we are setting data
        // to our views of recycler view item.
        DienNuocModel modal = dienNuocModalArrayList.get(position);
        holder.maDayTV.setText(modal.layMaDay());
        holder.maPhongTV.setText(modal.layMaPhong());
        holder.ngayGhiTV.setText(modal.layNgayGhi());
        holder.chiSoDienTV.setText(modal.layChiSoDien());
        holder.chiSoNuocTV.setText(modal.layChiSoNuoc());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dbHandler = new DBHandler(v.getContext());
                PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                popupMenu.getMenuInflater().inflate(R.menu.context_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.idDelete:
                                dbHandler.xoaDienNuoc(modal.layMaDienNuoc());
                                Toast.makeText(v.getContext(), "Đã xoá điện nước này!",
                                        Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(v.getContext(), DSDienNuoc.class);
                                context.startActivity(i);
                                return true;
                            case R.id.idEdit:
                                Intent j = new Intent(context, suaDienNuoc.class);
                                j.putExtra("maDay", modal.layMaDay());
                                j.putExtra("maPhong", modal.layMaPhong());
                                j.putExtra("ngayGhi", modal.layNgayGhi());
                                j.putExtra("chiSoDien", modal.layChiSoDien());
                                j.putExtra("chiSoNuoc", modal.layChiSoNuoc());
                                context.startActivity(j);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return dienNuocModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView maDayTV, maPhongTV, ngayGhiTV, chiSoDienTV, chiSoNuocTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            maDayTV = itemView.findViewById(R.id.TVMaDay);
            maPhongTV = itemView.findViewById(R.id.TVMaPhong);
            ngayGhiTV = itemView.findViewById(R.id.TVNgayGhi);
            chiSoDienTV = itemView.findViewById(R.id.TVChiSoDien);
            chiSoNuocTV = itemView.findViewById(R.id.TVChiSoNuoc);
        }
    }
}
