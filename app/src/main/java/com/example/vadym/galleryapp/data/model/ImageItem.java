package com.example.vadym.galleryapp.data.model;

public class ImageItem {

    private int id;
    private String uri;

    public ImageItem(int id, String uri) {
        this.id = id;
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
