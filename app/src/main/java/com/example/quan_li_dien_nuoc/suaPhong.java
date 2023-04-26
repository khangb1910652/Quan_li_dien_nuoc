package com.example.quan_li_dien_nuoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class suaPhong extends AppCompatActivity {

    private EditText edtMaDay, edtMaPhong;
    private DBHandler dbHandler;
    String maDay, maPhong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_phong);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtMaDay = findViewById(R.id.maDay);
        edtMaPhong = findViewById(R.id.maPhong);
//        editNoteBtn = findViewById(R.id.idBtnEditNote);

        // on below line we are initializing our dbhandler class.
        dbHandler = new DBHandler(suaPhong.this);

        maDay = getIntent().getStringExtra("maDay");
        maPhong = getIntent().getStringExtra("maPhong");
        edtMaDay.setText(maDay);
        edtMaPhong.setText(maPhong);
    }
    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {

        getMenuInflater().inflate(R.menu.save_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {

        switch (item.getItemId()){
            case android.R.id.home:
                Intent i = new Intent(suaPhong.this, themPhong.class);
                startActivity(i);
                break;
            case R.id.idSave:
                String maDayEdt = edtMaDay.getText().toString();
                String maPhongEdt = edtMaPhong.getText().toString();
                if (maDayEdt.isEmpty() || maPhongEdt.isEmpty()) {
                    Toast.makeText(suaPhong.this, "Vui lòng nhập thông tin cần sửa!",
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                boolean success = dbHandler.suaPhong(maDay, maPhong , maDayEdt, maPhongEdt);
                if (success) {
                    Toast.makeText(suaPhong.this, "Đã sửa thông tin phòng!",
                            Toast.LENGTH_SHORT).show();
                    Intent j = new Intent(suaPhong.this, themPhong.class);
                    startActivity(j);
                } else {
                    Toast.makeText(suaPhong.this, "Không thể sửa thông tin phòng do đã có phòng tồn tại!",
                            Toast.LENGTH_SHORT).show();
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}