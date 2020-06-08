package com.gigabytedevelopersinc.apps.botany.classofchamps18.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.R;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.fragment.StudentAboutFragment;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.fragment.StudentGalleryFragment;
import com.gigabytedevelopersinc.apps.botany.classofchamps18.utils.TinyDB;

import java.util.Objects;

public class StudentFullInfoLayout extends AppCompatActivity {

    private TinyDB tinyDB;
    public static TextView student_name_collapse, student_nickname_collapse;
    private ImageView student_collapse_profile;
    private boolean haveConnectedWifi = false;
    private boolean haveConnectedMobile = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_full_info_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tinyDB = new TinyDB(StudentFullInfoLayout.this);
        setTitle(tinyDB.getString("studentName"));
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(tinyDB.getString("studentName"));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        student_name_collapse = findViewById(R.id.student_name_collapse);
        student_nickname_collapse = findViewById(R.id.student_nickname_collapse);
        student_collapse_profile = findViewById(R.id.student_collapse_profile);

        student_name_collapse.setText(tinyDB.getString("studentName"));
        student_nickname_collapse.setText(tinyDB.getString("studentNickName"));

        if (haveNetworkConnection()){

            RequestOptions requestOptions = RequestOptions
                    .placeholderOf(R.drawable.placeholder)
                    .centerCrop()
                    .centerInside()
                    .format(DecodeFormat.PREFER_ARGB_8888);

            Glide.with(this)
                    .setDefaultRequestOptions(requestOptions)
                    .load(tinyDB.getString("studentImage"))
                    .into(student_collapse_profile);

        }
        tinyDB.remove("studentName");
        tinyDB.remove("studentNickName");
        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        StudentFullInfoAdapter studentFullInfoAdapter = new StudentFullInfoAdapter(getSupportFragmentManager());

        viewPager.setAdapter(studentFullInfoAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private boolean haveNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) Objects.requireNonNull(this).getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo){
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    private class StudentFullInfoAdapter extends FragmentPagerAdapter {
        StudentFullInfoAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {
                case 0:
                    //getSupportActionBar().setTitle(R.string.nav_about);
                    return new StudentAboutFragment();
                case 1:
                    //getSupportActionBar().setTitle(R.string.gallery);
                    return new StudentGalleryFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public String getPageTitle(int pos) {
            switch(pos) {
                case 0:
                    return getString(R.string.nav_about);
                case 1:
                    return getString(R.string.gallery);
                default:
                    return null;
            }
        }
    }
}
