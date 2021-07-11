package com.example.attendi;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter_normal_teacher extends RecyclerView.Adapter<adapter_normal_teacher.ViewHolder> {

    private List<String> mListString;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CardView myCard;
        public LinearLayout lo;
        public TextView txt;

        public ViewHolder (View itemView){
            super(itemView);

            myCard = (CardView) itemView.findViewById(R.id.myCard);
            lo = (LinearLayout) itemView.findViewById(R.id.lo);
            txt = (TextView) itemView.findViewById(R.id.txt);
            myCard.setCardElevation(0);
            myCard.canScrollHorizontally(0);
            myCard.setVerticalScrollBarEnabled(false);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
           /* Intent roll_call = new Intent (view.getContext(), Student_Roll_Call.class);
            view.getContext().startActivity(roll_call);*/

        }
    }

    public adapter_normal_teacher(List<String> listString) {
        mListString = listString;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_normal, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        int background [] = {R.drawable.method3_square_red, R.drawable.method3_square_green};
        holder.lo.setBackgroundResource(background[0]);
        holder.txt.setText(mListString.get(i).split(" ")[0]);
        //美麗的錯誤就從這開始
        String bool = mListString.get(i).split(" ")[1];

        if(bool.equals("true")){
            holder.lo.setBackgroundResource(background[1]);
        }
        if(bool.equals("false")){
            holder.lo.setBackgroundResource(background[0]);
        }
    }

    @Override
    public int getItemCount() {
        return mListString.size();
    }
}