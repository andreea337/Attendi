package com.example.attendi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class class_info extends AppCompatActivity {

    RecyclerView recyclerView;
    private static final String TAG = "DocSnippets";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_info_lay);
        itemSetting();
        Intent intent = getIntent();
        int pos = intent.getIntExtra("pos", 0);
        String name = intent.getStringExtra("name");
        Log.d(TAG, name);
        // 把項目清單準備好，放在一個List物件裏頭
        //update---------------------------

        List<Map<String , Object>> l = new ArrayList<>();
        db.collection("professors")
                .document(name)
                .collection("day")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i = 0;
                            Map<String, Object> m = new HashMap<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                m  = document.getData();
                                l.add(m);
//                                Log.d(TAG, document.getId() + " = " +m+'\n'+ l.get(i));
//                                Log.d(TAG, l.size()+"");
                                i++;
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
//                        Log.d(TAG, l.size()+"");

                        List<String> data = new ArrayList<>();
                        for(Object key:l.get(pos).keySet()){
                            String s ="";
                            s += key.toString() +":"+ l.get(pos).get(key).toString() + '\n';
                            data.add(s);
                            //Log.d(TAG,s+"-------------"+data.size());
                        }
                        recyclerView.setLayoutManager(new LinearLayoutManager(class_info.this));
                        viewAdapter_class adapter = new viewAdapter_class(data);
                        recyclerView.setAdapter(adapter);
                    }
                });
        //匯入雲端資料庫的課程資料
//        List<String> list = new ArrayList<>();
//        list.add("AM 9:00\nJAVA and APP Develope");
//        list.add("AM 13:00\nDatabase Develope");
        // 設定RecyclerView使用的LayoutManager
        // LayoutManager決定項目的排列方式。
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        // 建立RecyclerView的Adapter物件，傳入包含項目清單的List物件
//        viewAdapter_class adapter = new viewAdapter_class(list);
//
//        // 把Adapter物件傳給RecyclerView
//        recyclerView.setAdapter(adapter);
    }

    private void itemSetting(){
        recyclerView = findViewById(R.id.recyclerView);
    }
}
