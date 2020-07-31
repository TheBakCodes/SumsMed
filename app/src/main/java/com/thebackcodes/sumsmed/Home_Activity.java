package com.thebackcodes.sumsmed;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Home_Activity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    MaterialSearchView searchView;
    RecyclerView rcvvent,rcvemer,rcvpatie;
    FloatingActionButton fab;

    ProgressDialog pd;
    String list1[]={"1","2","3","4","5","6","7","8","9","10"};
    String list2[]={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
    String list3[]={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setTitle("");
        fab=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home_Activity.this,newpatient_activity.class);
                startActivity(intent);
            }
        });

        jsondownload jsd=new jsondownload();
        jsd.execute("https://rahman09.pythonanywhere.com/patient/beds/");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    private class jsondownload extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(Home_Activity.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)


                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()){
                pd.dismiss();
            }
           // tv.setText(result);
            load(result);
        }



    }



    public void load(String w)
    {
        Toast.makeText(this, w, Toast.LENGTH_LONG).show();
        rcvvent=(RecyclerView)findViewById(R.id.rcvvent);
        // set a GridLayoutManager with default vertical orientation and 2 number of columns
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getApplicationContext(),4);
        rcvvent.setLayoutManager(gridLayoutManager1); // set LayoutManager to RecyclerView
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        rcvvent.setAdapter(new BedAdapter(list1,Home_Activity.this)); // set the Adapter to RecyclerView

        rcvemer=(RecyclerView)findViewById(R.id.rcvemerg);
        // set a GridLayoutManager with default vertical orientation and 2 number of columns
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getApplicationContext(),4);
        rcvemer.setLayoutManager(gridLayoutManager2); // set LayoutManager to RecyclerView
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        rcvemer.setAdapter(new BedAdapter(list2,Home_Activity.this)); // set the Adapter to RecyclerView

        rcvpatie=(RecyclerView)findViewById(R.id.rcvpatient);
        // set a GridLayoutManager with default vertical orientation and 2 number of columns
        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(getApplicationContext(),4);
        rcvpatie.setLayoutManager(gridLayoutManager3); // set LayoutManager to RecyclerView
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        rcvpatie.setAdapter(new BedAdapter(list3,Home_Activity.this)); // set the Adapter to RecyclerView
    }


}
