package com.example.attendi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class class_info extends AppCompatActivity {

    RecyclerView recyclerView;
    private static final String TAG = "DocSnippets";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_info_lay);
        itemSetting();
        Intent intent = getIntent();
        int pos = intent.getIntExtra("pos", 0);
        String name = intent.getStringExtra("name");

        int day[] = {R.drawable.class_info_image_monday2, R.drawable.class_info_image_tuesday2, R.drawable.class_info_image_wednesday2,
                R.drawable.class_info_image_thursday2, R.drawable.class_info_image_friday2, R.drawable.class_info_image_saturday2,
                R.drawable.class_info_image_sunday2};
        img.setImageResource(day[pos]);

        // 把項目清單準備好，放在一個List物件裏頭
        List<Map<String , Object>> l = new ArrayList<>();
        db.collection("professors")
                .document(name)
                .collection("day")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i = 0;
                            Map<String, Object> m = new HashMap<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                m  = document.getData();
                                l.add(m);
                                i++;
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                        List<String> data = new ArrayList<>();
                        for(Object key:l.get(pos).keySet()){
                            String s ="";
                            s += key.toString() +":"+ l.get(pos).get(key).toString() + '\n';
                            data.add(s);
                        }
                        //recyclerView.setLayoutManager(new LinearLayoutManager(class_info.this));
                        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                                StaggeredGridLayoutManager.VERTICAL));
                        viewAdapter_class adapter = new viewAdapter_class(data);
                        recyclerView.setAdapter(adapter);
                    }
                });

    }

    private void itemSetting(){
        recyclerView = findViewById(R.id.recyclerView);
        img = findViewById(R.id.class_info_image);
    }
}
