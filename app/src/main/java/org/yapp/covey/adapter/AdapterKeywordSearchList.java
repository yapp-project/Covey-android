package org.yapp.covey.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.yapp.covey.R;
import org.yapp.covey.model.Document;
import org.yapp.covey.model.KaKaoMapSearchModel;

import java.util.ArrayList;

public class AdapterKeywordSearchList extends RecyclerView.Adapter<AdapterKeywordSearchList.ViewHolder> {
    ArrayList<Document> mSearchList = new ArrayList<>();

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    private OnItemClickListener mListener = null;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }


    @NonNull
    @Override
    public AdapterKeywordSearchList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_address,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKeywordSearchList.ViewHolder holder, int position) {
        Document addressItem = mSearchList.get(position);
        holder.tvRoadAddressName.setText(addressItem.getRoadAddressName());
        holder.tvRoadAddress.setText(addressItem.getAddressName());
    }

    @Override
    public int getItemCount() {
        return mSearchList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRoadAddressName , tvRoadAddress;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRoadAddressName = itemView.findViewById(R.id.tv_road_address_name_content);
            tvRoadAddress = itemView.findViewById(R.id.tv_road_address_content);

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

    public void setmSearchList(KaKaoMapSearchModel searchList){
        this.mSearchList = searchList.getDocuments();
        notifyDataSetChanged();
    }
}
