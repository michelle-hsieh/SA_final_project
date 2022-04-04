package com.example.sa_f.profile_related;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.sa_f.R;

import java.util.ArrayList;

public class ImageTextAdapter extends BaseAdapter {
    private ArrayList<ImageText> data;
    private Context context;

    public ImageTextAdapter(Context context, ArrayList<ImageText> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R.layout.profile_row_layout, null);
        }

        ImageView icon = view.findViewById(R.id.icon);
        TextView text = view.findViewById(R.id.text);

        final ImageText item = data.get(position);
        text.setText(item.name);
        icon.setImageDrawable(item.icon);
        view.setClickable(false);

        return view;
    }
}