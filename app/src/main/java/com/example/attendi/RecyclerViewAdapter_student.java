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

public class RecyclerViewAdapter_student extends RecyclerView.Adapter<RecyclerViewAdapter_student.ViewHolder> {
    private static final String TAG = "DocSnippets";
    // 儲存要顯示的資料
    private List<String> stringList;
    //private List<Map<String, Object>> stringList;


    // ViewHolder 是把項目中所有的 View 物件包起來。
    // 它在 onCreateViewHolder() 中使用。
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public CardView myCard;
        public LinearLayout lo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myCard = (CardView) itemView.findViewById(R.id.myCard);
            lo = (LinearLayout) itemView.findViewById(R.id.lo);
            myCard.setCardElevation(0);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            Toast.makeText(v.getContext(), stringList.get(getAdapterPosition()), Toast.LENGTH_LONG)
//                    .show();
            //update
            /*如何將雲端課程資料庫匯入*/
            int pos = getAdapterPosition();
            Log.d(TAG, pos+"");
            Intent class_info = new Intent(v.getContext(), class_info_student.class);
            class_info.putExtra("pos", pos);
            class_info.putExtra("name",stringList.get(0));
            Log.d("now3",stringList.get(0)+"ddd");
            v.getContext().startActivity(class_info);
        }
    }
    // 建構式，用來接收外部程式傳入的項目資料。

    public RecyclerViewAdapter_student(List<String> stringList) {
        this.stringList = stringList;

    }

    // RecyclerView會呼叫這個方法，我們必須建立好項目的ViewHolder物件，
    // 然後傳回給RecyclerView。
    @NonNull
    @Override
    public RecyclerViewAdapter_student.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
    public void onBindViewHolder(@NonNull RecyclerViewAdapter_student.ViewHolder holder, int position) {
        // 把資料設定給 ViewHolder。
        int [] drawable = {R.drawable.schedule_monday, R.drawable.schedule_tuesday, R.drawable.schedule_wednesday,
                R.drawable.schedule_thursday, R.drawable.schedule_friday, R.drawable.schedule_saturday, R.drawable.schedule_sunday};
        holder.lo.setBackgroundResource(drawable[position%7]);
        //holder.mTxt.setText(stringList.get(position).get("id").toString() + '\n' + stringList.get(position).get("value").toString());
    }
    // RecyclerView會呼叫這個方法，我們要傳回總共有幾個項目。
    @Override
    public int getItemCount() {
        return stringList.size();
    }
}

