package com.example.attendi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class attendi_mode extends AppCompatActivity {

    Button mbtn_normal, mbtn_ball, mbtn_random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendi_mode_lay);
        itemSetting();
        Intent intent = getIntent();
        String id = intent.getStringExtra("id"); //get class id

        mbtn_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent normal = new Intent(attendi_mode.this, normal.class);
                normal.putExtra("id", id);
                startActivity(normal);
            }
        });

        mbtn_ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mbtn_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent random = new Intent(attendi_mode.this, random.class);
                random.putExtra("id", id);
                startActivity(random);
            }
        });
    }

    public void itemSetting(){
        mbtn_normal = findViewById(R.id.btn_normal);
        mbtn_ball = findViewById(R.id.btn_ball);
        mbtn_random = findViewById(R.id.btn_random);
    }
}
