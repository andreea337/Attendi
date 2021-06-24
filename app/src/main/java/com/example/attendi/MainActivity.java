package com.example.attendi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

public class MainActivity extends AppCompatActivity {
    ImageButton mteacher, mstudent;
    private static final String TAG = "DocSnippets";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemSetting();

        mteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent 變數 = new Intent(現在的activity.this, 要前往的activity.class);
                startActivity(變數);
                */
                Intent login = new Intent(MainActivity.this, login.class);
                startActivity(login);
            }
        });

        mstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update--------------------------------------------------------
                //if teacher and students need different login pages
                Intent login = new Intent(MainActivity.this, login_student.class);
                startActivity(login);
            }
        });
        db.collection("java123")
                .document("teacher")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    Date date = new Date();
                    String d;
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        //get data
                        d = documentSnapshot.getString("date");

                        //clean teacher timestamp: turn string to date, then to long
                        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                        sdFormat.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
                        Date teacher = null;
                        try {
                            teacher = sdFormat.parse(d);

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        long begin = teacher.getTime();

                        //set student timestamp
                        long now = date.getTime();
                        Log.d(TAG,teacher.toString()+'\n'+now+'\n'+begin);
                        Log.d(TAG, (now-begin)/1000+"");
                        //compute 2 timestamp diff

                    }
                });

    }

    public void itemSetting(){
        mteacher = findViewById(R.id.btn1);
        mstudent = findViewById(R.id.btn2);
    }

}