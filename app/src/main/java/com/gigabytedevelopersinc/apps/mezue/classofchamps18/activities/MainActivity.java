package com.gigabytedevelopersinc.apps.mezue.classofchamps18.activities;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.gigabytedevelopersinc.apps.mezue.classofchamps18.R;
import com.gigabytedevelopersinc.apps.mezue.classofchamps18.fragment.AboutFragment;
import com.gigabytedevelopersinc.apps.mezue.classofchamps18.fragment.ExcossFragment;
import com.gigabytedevelopersinc.apps.mezue.classofchamps18.fragment.HomeFragment;
import com.gigabytedevelopersinc.apps.mezue.classofchamps18.fragment.StaffsFragment;
import com.gigabytedevelopersinc.apps.mezue.classofchamps18.fragment.StudentFragment;
import com.gigabytedevelopersinc.apps.mezue.classofchamps18.utils.TinyDB;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener{
        Toolbar toolbar;
        private TinyDB tinyDB;
    private boolean haveConnectedWifi = false;
    private boolean haveConnectedMobile = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tinyDB = new TinyDB(MainActivity.this);
        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        HomeFragment home = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_frame, home);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setOnQueryTextListener(this);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            HomeFragment home = new HomeFragment();
            FragmentManager homeManager = getSupportFragmentManager();
            homeManager.beginTransaction().replace(R.id.container_frame, home).addToBackStack(null).commit();
            toolbar.setTitle(R.string.nav_home);
        } else if (id == R.id.nav_student) {
            StudentFragment studentFragment = new StudentFragment();
            FragmentManager student = getSupportFragmentManager();
            student.beginTransaction()
                    .replace(R.id.container_frame, studentFragment)
                    .addToBackStack(null)
                    .commit();
            toolbar.setTitle(R.string.nav_student);
            tinyDB.putString("toolbarString", toolbar.getTitle().toString());

        } else if (id == R.id.nav_fyf) {
            ExcossFragment fyfExcossFragment = new ExcossFragment();
            FragmentManager fyfExcossManager = getSupportFragmentManager();
            fyfExcossManager.beginTransaction()
                    .replace(R.id.container_frame, fyfExcossFragment)
                    .addToBackStack(null)
                    .commit();
            toolbar.setTitle(R.string.nav_fyf);
            tinyDB.putString("toolbarString", toolbar.getTitle().toString());

        } else if (id == R.id.nav_excos) {
            ExcossFragment excossFragment = new ExcossFragment();
            FragmentManager excossManger = getSupportFragmentManager();
            excossManger.beginTransaction()
                    .replace(R.id.container_frame, excossFragment)
                    .addToBackStack(null)
                    .commit();
            toolbar.setTitle(R.string.nav_excos);
            tinyDB.putString("toolbarString", toolbar.getTitle().toString());

        } else if (id == R.id.nav_staffs) {
            StaffsFragment staffsFragment = new StaffsFragment();
            FragmentManager staffsManger = getSupportFragmentManager();
            staffsManger.beginTransaction()
                    .replace(R.id.container_frame, staffsFragment)
                    .addToBackStack(null)
                    .commit();
            toolbar.setTitle(R.string.nav_staffs);
            tinyDB.putString("toolbarString", toolbar.getTitle().toString());

        } else if (id == R.id.nav_about) {
            AboutFragment aboutFragment = new AboutFragment();
            FragmentManager about = getSupportFragmentManager();
            about.beginTransaction()
                    .replace(R.id.container_frame, aboutFragment)
                    .addToBackStack(null)
                    .commit();
            toolbar.setTitle(R.string.nav_about);
            toolbar.hideOverflowMenu();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
