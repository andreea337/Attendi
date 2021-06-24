package com.example.attendi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
import java.util.TimeZone;

public class check_normal extends AppCompatActivity {
    TextView txt, outcome;
    Button btn;
    private static final String TAG = "DocSnippets";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_normal);
        itemSetting();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name").trim();
        String id = intent.getStringExtra("id").trim();

        //diaplay course id
        txt.setText(id);

        //student click the sign in btn
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getValidate(new validate() {
                    @Override
                    public void onCallback(boolean value) {
                        Log.d(TAG, "SUCCESS outside" + value);
                        if (value == true) {
                            db.collection(id)
                                    .document(name)
                                    .update("check", true)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            outcome.setText("Success");
                                            Log.d(TAG, "DocumentSnapshot successfully updated!");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            outcome.setText("Failure");
                                            Log.w(TAG, "Error updating document", e);
                                        }
                                    });
                        } else {
                            outcome.setText("Timeout");
                        }
                    }
                },id);
            }
        });
    }

    public interface validate{
        void onCallback(boolean value);
    }

    public void getValidate(final validate callback, String id){

        db.collection(id)
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
                        //compute 2 timestamp diff
                        if(now - begin < 300000) {
                            callback.onCallback(true);
                            Log.d(TAG, "true");
                        }
                        else{
                            Log.d(TAG, "false");
                            callback.onCallback(false);
                        }
                    }
                });

    }

    private void itemSetting() {
        txt = findViewById(R.id.txt_chk_normal);
        btn = findViewById(R.id.chkbtn);
        outcome = findViewById(R.id.txt_chk_normal_out);
    }
}
