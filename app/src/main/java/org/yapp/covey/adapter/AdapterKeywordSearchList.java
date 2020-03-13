package org.yapp.covey.adapter;

import android.annotation.SuppressLint;
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
        void onItemClick(View v, String address, String placeName);
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterKeywordSearchList.ViewHolder holder, int position) {
        Document addressItem = mSearchList.get(position);
        String roadAddressName = addressItem.getRoadAddressName();
        String addressName = addressItem.getAddressName();
        String placeName = addressItem.getPlaceName();
        if (roadAddressName.length()==0){
            roadAddressName = "도로명 주소가 없습니다";
        }
        if (addressName.length()==0){
            addressName = "지번 주소가 없습니다";
        }
        holder.tvRoadAddressName.setText(roadAddressName+ " (" + placeName + ")");
        holder.tvRoadAddress.setText(addressName);
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

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    if (mListener != null){
                        Document addressItem = mSearchList.get(position);
                        String placeName = " ("+addressItem.getPlaceName()+")";
                        if (addressItem.getRoadAddressName().length() != 0 ){
                            mListener.onItemClick(view, addressItem.getRoadAddressName(), placeName);
                        }else {
                            mListener.onItemClick(view, addressItem.getAddressName(), placeName);
                        }
                    }
                }
            });
        }
    }

    public void setSearchList(KaKaoMapSearchModel searchList){
        this.mSearchList = searchList.getDocuments();
        notifyDataSetChanged();
    }
}
