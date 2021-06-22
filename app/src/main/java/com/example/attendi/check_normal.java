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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Log.d(TAG, name+id.trim());
        txt.setText(id);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

            }
        });
    }

    private void itemSetting() {
        txt = findViewById(R.id.txt_chk_normal);
        btn = findViewById(R.id.chkbtn);
        outcome = findViewById(R.id.txt_chk_normal_out);
    }
}
