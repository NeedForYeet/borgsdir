package com.auerbauerjensch.borgsdir;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class BrowseItemsActivity extends MainActivity {


    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_items);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

/*        //TODO: fix icon
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_camera);
        ab.setDisplayHomeAsUpEnabled(true);*/

        drawer = (DrawerLayout) findViewById(com.auerbauerjensch.borgsdir.R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, com.auerbauerjensch.borgsdir.R.string.navigation_drawer_open, com.auerbauerjensch.borgsdir.R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(com.auerbauerjensch.borgsdir.R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        loggedInNavView();

    }
    public void loggedInNavView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().findItem(R.id.nav_myprofile).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_favorites).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_messages).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_lentproducts).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_myproducts).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_geborgt).setVisible(true);

        navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_register).setVisible(false);

        // swap the big logo  for small one and name/email
        View headerView = navigationView.getHeaderView(0);
        headerView.findViewById(R.id.header_logo_big).setVisibility(View.GONE);
        headerView.findViewById(R.id.header_email).setVisibility(View.VISIBLE);
        headerView.findViewById(R.id.header_name).setVisibility(View.VISIBLE);
        headerView.findViewById(R.id.header_logo_small).setVisibility(View.VISIBLE);
    }
    // TODO: Add search tab here. fuck the symbol in the titlebar
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new ProductListFragment(), "Produkte");
        adapter.addFragment(new SearchFragment(), "Suchen");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == com.auerbauerjensch.borgsdir.R.id.nav_myprofile) {
            Intent intent = new Intent(this, ViewProfileActivity.class);
            startActivity(intent);
        } else if (id == com.auerbauerjensch.borgsdir.R.id.nav_messages) {

        } else if (id == com.auerbauerjensch.borgsdir.R.id.nav_favorites) {

        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        } else if (id == com.auerbauerjensch.borgsdir.R.id.nav_myproducts) {

        } else if (id == com.auerbauerjensch.borgsdir.R.id.nav_lentproducts) {
            startActivity(new Intent(this, BorrowActivity.class));
        } else if (id == R.id.nav_login) {
            loggedInNavView();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_register) {
            loggedInNavView();
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(com.auerbauerjensch.borgsdir.R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
