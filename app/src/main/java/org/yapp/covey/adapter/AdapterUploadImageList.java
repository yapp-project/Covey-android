package org.yapp.covey.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.yapp.covey.R;

import java.util.ArrayList;

public class AdapterUploadImageList extends RecyclerView.Adapter<AdapterUploadImageList.ViewHolder> {
    private ArrayList<Uri> mUriList = new ArrayList<>();

    private ItemClick itemClick;

    public interface ItemClick{
        void onClick(View view, int position);
    }
    public void setItemClick(ItemClick itemClick){
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public AdapterUploadImageList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_upload_image,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUploadImageList.ViewHolder holder, int position) {
        Uri itemData = mUriList.get(position);
        Glide.with(holder.itemView).load(itemData)
                .error(R.drawable.no_image_img)
                .into(holder.imageAdded);
        holder.deleteBtn.setOnClickListener(view -> {
            deleteImageUri(position);
            if (itemClick!=null){
                itemClick.onClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUriList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageAdded;
        ImageButton deleteBtn;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageAdded = itemView.findViewById(R.id.imageView_upload);
            deleteBtn = itemView.findViewById(R.id.imageButton_delete);
        }
    }

    public void addUriList(ArrayList<Uri> uriList){
        mUriList.addAll(uriList);
        notifyDataSetChanged();
    }

    private void deleteImageUri(int position){
        mUriList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, getItemCount());
    }

    public ArrayList<Uri> getUriList(){
        return mUriList;
    }
}
