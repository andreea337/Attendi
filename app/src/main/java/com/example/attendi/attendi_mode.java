package com.example.attendi;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class attendi_mode extends AppCompatActivity {

    Button mbtn_normal, mbtn_ball, mbtn_random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendi_mode_lay);
        itemSetting();
    }

    public void itemSetting(){
        mbtn_normal = findViewById(R.id.btn_normal);
        mbtn_ball = findViewById(R.id.btn_ball);
        mbtn_random = findViewById(R.id.btn_random);
    }
}
