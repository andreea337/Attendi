package com.example.attendi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class class_info extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_info_lay);
        itemSetting();
        // 把項目清單準備好，放在一個List物件裏頭
        //update---------------------------
        //匯入雲端資料庫的課程資料
        List<String> list = new ArrayList<>();
        list.add("AM 9:00\nJAVA and APP Develope");
        list.add("AM 13:00\nDatabase Develope");
        // 設定RecyclerView使用的LayoutManager
        // LayoutManager決定項目的排列方式。
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 建立RecyclerView的Adapter物件，傳入包含項目清單的List物件
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list);

        // 把Adapter物件傳給RecyclerView
        recyclerView.setAdapter(adapter);
    }

    private void itemSetting(){
        recyclerView = findViewById(R.id.recyclerView);
    }
}
