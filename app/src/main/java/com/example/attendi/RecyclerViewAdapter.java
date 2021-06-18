package com.example.attendi;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    // 儲存要顯示的資料
    private List<String> stringList;
    CardView mcardview;

    // ViewHolder 是把項目中所有的 View 物件包起來。
    // 它在 onCreateViewHolder() 中使用。
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.day);
            mcardview = itemView.findViewById(R.id.cardview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            Toast.makeText(v.getContext(), stringList.get(getAdapterPosition()), Toast.LENGTH_LONG)
//                    .show();
            //update
            /*如何將雲端課程資料庫匯入*/
            Intent class_info = new Intent(v.getContext(), class_info.class);
            v.getContext().startActivity(class_info);
        }
    }
    // 建構式，用來接收外部程式傳入的項目資料。

    public RecyclerViewAdapter(List<String> stringList) {
        this.stringList = stringList;
    }

    // RecyclerView會呼叫這個方法，我們必須建立好項目的ViewHolder物件，
    // 然後傳回給RecyclerView。
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 建立一個 view。
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.teacher_recyclerview_item,
                parent, false);

        // 建立這個 view 的 ViewHolder。
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    // RecyclerView會呼叫這個方法，我們必須把項目資料填入ViewHolder物件。
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        // 把資料設定給 ViewHolder。
        holder.txt.setText(stringList.get(position));
        if(position%3 == 0){
            String color = "#a297c8";
            mcardview.setCardBackgroundColor(Integer.parseInt(color));
        }
        else if (position%3 == 1){
            mcardview.setCardBackgroundColor(R.color.light_green);
        }
        else{
            mcardview.setCardBackgroundColor(R.color.light_purple);
        }

    }
    // RecyclerView會呼叫這個方法，我們要傳回總共有幾個項目。
    @Override
    public int getItemCount() {
        return stringList.size();
    }
}

