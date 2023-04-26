package com.example.quan_li_dien_nuoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton themPhong = (ImageButton) findViewById(R.id.btnThemPhong);
        themPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, themPhong.class);
                startActivity(i);
            }
        });

        ImageButton DSDienNuoc = (ImageButton) findViewById(R.id.btnXemDSDienNuoc);
        DSDienNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DSDienNuoc.class);
                startActivity(i);
            }
        });
        ImageButton themDienNuoc = (ImageButton) findViewById(R.id.btnThemDienNuoc);
        themDienNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, themDienNuoc.class);
                startActivity(i);
            }
        });
    }
}