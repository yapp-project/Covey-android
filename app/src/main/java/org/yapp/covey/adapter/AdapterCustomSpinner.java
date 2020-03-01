package org.yapp.covey.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.yapp.covey.R;

import java.util.List;

public class AdapterCustomSpinner extends BaseAdapter {
    Context context;
    List<String> dataList;
    LayoutInflater inflater;
    String title, data;

    public AdapterCustomSpinner(Context context, List<String> data, String title){
        this.context = context;
        this.dataList = data;
        this.title = title;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.item_spinner,parent,false);
        String text = dataList.get(position);
        ((TextView)view.findViewById(R.id.tv_spinner_hint)).setText(text);
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_spinner_dropdown, parent, false);
        if (position == 0) {
            convertView.setBackgroundResource(R.drawable.rounded_top_rectangle_outline_8dp);
        } else if (position == dataList.size()-1) {
            convertView.setBackgroundResource(R.drawable.rounded_bottom_rectangle_outline_8dp);
        }
        data = dataList.get(position);
        TextView tvData = convertView.findViewById(R.id.tv_spinner_item);
        tvData.setText(data);
        return convertView;
    }
}
