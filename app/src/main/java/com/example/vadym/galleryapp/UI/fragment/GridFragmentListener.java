package com.example.vadym.galleryapp.UI.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vadym.galleryapp.model.ImageItem;
import com.example.vadym.galleryapp.R;
import com.example.vadym.galleryapp.UI.adapter.AdapterRecyclerImages;
import com.example.vadym.galleryapp.UI.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class GridFragmentListener extends Fragment implements MainActivity.OnRemoteFragmentListener {
    private static final String PAGE_NUMBER = "page_number";
    private RecyclerView rv;
    private List<ImageItem> images = new ArrayList<>();
    private AdapterRecyclerImages adapterRecycler;

    public static GridFragmentListener newInstance(int position) {
        GridFragmentListener fragment = new GridFragmentListener();

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        adapterRecycler = new AdapterRecyclerImages(images, context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_grid, container, false);

        rv = (RecyclerView) v.findViewById(R.id.recycler_view);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rv.setAdapter(adapterRecycler);

        return v;
    }

    @Override
    public void addImage(Uri uri) {
        ImageItem img = new ImageItem(uri);
        images.add(img);
        adapterRecycler.notifyDataSetChanged();
    }
}
