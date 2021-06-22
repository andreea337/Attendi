package com.example.attendi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class schedule_teacher extends AppCompatActivity{
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

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(listStr);
        recyclerView.setAdapter(adapter);
//        List<Map<String , Object>> l = new ArrayList<>();
//        db.collection("professors")
//                .document("Andy")
//                .collection("day")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            int i = 0;
//                            Map<String, Object> m = new HashMap<>();
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                m  = document.getData();
//                                l.add(m);
////                                Log.d(TAG, document.getId() + " = " +m+'\n'+ l.get(i));
////                                Log.d(TAG, l.size()+"");
//                                i++;
//                            }
//                        } else {
//                            Log.d(TAG, "Error getting documents: ", task.getException());
//                        }
////                        Log.d(TAG, l.size()+"");
//                        Map<String, String> data = new HashMap<>();
//                        List<String> input = new ArrayList<>();
//                        for(int i=0; i<7; i++){
//                            Log.d(TAG, l.get(i)+"");
//                            String s ="";
//                            for(Object key:l.get(i).keySet()){
//                                data.put("class", key.toString());
//                                data.put("time", l.get(i).get(key).toString());
//                                s += key.toString() +":"+ l.get(i).get(key).toString() + '\n';
//                                Log.d(TAG,s+"k");
//                            }
//                            input.add(s);
//                        }
//                        Log.d(TAG, input.size()+"");
//                        // 設定RecyclerView使用的LayoutManager
//                        // LayoutManager決定項目的排列方式。
//                        recyclerView.setLayoutManager(new LinearLayoutManager(schedule_teacher.this));
//
//                        // 建立RecyclerView的Adapter物件，傳入包含項目清單的List物件
//                        RecyclerViewAdapter adapter = new RecyclerViewAdapter(input);
//
//                        // 把Adapter物件傳給RecyclerView
//                        recyclerView.setAdapter(adapter);
//
//                    }
//                });

    }

    private void itemSetting(){

        recyclerView = findViewById(R.id.recyclerView);
    }
}
