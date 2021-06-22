package com.example.attendi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class login_student extends AppCompatActivity {
    private static final String TAG = "DocSnippets";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ImageButton mLight,mBtn_Login, mBtn_Back;
    private Fragment_Easter_Egg mFragEgg;
    TextView memail, mpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_student);
        itemSetting();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLay1, mFragEgg, "Surprise")
                .hide(mFragEgg)
                .commit();

        mLight.setOnClickListener(light_on);
        mBtn_Login.setOnClickListener(Student_Login);
        mBtn_Back.setOnClickListener(Student_Back);
    }

    public void login(){
        db.collection("students")
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
                                    Toast.makeText(login_student.this, c + " Welcome", Toast.LENGTH_LONG).show();
                                    //-------------------------------------------------------------------------
                                    Intent student_main = new Intent(login_student.this, schedule_student.class);
                                    student_main.putExtra("name", c);
                                    Log.d("now3",c+"now");
                                    student_main.putExtra("id", a);
                                    startActivity(student_main);
                                    break;
                                }else
                                    Toast.makeText(login_student.this, "Cannot login,incorrect Email and Password", Toast.LENGTH_SHORT).show();

                            }

                        }
                    }
                });


    }

    public void showHiddenPage(){
        getSupportFragmentManager().beginTransaction()
                .show(mFragEgg)
                .commit();
    }

    public void hidePage(){
        getSupportFragmentManager().beginTransaction()
                .hide(mFragEgg)
                .commit();
    }

    private View.OnClickListener light_on = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            showHiddenPage();
        }
    };

    private View.OnClickListener Student_Login = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            login();
        }
    };

    private View.OnClickListener Student_Back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent back = new Intent ( login_student.this, MainActivity.class);
            startActivity(back);
        }
    };

    public void itemSetting(){
        mLight = findViewById(R.id.light);
        mBtn_Login = findViewById(R.id.btn_login);
        mBtn_Back = findViewById(R.id.btn_back);
        mFragEgg = new Fragment_Easter_Egg();
        memail = findViewById(R.id.sid);
        mpwd = findViewById(R.id.pwd);
    }
}