package com.example.quan_li_dien_nuoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class themPhong extends AppCompatActivity {

    private EditText edtMaDay, edtMaPhong;
    private DBHandler dbHandler;
    private ArrayList<PhongModal> phongModalArrayList;
    private PhongRVAdapter phongRVAdapter;
    private RecyclerView DSPhongRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_phong);

        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtMaDay = findViewById(R.id.maDay);
        edtMaPhong = findViewById(R.id.maPhong);
        dbHandler = new DBHandler(themPhong.this);

        // initializing our all variables.
        phongModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(themPhong.this);

        // getting our course array
        // list from db handler class.
        phongModalArrayList = dbHandler.danhSachPhong();

        // on below line passing our array list to our adapter class.
        phongRVAdapter = new PhongRVAdapter(phongModalArrayList, themPhong.this);
        DSPhongRV = findViewById(R.id.idRVDSPhong);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(themPhong.this, RecyclerView.VERTICAL, false);
        DSPhongRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        DSPhongRV.setAdapter(phongRVAdapter);

    }
    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {

        getMenuInflater().inflate(R.menu.save_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent i = new Intent(themPhong.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.idSave:
                String maDay = edtMaDay.getText().toString();
                String maPhong = edtMaPhong.getText().toString();
                if (maDay.isEmpty() && maPhong.isEmpty() ) {
                    Toast.makeText(themPhong.this, "Vui lòng nhập đầy đủ thông tin!",
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                boolean success = dbHandler.themMoiPhong(maDay, maPhong);
                if (success) {
                    Toast.makeText(themPhong.this, "Phòng đã được thêm thành công!",
                            Toast.LENGTH_SHORT).show();
                    edtMaDay.setText("");
                    edtMaPhong.setText("");
                    Intent j = new Intent(themPhong.this, themPhong.class);
                    startActivity(j);
                } else {
                    Toast.makeText(themPhong.this, "Phòng đã tồn tại!",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}