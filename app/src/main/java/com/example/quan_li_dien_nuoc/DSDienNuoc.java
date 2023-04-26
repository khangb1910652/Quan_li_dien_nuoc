package com.example.quan_li_dien_nuoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class DSDienNuoc extends AppCompatActivity {

    private DBHandler dbHandler;

    String selectedItemDay, selectedItemPhong;
    private Spinner spinnerMaDay, spinnerMaPhong;
    private List<String> day, phong;

    private ArrayList<DienNuocModel> dienNuocModelArrayList;
    private DienNuocRVAdapter dienNuocRVAdapter;
    private RecyclerView DSDienNuocRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsdien_nuoc);

        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHandler = new DBHandler(this);



        spinnerMaDay = findViewById(R.id.spinnerMaDay);
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
                ArrayAdapter<String> adapter = new ArrayAdapter<>(DSDienNuoc.this,
                        android.R.layout.simple_spinner_item, phong);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerMaPhong.setAdapter(adapter);
                spinnerMaPhong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedItemPhong = parent.getItemAtPosition(position).toString();

                        dienNuocModelArrayList = new ArrayList<>();
                        dienNuocModelArrayList = dbHandler.danhSachDienNuoc(selectedItemDay,selectedItemPhong);
                        dienNuocRVAdapter = new DienNuocRVAdapter(dienNuocModelArrayList, DSDienNuoc.this);
                        DSDienNuocRV = findViewById(R.id.idRVDSDienNuoc);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DSDienNuoc.this, RecyclerView.VERTICAL, false);
                        DSDienNuocRV.setLayoutManager(linearLayoutManager);
                        DSDienNuocRV.setAdapter(dienNuocRVAdapter);
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent i = new Intent(DSDienNuoc.this, MainActivity.class);
                startActivity(i);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}