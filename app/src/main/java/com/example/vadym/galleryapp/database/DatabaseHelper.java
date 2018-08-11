package com.example.vadym.galleryapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vadym.galleryapp.UI.MainActivity;
import com.example.vadym.galleryapp.database.model.ImageItem;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "db1";
    public static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (int i = 0; i < MainActivity.COUNT_GRID; i++) {
            String tableName = ImageItem.TABLE_NAME + i;
            db.execSQL("CREATE TABLE " + (ImageItem.TABLE_NAME + i) + "("
                    + ImageItem.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ImageItem.COLUMN_URI + " TEXT"
                    + ")");
        }
    }

    public ImageItem getItem(int numberTable, long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String tableName = ImageItem.TABLE_NAME + numberTable;
        Cursor cursor = db.query(tableName, new String[]{ImageItem.COLUMN_ID, ImageItem.COLUMN_URI}, ImageItem.COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        ImageItem img = new ImageItem(
                cursor.getInt(cursor.getColumnIndex(ImageItem.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(ImageItem.COLUMN_URI))
        );
        cursor.close();
        db.close();
        return img;
    }

    public List<ImageItem> getAllItems(int numberTable) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<ImageItem> images = new ArrayList<>();
        String tableName = ImageItem.TABLE_NAME + numberTable;
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName +
                " ORDER BY " + ImageItem.COLUMN_ID + " DESC", null);
        if (cursor.moveToFirst()) {
            do {
                ImageItem img = new ImageItem(
                        cursor.getInt(cursor.getColumnIndex(ImageItem.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(ImageItem.COLUMN_URI))
                );
                images.add(img);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return images;
    }

    public long insertItem(int numberTable, String uri) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ImageItem.COLUMN_URI, uri);
        String tableName = ImageItem.TABLE_NAME + numberTable;
        long id = db.insert(tableName, null, values);

        db.close();
        return id;
    }

    public void deleteItem(int numberTable, long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String tableName = ImageItem.TABLE_NAME + numberTable;
        db.delete(tableName, ImageItem.COLUMN_ID + "=?", new String[]{String.valueOf(id)});

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
