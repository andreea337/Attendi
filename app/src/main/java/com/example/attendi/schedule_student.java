package com.example.attendi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class schedule_student extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        itemSetting();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");

        List<String> listStr = new ArrayList<>();
        listStr.add(name);

        for (int i = 0; i < 6; i++) {
            listStr.add(new String("***更改我***"));
        }
        RecyclerView recyclerView = findViewById(R.id.RCview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerViewAdapter_student adapter = new RecyclerViewAdapter_student(listStr);
        recyclerView.setAdapter(adapter);

    }

    private void itemSetting(){
        recyclerView = findViewById(R.id.recyclerView);
    }
}