package com.ithinkbest.myapplication5;

import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("photocloud5x5");


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        // populate the navigation drawer
        mNavigationDrawerFragment.setUserData("John Doe", "johndoe@doe.com", BitmapFactory.decodeResource(getResources(), R.drawable.avatar));
    }

//    @Override
//    public void onNavigationDrawerItemSelected(int position) {
//        // update the main content by replacing fragments
//     }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
     //   Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();

        Fragment fragment;
        switch (position) {
            case 0: //search//todo
              //  getSupportActionBar().setTitle("Human first");

                GameAbout.mode=1;
                fragment = new Game1Fragment();
                getFragmentManager().beginTransaction().
                        replace(R.id.container, fragment).commit();
                Log.d("MARK987", "Game1");
                break;




//                fragment = getFragmentManager().findFragmentByTag(F1Fragment.TAG);
//                if (fragment == null) {
//                    fragment = new F1Fragment();
//                }
//                getFragmentManager().beginTransaction().
//                        replace(R.id.container, fragment, F1Fragment.TAG).commit();
//                Log.d("MARK987", "CASE 0");
//                break;
            case 1:
                //getSupportActionBar().setTitle("Android first");

                GameAbout.mode=2;
                fragment = new Game1Fragment();
                getFragmentManager().beginTransaction().
                        replace(R.id.container, fragment).commit();
//                Log.d("MARK987", "Game1");
                break;

            case 2: //my account //todo
                fragment = new Game1Fragment();
                getFragmentManager().beginTransaction().
                        replace(R.id.container, fragment).commit();
//                Log.d("MARK987", "Game1");
                break;
            case 3: //settings //todo
                break;
        }
    }



    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onClickImg2(View view) {
        Log.d("MARK987","doing something here...");
    }
}
