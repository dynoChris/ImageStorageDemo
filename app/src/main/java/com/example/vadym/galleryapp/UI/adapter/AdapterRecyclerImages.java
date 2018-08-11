package com.example.vadym.galleryapp.UI.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.vadym.galleryapp.R;
import com.example.vadym.galleryapp.UI.SlideshowActivity;
import com.example.vadym.galleryapp.database.model.ImageItem;

import java.util.List;

public class AdapterRecyclerImages extends RecyclerView.Adapter<AdapterRecyclerImages.MyViewHolder> {

    private List<ImageItem> images;
    private Context context;
    private int numberTable;

//    public interface OnRecyclerListener {
//        void onClickRecyclerItem(int position);
//    }

    public AdapterRecyclerImages(List<ImageItem> images, Context context, int numberTable) {
        this.images = images;
        this.context = context;
        this.numberTable = numberTable;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_items, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String uriAsString = images.get(position).getUri();
        Uri uri = Uri.parse(uriAsString);
        Glide.with(context).load(uri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    OnRecyclerListener listener = (OnRecyclerListener) context;
//                    listener.onClickRecyclerItem(getAdapterPosition());

//                    Intent intent = new Intent(context, SlideshowActivity.class);
//                    Bundle extras = new Bundle();
//                    extras.putInt(SlideshowActivity.TAG_POSITION_ITEM, getAdapterPosition());
//                    extras.putInt(SlideshowActivity.TAG_NUMBER_TABLE, numberTable);
//                    intent.putExtras(extras);
//                    context.startActivity(intent);
                }
            });

            imageView = (ImageView) itemView.findViewById(R.id.image_view_thumbnail);
        }
    }
}