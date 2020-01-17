package org.yapp.covey.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.yapp.covey.R;
import org.yapp.covey.etc.careerClass;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterCareerList extends RecyclerView.Adapter<AdapterCareerList.ViewHolder> {
    public ArrayList<careerClass> mDataList = new ArrayList<>();

    public AdapterCareerList(){ }

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    private OnItemClickListener mListener = null;
    private OnItemClickListener deleteListener = null;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }
    public void setOndeleteClickListener(OnItemClickListener listener){
        this.deleteListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_career , parent,false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        view.setLayoutParams(layoutParams);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCareerList.ViewHolder holder, int position) {
        careerClass item = mDataList.get(position);

        holder.title.setText(item.getJob());
        holder.time.setText(item.getPeriodNum() + item.getPeriodUnit());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView delete, title, time;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.career_title);
            delete = itemView.findViewById(R.id.career_delete);
            time = itemView.findViewById(R.id.career_time);

            delete.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        if (deleteListener != null){
                            deleteListener.onItemClick(view,position);
                        }
                    }
                }
            });

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

