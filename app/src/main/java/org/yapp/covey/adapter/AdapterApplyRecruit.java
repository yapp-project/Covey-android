package org.yapp.covey.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.yapp.covey.R;
import org.yapp.covey.helper.CalculateDate;
import org.yapp.covey.model.ItemDataModel;
import java.util.ArrayList;

public class AdapterApplyRecruit extends RecyclerView.Adapter<AdapterApplyRecruit.ViewHolder> {
    public ArrayList<ItemDataModel> mDataList = new ArrayList<>();

    public AdapterApplyRecruit(ArrayList<ItemDataModel> mDataList){
        this.mDataList = mDataList;
    }

    public AdapterApplyRecruit(){}

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    private OnItemClickListener mListener = null;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    @NonNull
    @Override
    public AdapterApplyRecruit.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_apply_recruit,parent,false);
        return new ViewHolder(itemView);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull AdapterApplyRecruit.ViewHolder holder, int position) {
        ItemDataModel mData = mDataList.get(position);

        long dDay = new CalculateDate().calDateBetween(mData.getDueDate());
        if (dDay<4){
            holder.tvDDay.setTextColor(R.color.tomato);
        }

        holder.tvTitle.setText(mData.getTitle());
        holder.tvDDay.setText("D - "+dDay);
        holder.tvDate.setText(mData.getStartDate()+" ~ "+mData.getEndDate());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvDDay, tvDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvDDay = itemView.findViewById(R.id.tv_d_day);

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
