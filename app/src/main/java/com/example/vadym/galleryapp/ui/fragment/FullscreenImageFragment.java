package com.example.vadym.galleryapp.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.vadym.galleryapp.R;

public class FullscreenImageFragment extends Fragment {

    private String uriAsString;

    public static FullscreenImageFragment newInstance(String uriAsString) {
        FullscreenImageFragment fragment = new FullscreenImageFragment();
        Bundle args = new Bundle();
        args.putString("uri", uriAsString);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.uriAsString = getArguments().getString("uri");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fullscreen_image, container, false);

        ImageView imageView = (ImageView) v.findViewById(R.id.image_view_fullscreen);

        Uri uri = Uri.parse(uriAsString);
        Glide.with(getActivity()).load(uri).into(imageView);

        return v;
    }
}
