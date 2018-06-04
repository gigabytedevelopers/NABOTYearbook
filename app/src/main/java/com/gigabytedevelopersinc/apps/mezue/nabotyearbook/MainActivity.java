package com.gigabytedevelopersinc.apps.mezue.nabotyearbook;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gigabytedevelopersinc.apps.mezue.nabotyearbook.fragment.AboutFragment;
import com.gigabytedevelopersinc.apps.mezue.nabotyearbook.fragment.ExcossFragment;
import com.gigabytedevelopersinc.apps.mezue.nabotyearbook.fragment.HomeFragment;
import com.gigabytedevelopersinc.apps.mezue.nabotyearbook.fragment.StudentFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        HomeFragment home = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_frame, home);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            HomeFragment home = new HomeFragment();
            FragmentManager homeManager = getSupportFragmentManager();
            homeManager.beginTransaction().replace(R.id.container_frame, home).commit();
            toolbar.setTitle(R.string.nav_home);
        } else if (id == R.id.nav_excos) {
            ExcossFragment excossFragment = new ExcossFragment();
            FragmentManager excossManger = getSupportFragmentManager();
            excossManger.beginTransaction().replace(R.id.container_frame, excossFragment).commit();
            toolbar.setTitle(R.string.nav_excos);

        } else if (id == R.id.nav_student) {
            StudentFragment studentFragment = new StudentFragment();
            FragmentManager student = getSupportFragmentManager();
            student.beginTransaction().replace(R.id.container_frame, studentFragment).commit();
            toolbar.setTitle(R.string.nav_student);

        } else if (id == R.id.nav_about) {
            AboutFragment aboutFragment = new AboutFragment();
            FragmentManager about = getSupportFragmentManager();
            about.beginTransaction().replace(R.id.container_frame, aboutFragment).commit();
            toolbar.setTitle(R.string.nav_about);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
