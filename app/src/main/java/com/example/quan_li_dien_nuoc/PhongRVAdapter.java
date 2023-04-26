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

public class PhongRVAdapter extends RecyclerView.Adapter<PhongRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<PhongModal> phongModalArrayList;
    private Context context;
    private DBHandler dbHandler;

    // constructor
    public PhongRVAdapter(ArrayList<PhongModal> phongModalArrayList, Context context) {
        this.phongModalArrayList = phongModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_xem_phong,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // on below line we are setting data
        // to our views of recycler view item.
        PhongModal modal = phongModalArrayList.get(position);
        holder.maDayTV.setText(modal.layMaDay());
        holder.maPhongTV.setText(modal.layMaPhong());

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
                                dbHandler.xoaPhong(modal.layMaDay(),modal.layMaPhong());
                                Toast.makeText(v.getContext(), "Đã xoá phòng!",
                                        Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(v.getContext(), themPhong.class);
                                context.startActivity(i);
                                return true;
                            case R.id.idEdit:
                                Intent j = new Intent(context, suaPhong.class);
                                j.putExtra("maDay", modal.layMaDay());
                                j.putExtra("maPhong", modal.layMaPhong());
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
        return phongModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView maPhongTV, maDayTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            maPhongTV = itemView.findViewById(R.id.TVMaPhong);
            maDayTV = itemView.findViewById(R.id.TVMaDay);
        }
    }

}

