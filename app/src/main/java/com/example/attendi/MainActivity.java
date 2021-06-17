package com.example.attendi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
public class MainActivity extends AppCompatActivity {
    ImageButton mteacher, mstudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemSetting();

        mteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainActivity.this, login.class);
                startActivity(login);
            }
        });

        mstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update--------------------------------------------------------
                //if teacher and students need different login pages
                Intent login = new Intent(MainActivity.this, login.class);
                startActivity(login);
            }
        });
    }

    public void itemSetting(){
        mteacher = findViewById(R.id.btn1);
        mstudent = findViewById(R.id.btn2);
    }

}