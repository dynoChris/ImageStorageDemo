package com.example.vadym.galleryapp.UI.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vadym.galleryapp.model.ImageItem;
import com.example.vadym.galleryapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRecyclerImages extends RecyclerView.Adapter<AdapterRecyclerImages.MyViewHolder> {

    private List<ImageItem> images;
    private Context context;

    public AdapterRecyclerImages(List<ImageItem> images, Context context) {
        this.images = images;
        this.context = context;
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
        Uri uri = images.get(position).getUri();
        Picasso.get().load(uri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.image_view_thumbnail);
        }
    }
}