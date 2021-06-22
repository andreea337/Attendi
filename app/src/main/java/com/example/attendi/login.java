package com.example.attendi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;

public class login extends AppCompatActivity {
    private static final String TAG = "DocSnippets";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView memail, mpwd;
    ImageButton btnLogin, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_lay);
        itemSetting();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnBack.setOnClickListener(clickBack);
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
                                String c=doc.getString("Name");
                                String a1=memail.getText().toString().trim();
                                String b1=mpwd.getText().toString().trim();
                                if(a.equalsIgnoreCase(a1) & b.equalsIgnoreCase(b1)) {
                                    Toast.makeText(login.this, c + " Welcome", Toast.LENGTH_LONG).show();
                                    Intent teacher_main = new Intent(login.this, schedule_teacher.class);
                                    teacher_main.putExtra("name", c);
                                    teacher_main.putExtra("id", a);
                                    startActivity(teacher_main);
                                    break;
                                }else
                                    Toast.makeText(login.this, "Cannot login,incorrect Email and Password", Toast.LENGTH_SHORT).show();

                            }

                        }
                    }
                });


    }

    private View.OnClickListener clickBack = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent back = new Intent (login.this, MainActivity.class);
            startActivity(back);
        }
    };

    public void itemSetting(){
        memail = findViewById(R.id.pid);
        mpwd = findViewById(R.id.pwd);
        btnLogin = findViewById(R.id.btnLogin);
        btnBack = findViewById(R.id.btnBack);

    }

}