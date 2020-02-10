package org.yapp.covey.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.yapp.covey.R;
import org.yapp.covey.util.Config;

import java.util.ArrayList;

public class AdapterApplyImage extends RecyclerView.Adapter<AdapterApplyImage.ViewHolder> {
    public ArrayList<String> imageUriList = new ArrayList<>();

    @NonNull
    @Override
    public AdapterApplyImage.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_apply_image,parent,false);

//        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//        layoutParams.width = (int) (parent.getWidth()*0.61);
//        view.setLayoutParams(layoutParams);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterApplyImage.ViewHolder holder, int position) {
        String imageUri = imageUriList.get(position);

        Glide.with(holder.image)
                .load(Config.serverUrl+imageUri)
                .error(R.drawable.no_image_img)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return imageUriList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_item);
        }
    }
}
