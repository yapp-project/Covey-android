package org.yapp.covey.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.yapp.covey.R;

import java.util.ArrayList;

public class AdapterUploadImageList extends RecyclerView.Adapter<AdapterUploadImageList.ViewHolder> {
    ArrayList<Uri> mUriList = new ArrayList<>();

    @NonNull
    @Override
    public AdapterUploadImageList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_upload_image,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUploadImageList.ViewHolder holder, int position) {
        Glide.with(holder.imageView).load(mUriList.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUriList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void addUriList(ArrayList<Uri> uriList){
        this.mUriList = uriList;
        notifyDataSetChanged();
    }

    public void deleteImageUri(int position){
        mUriList.remove(position);
    }

    public ArrayList<String> getUriList(){
        ArrayList<String> stringList = new ArrayList<>();
        for (int i = 0 ; i<mUriList.size(); i++){
            stringList.set(i, String.valueOf(mUriList.get(i)));
        }
        return stringList;
    }
}
