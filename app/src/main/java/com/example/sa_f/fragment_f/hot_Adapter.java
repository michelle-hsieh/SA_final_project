package com.example.sa_f.fragment_f;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sa_f.R;
import com.example.sa_f.database.Infor;
import com.example.sa_f.res_detail;

import java.util.ArrayList;

    public class hot_Adapter extends RecyclerView.Adapter<hot_Adapter.myViewHolder> {
    private ArrayList<Infor> dataList;
    private Context context;

    public hot_Adapter(Context context, ArrayList<Infor> dataList){
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.card_item, parent, false);
        return new myViewHolder(v);
    }
    @Override
    public void onBindViewHolder(myViewHolder holder, final int position){

        holder.title.setText(dataList.get(position).getFields().getRes_name());
        holder.fav.setText(dataList.get(position).getFields().getFav_count() + "人收藏");
        holder.history.setText(dataList.get(position).getFields().getRes_click() + " 瀏覽");
        holder.score.setText(dataList.get(position).getFields().getRes_score() + "");
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, res_detail.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", dataList.get(position).getFields().getRes_id());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });



    }
    @Override
    public int getItemCount(){
        return dataList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView fav;
        private TextView history;
        private TextView score;
        private Button detail;

        public myViewHolder(View itemView){
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.place_text);
            fav = (TextView) itemView.findViewById(R.id.place_popular);
            history = (TextView) itemView.findViewById(R.id.place_his);
            score = (TextView) itemView.findViewById(R.id.star);
            detail = (Button) itemView.findViewById(R.id.hot_Button);

        }
    }

}
