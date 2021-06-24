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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
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

        //set all students to false
        //setToFalse(id);
        setFalseValidate(new setFalse(){
            @Override
            public void onCallback(boolean value) {
                if(value == true){
                    //set firestore time
                    setTimeToFirestore(id);
                }
                else {
                    Log.d(TAG, "setFalse callback error");
                }
            }
        },id);

        //display
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
            }
        },0,5000);
        //0.5秒後每隔5秒做一次

    }

    private void setAdapter(List<String> str) {
        ArrayAdapter<String> adapter=
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,str);
        lvShow.setAdapter(adapter);
    }

    public void itemSetting(){
        lvShow=(ListView)findViewById(R.id.lvShow);
    }

    public interface setFalse{
        void onCallback(boolean value);
    }
    public void setFalseValidate(final setFalse callback, String id){
        db.collection(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG,"SUCCESS in SetToFalse");
                                document.getReference().update("check", false);
                            }
                            callback.onCallback(true);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                            callback.onCallback(false);
                        }
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
                        Log.d(TAG,"SUCCESS");
                    }
                });
    }

}