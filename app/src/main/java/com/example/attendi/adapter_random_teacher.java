package com.example.attendi;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class adapter_random_teacher extends RecyclerView.Adapter<adapter_random_teacher.ViewHolder> {

    private static final String TAG = "DocSnippets";
    // 儲存要顯示的資料
    private List<String> stringList;
    //private List<Map<String, Object>> stringList;


    // ViewHolder 是把項目中所有的 View 物件包起來。
    // 它在 onCreateViewHolder() 中使用。
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public CardView myCard;
        public LinearLayout lo;
        public TextView txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myCard = (CardView) itemView.findViewById(R.id.myCard);
            lo = (LinearLayout) itemView.findViewById(R.id.lo);
            txt = (TextView) itemView.findViewById(R.id.student_name);
            myCard.setCardElevation(0);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

//            int pos = getAdapterPosition();
//            Log.d(TAG, pos+"");
//            Intent class_info = new Intent(v.getContext(), class_info.class);
//            class_info.putExtra("pos", pos);
//            class_info.putExtra("name", stringList.get(0));
//            v.getContext().startActivity(class_info);
        }
    }

    // 建構式，用來接收外部程式傳入的項目資料。
    public adapter_random_teacher(List<String> stringList) {
        this.stringList = stringList;
    }

    // RecyclerView會呼叫這個方法，我們必須建立好項目的ViewHolder物件，
    // 然後傳回給RecyclerView。
    @NonNull
    @Override
    public adapter_random_teacher.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 建立一個 view。
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.random_list_item,
                parent, false);

        // 建立這個 view 的 ViewHolder。
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    // RecyclerView會呼叫這個方法，我們必須把項目資料填入ViewHolder物件。
    @Override
    public void onBindViewHolder(@NonNull adapter_random_teacher.ViewHolder holder, int position) {
        // 把資料設定給 ViewHolder。
        holder.txt.setText(stringList.get(position) );
        String bool = stringList.get(position).split(" ")[1];
        if(bool.equals("true")){
            holder.lo.setBackgroundResource(R.drawable.good_random);
        }
        if(bool.equals("false")){
            holder.lo.setBackgroundResource(R.drawable.bad_random);
        }

    }
    // RecyclerView會呼叫這個方法，我們要傳回總共有幾個項目。
    @Override
    public int getItemCount() {
        return stringList.size();
    }
}

