package com.example.attendi;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class viewAdapter_class extends RecyclerView.Adapter<viewAdapter_class.ViewHolder> {

    // 儲存要顯示的資料
    private List<String> stringList;

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
            txt = (TextView) itemView.findViewById(R.id.course);
            myCard.setCardElevation(0);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            //update
            int pos = getAdapterPosition();
            String s = stringList.get(pos).split(":")[1].trim(); // class ID

            Intent class_info = new Intent(v.getContext(), attendi_mode.class);
            class_info.putExtra("id", s);
            v.getContext().startActivity(class_info);
        }
    }
    // 建構式，用來接收外部程式傳入的項目資料。

    public viewAdapter_class(List<String> stringList) {
        this.stringList = stringList;
    }

    // RecyclerView會呼叫這個方法，我們必須建立好項目的ViewHolder物件，
    // 然後傳回給RecyclerView。
    @NonNull
    @Override
    public viewAdapter_class.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 建立一個 view。
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.class_info_item,
                parent, false);

        // 建立這個 view 的 ViewHolder。
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    // RecyclerView會呼叫這個方法，我們必須把項目資料填入ViewHolder物件。
    @Override
    public void onBindViewHolder(@NonNull viewAdapter_class.ViewHolder holder, int position) {
        // 把資料設定給 ViewHolder。

        holder.txt.setText(stringList.get(position));
    }
    // RecyclerView會呼叫這個方法，我們要傳回總共有幾個項目。
    @Override
    public int getItemCount() {
        return stringList.size();
    }
}

