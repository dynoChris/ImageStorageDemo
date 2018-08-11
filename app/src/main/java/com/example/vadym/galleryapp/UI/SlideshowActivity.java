package com.example.vadym.galleryapp.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.vadym.galleryapp.R;

public class SlideshowActivity extends AppCompatActivity {

    public static final String TAG_POSITION_ITEM = "position_item";
    public static final String TAG_NUMBER_TABLE = "number_table";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        Intent intent = getIntent();
        int position = intent.getIntExtra(TAG_POSITION_ITEM, -1);
        int numberTable = intent.getIntExtra(TAG_NUMBER_TABLE, -1);

        Toast.makeText(this, "" + position + ", " + numberTable, Toast.LENGTH_SHORT).show();
    }
}
