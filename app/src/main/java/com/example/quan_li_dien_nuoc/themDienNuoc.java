package com.example.quan_li_dien_nuoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Spinner;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class themDienNuoc extends AppCompatActivity {

    private EditText edtChiSoDien, edtChiSoNuoc;
    private DBHandler dbHandler;
//    private ArrayList<PhongModal> phongModalArrayList;
//    private PhongRVAdapter phongRVAdapter;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    String currentTime = sdf.format(c.getTime());

    String selectedItemDay, selectedItemPhong;
    private Spinner spinnerMaDay, spinnerMaPhong;
    private List<String> day, phong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_dien_nuoc);

        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtChiSoDien = findViewById(R.id.chiSoDien);
        edtChiSoNuoc = findViewById(R.id.chiSoNuoc);

        spinnerMaDay = findViewById(R.id.spinnerMaDay);
        dbHandler = new DBHandler(this);
        day = dbHandler.layDSDay();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, day);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaDay.setAdapter(adapter);
        spinnerMaDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItemDay = parent.getItemAtPosition(position).toString();
                spinnerMaPhong = findViewById(R.id.spinnerMaPhong);
                phong = dbHandler.layDSPhong(selectedItemDay);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(themDienNuoc.this,
                        android.R.layout.simple_spinner_item, phong);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerMaPhong.setAdapter(adapter);
                spinnerMaPhong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedItemPhong = parent.getItemAtPosition(position).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

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
                Intent i = new Intent(themDienNuoc.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.idSave:
                String chiSoDien = edtChiSoDien.getText().toString();
                String chiSoNuoc = edtChiSoNuoc.getText().toString();
                if (chiSoDien.isEmpty() && chiSoNuoc.isEmpty() ) {
                    Toast.makeText(themDienNuoc.this, "Vui lòng nhập đầy đủ thông tin!",
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                dbHandler.themMoiDienNuoc(String.valueOf(currentTime), chiSoDien, chiSoNuoc, selectedItemDay, selectedItemPhong);
                Toast.makeText(themDienNuoc.this, "Chỉ số điện nước đã được thêm thành công!",
                        Toast.LENGTH_SHORT).show();
                edtChiSoDien.setText("");
                edtChiSoNuoc.setText("");
                Intent j = new Intent(themDienNuoc.this, themDienNuoc.class);
                startActivity(j);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}