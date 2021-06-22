package com.example.attendi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

        Date date = new Date();
        String[] str = date.toString().split("GMT");
        String time = str[0].trim();
        Log.d(TAG, str[0].trim());
        Map<String, Object> docData = new HashMap<>();
        docData.put("date", time);

        db.collection("java123")
                .document(time)
                .set(docData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG,"SUCCESS");
                    }
                });


    }

    public void itemSetting(){
        mteacher = findViewById(R.id.btn1);
        mstudent = findViewById(R.id.btn2);
    }

}