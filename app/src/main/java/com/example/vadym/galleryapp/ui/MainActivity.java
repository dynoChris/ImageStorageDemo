package com.example.vadym.galleryapp.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.example.vadym.galleryapp.R;
import com.example.vadym.galleryapp.ui.adapter.AdapterRecyclerImages;
import com.example.vadym.galleryapp.ui.fragment.GridFragment;

public class MainActivity extends AppCompatActivity implements AdapterRecyclerImages.OnRecyclerListener {

    public static final int COUNT_GRID = 9; //max 10

    private boolean needReplace = false;
    private int positionReplace;

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MyPagerAdapter pagerAdapter;

    public interface OnRemoteFragmentListener {
        void addImage(String uri);

        void deleteImage(int position);

        void replaceImage(String uri, int position);

        void startSlideshow(int position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);

        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(pagerAdapter);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchImageFromFileSystem();
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
            Uri uriAsObject = data.getData();
            String uri = uriAsObject.toString();
            if (!needReplace) {
                OnRemoteFragmentListener onRemoteFragmentListener = pagerAdapter.getCurrentFragment();
                onRemoteFragmentListener.addImage(uri);
            } else {
                OnRemoteFragmentListener onRemoteFragmentListener = pagerAdapter.getCurrentFragment();
                onRemoteFragmentListener.replaceImage(uri, this.positionReplace);
                needReplace = false;
            }
        }
    }

    @Override
    public void onClickRecyclerItem(int position) {
        OnRemoteFragmentListener onRemoteFragmentListener = pagerAdapter.getCurrentFragment();
        onRemoteFragmentListener.startSlideshow(position);
    }

    @Override
    public void onLongClickRecyclerItem(int position) {
        showActionDialog(position);
    }

    private void showActionDialog(final int position) {
        String choice[] = new String[]{getResources().getString(R.string.replace), getResources().getString(R.string.delete)};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(choice, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: //replace
                        needReplace = true;
                        MainActivity.this.positionReplace = position;
                        fetchImageFromFileSystem();
                        break;
                    case 1: //delete
                        OnRemoteFragmentListener onRemoteFragmentListener = pagerAdapter.getCurrentFragment();
                        onRemoteFragmentListener.deleteImage(position);
                        break;
                }
            }
        });
        builder.show();
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        private GridFragment currentFragment;

        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public GridFragment getCurrentFragment() {
            return this.currentFragment;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            if (getCurrentFragment() != object) {
                currentFragment = ((GridFragment) object);
            }
            super.setPrimaryItem(container, position, object);
        }

        @Override
        public Fragment getItem(int position) {
            return GridFragment.newInstance(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getString(R.string.title_tab_layout1);
                case 1:
                    return getResources().getString(R.string.title_tab_layout2);
                case 2:
                    return getResources().getString(R.string.title_tab_layout3);
                case 3:
                    return getResources().getString(R.string.title_tab_layout4);
                case 4:
                    return getResources().getString(R.string.title_tab_layout5);
                case 5:
                    return getResources().getString(R.string.title_tab_layout6);
                case 6:
                    return getResources().getString(R.string.title_tab_layout7);
                case 7:
                    return getResources().getString(R.string.title_tab_layout8);
                case 8:
                    return getResources().getString(R.string.title_tab_layout9);
                case 9:
                    return getResources().getString(R.string.title_tab_layout10);
            }
            return "";
        }

        @Override
        public int getCount() {
            return COUNT_GRID;
        }
    }
}