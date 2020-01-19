package org.yapp.covey.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.yapp.covey.R;
import org.yapp.covey.etc.careerClass;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterProfileCareer extends RecyclerView.Adapter<AdapterProfileCareer.ViewHolder> {
    public ArrayList<careerClass> mDataList = new ArrayList<>();

    public AdapterProfileCareer(){ }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_profile_career , parent,false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        view.setLayoutParams(layoutParams);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProfileCareer.ViewHolder holder, int position) {
        careerClass item = mDataList.get(position);
        Log.d("asd",item.getJob());
        holder.title.setText(item.getJob());
        holder.time.setText(item.getPeriodNum() + item.getPeriodUnit());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, time;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.profile_career_title);
            time = itemView.findViewById(R.id.profile_career_time);
        }
    }

}

