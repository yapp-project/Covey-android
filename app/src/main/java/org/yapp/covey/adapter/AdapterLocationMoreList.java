package org.yapp.covey.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.yapp.covey.R;
import org.yapp.covey.model.ItemDataModel;

import java.util.ArrayList;

public class AdapterLocationMoreList extends RecyclerView.Adapter<AdapterLocationMoreList.ViewHolder>{
    public ArrayList<ItemDataModel> mDataList = new ArrayList<>();


    public AdapterLocationMoreList() {
    }

    public void setOnItemClickListener(AdapterLocationList.OnItemClickListener onItemClickListener) {
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
    public AdapterLocationMoreList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_home_location,parent,false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) (parent.getWidth()*0.48);
        layoutParams.height = (int)(parent.getHeight()*0.377);
        view.setLayoutParams(layoutParams);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLocationMoreList.ViewHolder holder, int position) {
        ItemDataModel data = mDataList.get(position);
        String time = data.getWorkingTime();
        String location = data.getAddress1()+" "+data.getAddress2()+"...";

        holder.tvTime.setText(time);
        holder.tvLocation.setText(location);
        holder.tvMoney.setText(data.getPay().toString()+"원");
        holder.tvName.setText(data.getTitle());
        holder.tvCategory.setText(data.getCategory());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvCategory, tvName, tvMoney, tvLocation, tvTime;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tv_category);
            tvName = itemView.findViewById(R.id.tv_name);
            tvLocation = itemView.findViewById(R.id.tv_location);
            tvMoney = itemView.findViewById(R.id.tv_money);
            tvTime = itemView.findViewById(R.id.tv_time);

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
