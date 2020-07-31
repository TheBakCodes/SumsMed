package com.thebackcodes.sumsmed;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Reports_activity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    Button btDischahge;
    TextView tvName,tvBed,tvUpdater;


    String jsonstring ;   // the json , after being fetched , is stored in this String
    String patientname;
    String patientBedNumber;
    String[][] patientRecord;  // data from json will be saved in this array   [recordID] [parameters]


    private RecyclerView mRecyclerview;
    private ReportsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports_activity);
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

        btDischahge=(Button)findViewById(R.id.btdischarge) ;
        btDischahge.setOnClickListener(new View.OnClickListener() {
            String id="1";
            @Override
            public void onClick(View v) {
              // report to server
            }
        });


        tvName = (TextView)findViewById(R.id.tvpatname);
        tvBed = (TextView)findViewById(R.id.tvbedno);

        Intent intent = getIntent();
        jsonstring = intent.getStringExtra("jsonkey"); //if it's a string you stored.

        try {

            JSONObject obj = new JSONObject(jsonstring);

            //patient details
            JSONObject patient= obj.getJSONObject("patient");
            patientname = patient.getString("name");
            tvName.setText(patientname);
            patientBedNumber = patient.getString("bed_no");
            tvBed.setText("Bed Number- "+patientBedNumber);
            //Updater here


            //all records are saved in 2D array
            JSONArray jsonArray = obj.optJSONArray("reports");
            patientRecord = new String[jsonArray.length()][21];
            for(int i=0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id_0 = jsonObject.optString(getString(R.string.id));
                String timestamp_1 = jsonObject.optString(getString(R.string.timestamp));
                String hb_2 = jsonObject.optString(getString(R.string.hb));
                String tlc_3 = jsonObject.optString(getString(R.string.tlc));
                String platelet_4 = jsonObject.optString(getString(R.string.platelet));
                String ferritin_5 = jsonObject.optString(getString(R.string.ferritin));
                String crp_6 = jsonObject.optString(getString(R.string.crp));
                String peak_pressure_7 = jsonObject.optString(getString(R.string.peak_pressure));
                String f1o2_8 = jsonObject.optString(getString(R.string.f1o2));
                String pf_ratio_9 = jsonObject.optString(getString(R.string.pf_ratio));
                String peep_10 = jsonObject.optString(getString(R.string.peep));
                String one_e_11 = jsonObject.optString(getString(R.string.one_e));
                String ps_tv_12 = jsonObject.optString(getString(R.string.ps_tv));
                String bp_13 = jsonObject.optString(getString(R.string.bp));
                String norad_dose_14 = jsonObject.optString(getString(R.string.norad_dose));
                String vaso_15 = jsonObject.optString(getString(R.string.vaso));
                String dialysis_16 = jsonObject.optString(getString(R.string.dialysis));
                String heartrate_17 = jsonObject.optString(getString(R.string.heartrate));
                String resp_rate_18 = jsonObject.optString(getString(R.string.resp_rate));
                String spo2_19 = jsonObject.optString(getString(R.string.spo2));
                String temp_20 = jsonObject.optString(getString(R.string.temp));
                // String updater_21 = jsonObject.optString(getString(R.string.updater));
                // String status_22 = jsonObject.optString(getString(R.string.status));
                //String

                patientRecord[i][0] = id_0;
                patientRecord[i][1] = timestamp_1;
                patientRecord[i][2] = hb_2;
                patientRecord[i][3] = tlc_3;
                patientRecord[i][4] = platelet_4;
                patientRecord[i][5] = ferritin_5;
                patientRecord[i][6] = crp_6;
                patientRecord[i][7] = peak_pressure_7;
                patientRecord[i][8] = f1o2_8;
                patientRecord[i][9] = pf_ratio_9;
                patientRecord[i][10] = peep_10;
                patientRecord[i][11] = one_e_11;
                patientRecord[i][12] = ps_tv_12;
                patientRecord[i][13] = bp_13;
                patientRecord[i][14] = norad_dose_14;
                patientRecord[i][15]= vaso_15;
                patientRecord[i][16]= dialysis_16;
                patientRecord[i][17]= heartrate_17;
                patientRecord[i][18]= resp_rate_18;
                patientRecord[i][19]= spo2_19;
                patientRecord[i][20]= temp_20;
                // patientRecord[i][21]= updater_21;
                // patientRecord[i][22]=status_22;


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //Arraylist(of type RecordItem) to be send to adapter class-'RecordAdapter'
        final ArrayList<ReportItem> RecordItemlist = new ArrayList<>();
        for (int i=0;i<patientRecord.length;i++)
        {
            String time =""+ patientRecord[i][1].charAt(8)+patientRecord[i][1].charAt(9)+patientRecord[i][1].charAt(7)+patientRecord[i][1].charAt(5)+patientRecord[i][1].charAt(6)+patientRecord[i][1].charAt(4)+patientRecord[i][1].charAt(0)+patientRecord[i][1].charAt(1)+patientRecord[i][1].charAt(2)+patientRecord[i][1].charAt(3);
            String date =""+patientRecord[i][1].substring(11,16);
            //String updater=""+patientRecord[i][21];
            String updater="Pabitra Sahoo";       // temp value for testing
            //String status=""+patientRecord[i][22];
            String status="Normal";                // temp value for testing
            RecordItemlist.add(new ReportItem(time,date,updater,status));
        }


        //Buils adapter and set clicklistener
        mRecyclerview =findViewById (R.id.recyclerView_AllRecords);
        mRecyclerview.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ReportsAdapter(RecordItemlist);

        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(mLayoutManager);

        mAdapter.setOnItemClickListener(new ReportsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {

                Toast.makeText(Reports_activity.this, ""+position, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), SingleReportActivity.class);

                Bundle b = new Bundle();
                b.putStringArray("arraydata", patientRecord[position]);
                b.putString("patientName",patientname);
                b.putString("patientBedNo",patientBedNumber);

                i.putExtras(b);
                startActivity(i);

            }
        });


    }
}