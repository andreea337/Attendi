package com.example.attendi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class normal extends AppCompatActivity {
    private static final String TAG = "DocSnippets";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ListView lvShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        itemSetting();
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                db.collection(id)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                List<String> str = new ArrayList<>();
                                if(task.isSuccessful()){
                                    for(QueryDocumentSnapshot doc : task.getResult()){
                                        Boolean chk = doc.getBoolean("check");
                                        String name = doc.getId();
                                        String s = name + " " + chk;
                                        str.add(s);
                                    }
                                }
                                setAdapter(str);
                            }
                        });
                Log.d(TAG, "ds");
            }
        },500,15000);
        //0.5秒後每隔15秒做一次

    }

    private void setAdapter(List<String> str) {
        ArrayAdapter<String> adapter=
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,str);
        lvShow.setAdapter(adapter);
    }

    public void itemSetting(){
        lvShow=(ListView)findViewById(R.id.lvShow);
    }

}