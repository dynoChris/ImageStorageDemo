package com.example.vadym.galleryapp.database.model;

public class ImageItem {

    public static final String TABLE_NAME = "table_images";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_URI = "uri";

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
