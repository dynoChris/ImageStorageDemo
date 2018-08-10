package com.example.vadym.galleryapp.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.example.vadym.galleryapp.R;
import com.example.vadym.galleryapp.UI.fragment.GridFragmentListener;

public class MainActivity extends AppCompatActivity {

    private final int COUNT_GRID = 9;

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MyPagerAdapter pagerAdapter;

    public interface OnRemoteFragmentListener {
        void addImage(Uri uri);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(pagerAdapter);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchImageFromFileSystem();
//                OnRemoteFragmentListener onRemoteFragmentListener = (OnRemoteFragmentListener) pagerAdapter.getCurrentFragment();
//                onRemoteFragmentListener.addImage();
            }
        });
    }

    private void fetchImageFromFileSystem() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            OnRemoteFragmentListener onRemoteFragmentListener = (OnRemoteFragmentListener) pagerAdapter.getCurrentFragment();
            onRemoteFragmentListener.addImage(uri);
        }
    }

//    private Bitmap getPath(Uri uri) {
//        String[] projection = { MediaStore.Images.Media.DATA };
//        Cursor cursor = managedQuery(uri, projection, null, null, null);
//        int column_index = cursor
//                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        String filePath = cursor.getString(column_index);
//        cursor.close();
//        // Convert file path into bitmap image using below line.
//        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
//
//        return bitmap;
//    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        private GridFragmentListener currentFragment;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public GridFragmentListener getCurrentFragment() {
            return this.currentFragment;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            if (getCurrentFragment() != object) {
                currentFragment = ((GridFragmentListener) object);
            }
            super.setPrimaryItem(container, position, object);
        }

        @Override
        public Fragment getItem(int position) {
            return GridFragmentListener.newInstance(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "#" + position;
        }

        @Override
        public int getCount() {
            return COUNT_GRID;
        }
    }
}