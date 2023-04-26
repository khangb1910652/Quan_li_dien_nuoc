package com.example.quan_li_dien_nuoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class suaDienNuoc extends AppCompatActivity {

    private EditText edtChiSoDien, edtChiSoNuoc;
    private TextView tvMaDay, tvMaPhong, tvNgayGhi;
    private DBHandler dbHandler;
    String maDay, maPhong, ngayGhi, chiSoDien, chiSoNuoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_dien_nuoc);

        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvMaDay = findViewById(R.id.maDay);
        tvMaPhong = findViewById(R.id.maPhong);
        tvNgayGhi = findViewById(R.id.ngayGhi);
        edtChiSoDien = findViewById(R.id.chiSoDien);
        edtChiSoNuoc = findViewById(R.id.chiSoNuoc);
//        editNoteBtn = findViewById(R.id.idBtnEditNote);

        // on below line we are initializing our dbhandler class.
        dbHandler = new DBHandler(suaDienNuoc.this);

        maDay = getIntent().getStringExtra("maDay");
        maPhong = getIntent().getStringExtra("maPhong");
        ngayGhi = getIntent().getStringExtra("ngayGhi");
        chiSoDien = getIntent().getStringExtra("chiSoDien");
        chiSoNuoc = getIntent().getStringExtra("chiSoNuoc");
        tvMaDay.setText(maDay);
        tvMaPhong.setText(maPhong);
        tvNgayGhi.setText(ngayGhi);
        edtChiSoDien.setText(chiSoDien);
        edtChiSoNuoc.setText(chiSoNuoc);
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
                Intent i = new Intent(suaDienNuoc.this, DSDienNuoc.class);
                startActivity(i);
                break;
            case R.id.idSave:
                String chiSoDienEdt = edtChiSoDien.getText().toString();
                String chiSoNuocEdt = edtChiSoNuoc.getText().toString();
                if (chiSoDienEdt.isEmpty() || chiSoNuocEdt.isEmpty()) {
                    Toast.makeText(suaDienNuoc.this, "Vui lòng nhập thông tin cần sửa!",
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                dbHandler.suaDienNuoc(ngayGhi, maDay, maPhong , chiSoDienEdt, chiSoNuocEdt);
                Toast.makeText(suaDienNuoc.this, "Đã sửa thông tin điện nước!",
                        Toast.LENGTH_SHORT).show();
                Intent j = new Intent(suaDienNuoc.this, DSDienNuoc.class);
                startActivity(j);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}