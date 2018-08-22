package com.example.vadym.galleryapp.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vadym.galleryapp.presentation.Main.MainActivity;
import com.example.vadym.galleryapp.data.model.ImageItem;

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
            String tableName = DBTable.TABLE_NAME + i;
            db.execSQL("CREATE TABLE " + (DBTable.TABLE_NAME + i) + "("
                    + DBTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + DBTable.COLUMN_URI + " TEXT"
                    + ")");
        }
    }

    public ImageItem getItem(int numberTable, long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String tableName = DBTable.TABLE_NAME + numberTable;
        Cursor cursor = db.query(tableName, new String[]{DBTable.COLUMN_ID, DBTable.COLUMN_URI}, DBTable.COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        ImageItem img = new ImageItem(
                cursor.getInt(cursor.getColumnIndex(DBTable.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(DBTable.COLUMN_URI))
        );
        cursor.close();
        db.close();
        return img;
    }

    public List<ImageItem> getAllItems(int numberTable) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<ImageItem> images = new ArrayList<>();
        String tableName = DBTable.TABLE_NAME + numberTable;
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName +
                " ORDER BY " + DBTable.COLUMN_ID + " DESC", null);
        if (cursor.moveToFirst()) {
            do {
                ImageItem img = new ImageItem(
                        cursor.getInt(cursor.getColumnIndex(DBTable.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(DBTable.COLUMN_URI))
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

        values.put(DBTable.COLUMN_URI, uri);
        String tableName = DBTable.TABLE_NAME + numberTable;
        long id = db.insert(tableName, null, values);

        db.close();
        return id;
    }

    public void deleteItem(int numberTable, long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String tableName = DBTable.TABLE_NAME + numberTable;
        db.delete(tableName, DBTable.COLUMN_ID + "=?", new String[]{String.valueOf(id)});

        db.close();
    }

    public void updateItem(int numberTable, long id, String uri) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBTable.COLUMN_URI, uri);

        String tableName = DBTable.TABLE_NAME + numberTable;
        db.update(tableName, values, DBTable.COLUMN_ID + "=?", new String[]{String.valueOf(id)});

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
