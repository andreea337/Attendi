package com.example.attendi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class random extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton btn;
    private static final String TAG = "DocSnippets";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<String> stringList = new ArrayList<>();
    List<String> name = new ArrayList<>();
    List<String> pick = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        itemSetting();

        //get intent data
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        //set firestore time
        setTimeToFirestore(id);


        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //get student data
                db.collection(id)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                int num = 0;
                                for(QueryDocumentSnapshot doc : task.getResult()){
                                    if(!doc.getId().equals("teacher")){
                                        name.add(doc.getId());
                                        num++;
                                    }
                                    //Log.d(TAG,doc.getId()+"num:"+num);
                                }
                                int[] call = new int[5];
                                Random r = new Random();

                                for (int i=0; i<5; i++){
                                    call[i] = r.nextInt(num);
                                    for (int j=0; j<i;){			// 與前數列比較，若有相同則再取亂數
                                        if (call[j]==call[i]){
                                            call[i] = r.nextInt(num);
                                            j=0;			// 避面重新亂數後又產生相同數字，若出現重覆，迴圈從頭開始重新比較所有數
                                        }
                                        else j++;			// 若都不重複則下一個數
                                    }
                                }
                                for(int i=0; i<5; i++){
                                    pick.add(name.get(call[i]));
                                }

                                //set recyclerviewdata
                                recyclerView.setLayoutManager(new LinearLayoutManager(random.this));
                                db.collection(id)
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                for (QueryDocumentSnapshot doc : task.getResult()) {
                                                    for (int i = 0; i < 5; i++) {
                                                        if (pick.get(i).equals(doc.getId())) {
                                                            String name = pick.get(i);
                                                            Boolean chk = doc.getBoolean("check");
                                                            String s = name + " " + chk;
                                                            stringList.add(s);
                                                            //set selected students check to false
                                                            doc.getReference().update("check", false);
                                                            break;
                                                        }
                                                    }
                                                }
                                                adapter_random_teacher adapter = new adapter_random_teacher(stringList);
                                                recyclerView.setAdapter(adapter);

                                                Timer timer = new Timer();
                                                timer.schedule(new TimerTask() {
                                                    @Override
                                                    public void run() {
                                                        db.collection(id)
                                                                .get()
                                                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                        stringList.clear();
                                                                        for (QueryDocumentSnapshot doc : task.getResult()) {
                                                                            for (int i = 0; i < 5; i++) {
                                                                                if (pick.get(i).equals(doc.getId())) {
                                                                                    String name = pick.get(i);
                                                                                    Boolean chk = doc.getBoolean("check");
                                                                                    String s = name + " " + chk;
                                                                                    stringList.add(s);
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        //notify recyclerviewdata changed
                                                                        //adapter_random_teacher adapter = new adapter_random_teacher(stringList);
                                                                        adapter.notifyDataSetChanged();
                                                                    }

                                                                });

                                                    }
                                                },5000,5000);
                                            }
                                        });
                            }
                        });



            }
        });
    }
    public void setTimeToFirestore(String id){

        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        sdFormat.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
        Date date = new Date();
        String time = sdFormat.format(date).trim();
        Map<String, Object> docData = new HashMap<>();
        docData.put("date", time);
        docData.put("check", true);

        db.collection(id)
                .document("teacher")
                .set(docData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(random.this, "Time is set!", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void itemSetting(){
        recyclerView = findViewById(R.id.recyclerView);
        btn = findViewById(R.id.btn_random_active);
    }
}