package com.example.sa_f.profile_related;

//import android.content.Context;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sa_f.MyDiary;
import com.example.sa_f.R;
import com.example.sa_f.database.MyAPIService;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DiaryItemAdapter extends RecyclerView.Adapter<DiaryItemAdapter.ViewHolder> {
    private ArrayList<DiaryItems> diaryList;
    private Context context;
    private OnItemClickListener itemListener;
    MyAPIService myAPIService;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView itemImage;
        public TextView itemName;
        public TextView itemContent;
        public ImageView itemTrashcan;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            itemName = itemView.findViewById(R.id.res_name);
            itemImage = itemView.findViewById(R.id.res_pic);
            itemContent = itemView.findViewById(R.id.res_content);
            itemTrashcan = itemView.findViewById(R.id.trashcan);

            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public DiaryItemAdapter(Context context, ArrayList<DiaryItems> data) {
        this.context = context;
        diaryList = data;
    }

    @Override
    //layout 和 holder 之間的連結
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_items, parent, false);
        ViewHolder holder = new ViewHolder(v, itemListener);
        holder.itemName = v.findViewById(R.id.res_name);
        holder.itemImage = v.findViewById(R.id.res_pic);
        holder.itemContent = v.findViewById(R.id.res_content);
        holder.itemTrashcan = v.findViewById(R.id.trashcan);
        return holder;
    }

    @Override
    //holder 和 layout 之間的連結
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        DiaryItems currentItem = diaryList.get(i);
        viewHolder.itemName.setText(currentItem.getDiaryTopic());

        if (!currentItem.getDiaryPicUrl().equals("error")){
            Glide.with(context)
                 .load(currentItem.getDiaryPicUrl())
                 .into(viewHolder.itemImage);
        } else {
            viewHolder.itemContent.setText(currentItem.getDiaryContent());
        }

        viewHolder.itemTrashcan.setVisibility(currentItem.getIsEdit() ? View.VISIBLE : View.GONE);

        //刪除 動畫
        viewHolder.itemTrashcan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //myAPIService.deleteInfor( i + "");
                diaryList.remove(viewHolder.getAdapterPosition());
                notifyItemRemoved(viewHolder.getAdapterPosition());
                notifyItemRangeChanged(viewHolder.getAdapterPosition(), diaryList.size());

                Snackbar.make(v, "已刪除該美食日記", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return diaryList.size();
    }

    public void removeData(int position) {
        diaryList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, diaryList.size());
    }

    public void changeState(boolean is_edit) {
        for (int i = 0; i < diaryList.size(); ++i) {
            diaryList.get(i).setIsEdit(is_edit);
        }
        notifyDataSetChanged();
    }
}