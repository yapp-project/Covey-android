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
    private ArrayList<String> dataCategory;
    private Context context;
    public ArrayList<careerClass> mDataList;

    public AdapterCareerList(){
    }

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
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_recycler_career , parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCareerList.ViewHolder holder, int position) {
        String item = dataCategory.get(position);

        holder.title.setText(item);
    }

    @Override
    public int getItemCount() {
        return dataCategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView delete, title;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.career_title);
            delete = itemView.findViewById(R.id.career_delete);

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

