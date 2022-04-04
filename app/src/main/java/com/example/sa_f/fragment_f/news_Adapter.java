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
import android.widget.Toast;

import com.example.sa_f.R;
import com.example.sa_f.database.Infor;
import com.example.sa_f.news_res;

import java.util.ArrayList;

public class news_Adapter extends RecyclerView.Adapter<news_Adapter.myViewHolder> {
    private ArrayList<Infor> dataList;
    private Context context;

    public news_Adapter(Context context, ArrayList<Infor> dataList){
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.news_item, parent, false);
        return new myViewHolder(v);
    }
    @Override
    public void onBindViewHolder(myViewHolder holder, final int position){

        holder.title.setText(dataList.get(position).getFields().getNews_topic());
        holder.date.setText(dataList.get(position).getFields().getNews_create() + "");
        holder.name.setText(dataList.get(position).getFields().getEditor_res() + "");
        if(dataList.get(position).getFields().getNews_content().length() > 40){
            holder.con.setText(dataList.get(position).getFields().getNews_content().substring(0, 40) + "...");
        }
        else{
            holder.con.setText(dataList.get(position).getFields().getNews_content() + "");
        }

        holder.newsbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, dataList.get(position).getId(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.setClass(context, news_res.class);
                Bundle bundle = new Bundle();
                bundle.putInt("newsId", dataList.get(position).getFields().getNews_id());
                bundle.putInt("restaurant_id", dataList.get(position).getFields().getId_res());
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
        private TextView con;
        private Button newsbut;

        public myViewHolder(View itemView){
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.news_title);
            date = (TextView) itemView.findViewById(R.id.news_date);
            name = (TextView) itemView.findViewById(R.id.news_name);
            con = (TextView) itemView.findViewById(R.id.news_context);
            newsbut = (Button) itemView.findViewById(R.id.news_Button);
        }
    }

}
