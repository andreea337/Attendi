package com.example.attendi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private static final String TAG = "DocSnippets";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView memail, mpwd;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemSetting();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login(){
        db.collection("professors")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot doc : task.getResult()){
                                String a=doc.getString("ID");
                                String b=doc.getString("pwd");
                                String a1=memail.getText().toString().trim();
                                String b1=mpwd.getText().toString().trim();
                                if(a.equalsIgnoreCase(a1) & b.equalsIgnoreCase(b1)) {
//                                    Intent home = new Intent(MainActivity.this, home_activity.class);
//                                    startActivity(home);
                                    Toast.makeText(MainActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                                    break;
                                }else
                                    Toast.makeText(MainActivity.this, "Cannot login,incorrect Email and Password", Toast.LENGTH_SHORT).show();

                            }

                        }
                    }
                });
    }

    public void itemSetting(){
        memail = findViewById(R.id.pid);
        mpwd = findViewById(R.id.pwd);
        button = findViewById(R.id.button);
    }

}