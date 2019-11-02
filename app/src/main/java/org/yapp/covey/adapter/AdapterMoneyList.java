package org.yapp.covey.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.yapp.covey.R;
import org.yapp.covey.etc.CalculateDate;
import org.yapp.covey.etc.ItemPostVO;

import java.util.ArrayList;

public class AdapterMoneyList extends RecyclerView.Adapter<AdapterMoneyList.ViewHolder> {
    public ArrayList<ItemPostVO> mDataList = new ArrayList<>();

    public AdapterMoneyList() { }

    @NonNull
    @Override
    public AdapterMoneyList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_home_money,parent,false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height =(int) (parent.getHeight()*0.144);
        view.setLayoutParams(layoutParams);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMoneyList.ViewHolder holder, int position) {
        ItemPostVO data = mDataList.get(position);
        String dDay = "D-" + new CalculateDate().calDateBetween(data.getDueDate());

        holder.tvDDay.setText(dDay + " | ");
        holder.tvContent.setText(data.getDescription());
        holder.tvMoney.setText(data.getPay().toString()+"원");
        holder.tvTitle.setText(data.getTitle());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle,tvContent, tvMoney, tvDDay;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvTitle = itemView.findViewById(R.id.tv_money_title);
            tvDDay = itemView.findViewById(R.id.tv_d_day);
            tvMoney = itemView.findViewById(R.id.tv_payment);
        }
    }

}
