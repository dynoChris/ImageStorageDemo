package com.example.vadym.galleryapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.vadym.galleryapp.R;
import com.example.vadym.galleryapp.UI.fragment.FullscreenImageFragment;
import com.example.vadym.galleryapp.database.DatabaseHelper;
import com.example.vadym.galleryapp.database.model.ImageItem;

import java.util.List;

public class SlideshowActivity extends AppCompatActivity {

    public static final String TAG_POSITION_ITEM = "position_item";
    public static final String TAG_NUMBER_TABLE = "number_table";

    private int index;
    private int numberTable;
    private List<ImageItem> images;

    private MyPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        db = new DatabaseHelper(this);

        index = getIntent().getIntExtra(TAG_POSITION_ITEM, 0);
        numberTable = getIntent().getIntExtra(TAG_NUMBER_TABLE, 0);

        images = db.getAllItems(numberTable);

        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(index);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            String uri = images.get(position).getUri();
            return FullscreenImageFragment.newInstance(uri);
        }

        @Override
        public int getCount() {
            return images.size();
        }
    }
}
