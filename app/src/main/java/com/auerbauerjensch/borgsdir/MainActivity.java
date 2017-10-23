package com.auerbauerjensch.borgsdir;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        DrawerLayout drawer = (DrawerLayout) findViewById(com.auerbauerjensch.borgsdir.R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // change visibility of drawer items.
        // if not logged in, there should only be login, register, settings
        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        loggedOutNavView();


        // Go to RegisterActivity with Register button in content_main
        Button btn_register = (Button) findViewById(R.id.button_start_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loggedInNavView();
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });


        // Go to LoginActivity with Login button in content_main
        Button btn_login = (Button) findViewById(R.id.button_start_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loggedInNavView();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });


        Button btn_stoebern = (Button) findViewById(R.id.stoebern_content_main);
        btn_stoebern.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, BrowseItemsActivity.class ));

            }
        });
    }

    /**
     * Change the state of the navigation drawer to logged out.
     * Most items are hidden this way.
     * @param
     */
    public void loggedOutNavView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

       navigationView.getMenu().findItem(R.id.nav_myprofile).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_favorites).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_messages).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_lentproducts).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_myproducts).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_geborgt).setVisible(false);

        // swap name/email for big logo
        View headerView = navigationView.getHeaderView(0);
        headerView.findViewById(R.id.header_logo_big).setVisibility(View.VISIBLE);
        headerView.findViewById(R.id.header_email).setVisibility(View.GONE);
        headerView.findViewById(R.id.header_name).setVisibility(View.GONE);
        headerView.findViewById(R.id.header_logo_small).setVisibility(View.GONE);

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(com.auerbauerjensch.borgsdir.R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
/*        if (id == R.id.action_settings) {
            return true;
        }*/

        switch (item.getItemId()) {
            case com.auerbauerjensch.borgsdir.R.id.action_anmelden:
                return true;
            case com.auerbauerjensch.borgsdir.R.id.action_registrieren:
                return true;
            case com.auerbauerjensch.borgsdir.R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * method to call login activity.
     * As we call this method from a Menu, we have to use MenuItem instead of View.
     * @param menuItem: the "Anmelden" MenuButton
     */
    public void doLogin(MenuItem menuItem) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Method to call register activity
     * @param menuItem: the "Registrieren" MenuButton
     */
    public void doRegister(MenuItem menuItem) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void doSettingsMenu(MenuItem menuItem) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
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
