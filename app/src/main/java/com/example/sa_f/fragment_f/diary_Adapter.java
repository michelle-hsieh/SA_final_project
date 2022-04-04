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

import com.example.sa_f.Diary_item;
import com.example.sa_f.R;
import com.example.sa_f.database.Infor;

import java.util.ArrayList;

public class diary_Adapter extends RecyclerView.Adapter<diary_Adapter.myViewHolder> {
    private ArrayList<Infor> dataList;
    private Context context;

    public diary_Adapter(Context context, ArrayList<Infor> dataList){
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.diary_item, parent, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, final int position){

        holder.title.setText(dataList.get(position).getFields().getDiary_topic());
        holder.date.setText(dataList.get(position).getFields().getdiaryTime() + "");
        holder.name.setText(dataList.get(position).getFields().getCus_name() + "");
        holder.diary_btu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, Diary_item.class);
                Bundle bundle = new Bundle();
                bundle.putInt("diary_id", dataList.get(position).getFields().getDiary_id());
                bundle.putInt("res_id", dataList.get(position).getFields().getDiary_resId()[0]);
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
        private TextView date;
        private TextView name;
        private Button diary_btu;

        public myViewHolder(View itemView){
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.diary_title);
            date = (TextView) itemView.findViewById(R.id.diary_date);
            name = (TextView) itemView.findViewById(R.id.diary_name);
            diary_btu = (Button) itemView.findViewById(R.id.diary_Button);
        }
    }

}
