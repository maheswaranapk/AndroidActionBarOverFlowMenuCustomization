package com.scriptedpapers.actionbarcustomization;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Field;


public class MainActivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialization
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        actionBar = getSupportActionBar();

        // Set Adapter to navigation Drawer
        mDrawerList.setAdapter(new NavigationDrawerAdapter(MainActivity.this));

        // Change Action Bar Background Color
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33B5E5")));

        // Change Action Bar Title Text
        actionBar.setTitle("Action Bar");

        // Change Action Bar Icon
        actionBar.setIcon(R.drawable.ic_launcher);

        // Enable Home Button
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Set Adapter to navigation Drawer
        mDrawerList.setAdapter(new NavigationDrawerAdapter(MainActivity.this));

        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                new Toolbar(MainActivity.this),
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            public void onDrawerClosed(View view) {
                actionBar.setTitle("Action Bar");
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                actionBar.setTitle("Menu Opened");
                invalidateOptionsMenu();
            }
        };

		/*
		 * Hack to enable Software Setting Button in Action Bar
		 * Even if device has Hardware Setting Button
		 */
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(getApplicationContext(), "Entered Keyword is "
                +s, Toast.LENGTH_LONG).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch(item.getItemId()) {
            case R.id.refreshButton:
                Toast.makeText(getApplicationContext(), "Refresh",Toast.LENGTH_LONG).show();
                return true;
            case R.id.helpButton:
                Toast.makeText(getApplicationContext(), "Help",Toast.LENGTH_LONG).show();
                return true;
            case R.id.settingButton:
                Toast.makeText(getApplicationContext(), "Setting",Toast.LENGTH_LONG).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Sync the toggle state
        mDrawerToggle.syncState();
    }
}
