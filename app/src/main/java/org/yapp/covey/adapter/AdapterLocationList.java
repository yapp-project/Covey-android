package org.yapp.covey.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.yapp.covey.R;
import org.yapp.covey.etc.ItemPostVO;

import java.util.ArrayList;

public class AdapterLocationList extends RecyclerView.Adapter<AdapterLocationList.ViewHolder> {
    public ArrayList<ItemPostVO> mDataList = new ArrayList<>();

    public AdapterLocationList() {
    }

    @NonNull
    @Override
    public AdapterLocationList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_home_location,parent,false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) (parent.getWidth()*0.411);
//        layoutParams.height =(int) (parent.getHeight()*0.271);
        view.setLayoutParams(layoutParams);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLocationList.ViewHolder holder, int position) {
        ItemPostVO data = mDataList.get(position);
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
        }
    }

}
