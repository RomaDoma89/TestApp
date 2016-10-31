package com.example.flexihub.test.testapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.flexihub.test.testapp.adapters.BlocksPagerAdapter;
import com.example.flexihub.test.testapp.fragments.BlockFragment;
import com.example.flexihub.test.testapp.models.Line;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BlocksPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initPagerAdapter();

        // Set up the ViewPager with the sections adapter.
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapter);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.drawer_line_1) {

        } else if (id == R.id.drawer_line_2) {

        } else if (id == R.id.drawer_line_3) {

        } else if (id == R.id.drawer_line_4) {

        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.drawer_line_1) {
            // Handle the camera action
        } else if (id == R.id.drawer_line_2) {

        } else if (id == R.id.drawer_line_3) {

        } else if (id == R.id.drawer_line_4) {

        } else if (id == R.id.drawer_line_5) {

        } else if (id == R.id.drawer_line_6) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initPagerAdapter() {
        pagerAdapter = new BlocksPagerAdapter(getSupportFragmentManager());
        ArrayList<Line> linesFirst = new ArrayList<>();
        linesFirst.add(new Line("Line 1", "Available"));
        linesFirst.add(new Line("Line 2", "Available"));
        pagerAdapter.addBlock(BlockFragment.newInstance("First", linesFirst));

        ArrayList<Line> linesSecond = new ArrayList<>();
        linesSecond.add(new Line("Line 1", "Available"));
        linesSecond.add(new Line("Line 2", "Not available"));
        linesSecond.add(new Line("Line 3", "Available"));
        pagerAdapter.addBlock(BlockFragment.newInstance("Second", linesSecond));
    }
}
