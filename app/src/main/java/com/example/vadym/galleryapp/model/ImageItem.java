package com.example.vadym.galleryapp.model;

import android.net.Uri;

public class ImageItem {
    private Uri uri;

    public ImageItem(Uri uri) {
        this.uri = uri;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
