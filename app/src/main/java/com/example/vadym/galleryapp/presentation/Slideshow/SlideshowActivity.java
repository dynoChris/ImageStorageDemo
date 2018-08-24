package com.example.vadym.galleryapp.presentation.Slideshow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.vadym.galleryapp.R;
import com.example.vadym.galleryapp.data.db.DBHelper;
import com.example.vadym.galleryapp.data.model.ImageItem;

import java.util.List;

public class SlideshowActivity extends AppCompatActivity {

    public static final String TAG_POSITION_ITEM = "position_item";
    public static final String TAG_NUMBER_TABLE = "number_table";

    private int index;
    private int numberTable;
    private List<ImageItem> images;

    private MyPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_slideshow);

        db = new DBHelper(this);

        index = getIntent().getIntExtra(TAG_POSITION_ITEM, 0);
        numberTable = getIntent().getIntExtra(TAG_NUMBER_TABLE, 0);

        images = db.getAllItems(numberTable);

        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(index);
    }

    private void hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        MyPagerAdapter(FragmentManager fm) {
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
