package com.example.vadym.galleryapp.presentation.Main;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.vadym.galleryapp.R;
import com.example.vadym.galleryapp.data.model.ImageItem;

import java.util.List;

public class AdapterRecyclerImages extends RecyclerView.Adapter<AdapterRecyclerImages.MyViewHolder> {

    private List<ImageItem> images;
    private Context context;
    private GridFragment fragment;

    public interface OnClickRecyclerListener {
        void onClickRecyclerItem(int position);
    }

    public interface OnLongClickRecyclerListener {
        void onLongClickRecyclerItem(int position);
    }

    AdapterRecyclerImages(List<ImageItem> images, Context context, GridFragment fragment) {
        this.images = images;
        this.context = context;
        this.fragment = fragment;
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
//        String path = Converter.uriToPath(context, uri);
        Glide.with(context).load(uri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnClickRecyclerListener listener = fragment;
                    listener.onClickRecyclerItem(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    OnLongClickRecyclerListener listener = (OnLongClickRecyclerListener) context;
                    listener.onLongClickRecyclerItem(getAdapterPosition());
                    return true;
                }
            });

            imageView = itemView.findViewById(R.id.image_view_thumbnail);
        }
    }
}