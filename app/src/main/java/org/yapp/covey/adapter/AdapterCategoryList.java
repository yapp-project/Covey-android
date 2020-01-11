package org.yapp.covey.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.yapp.covey.R;

import java.util.ArrayList;

public class AdapterCategoryList extends RecyclerView.Adapter<AdapterCategoryList.ViewHolder> {
    private ArrayList<String> dataCategory;
    private Context context;

    public AdapterCategoryList(Context context, ArrayList<String> list){
        this.context = context;
        this.dataCategory = list;
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    private OnItemClickListener mListener = null;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_recycler_home_category , parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategoryList.ViewHolder holder, int position) {
        String item = dataCategory.get(position);

        holder.tv.setText(item);
    }

    @Override
    public int getItemCount() {
        return dataCategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_category_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        if (mListener != null){
                            mListener.onItemClick(view,position);
                        }
                    }
                }
            });
        }
    }
}

