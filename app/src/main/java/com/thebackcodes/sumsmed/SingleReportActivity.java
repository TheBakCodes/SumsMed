package com.thebackcodes.sumsmed;

import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SingleReportActivity extends AppCompatActivity {

    String[] parameterarray;
    String patientname;
    String patientBedNumber;
    Boolean Dialysis;
    String updatorName;
    String updateTime;
    String updateDate;

    TextView tv_name;
    TextView tv_bed;
    TextView tv_updation_info;
    Switch dial;
    private RecyclerView sRecyclerview;
    private RecyclerView.Adapter sAdapter;
    private RecyclerView.LayoutManager sLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_record);

        Bundle b = this.getIntent().getExtras();
        parameterarray=b.getStringArray("arraydata");
        patientname =b.getString("patientName");
        patientBedNumber=b.getString("patientBedNo");
        if(parameterarray[16].equals("0")||parameterarray[16].equals("null")) Dialysis=false; else Dialysis=true;
        //updatorName=parameterarray[21];
        updatorName="Pabs";  // temp value for testing
        updateDate= ""+ parameterarray[1].charAt(8)
                +parameterarray[1].charAt(9)
                +parameterarray[1].charAt(7)+
                parameterarray[1].charAt(5)+
                parameterarray[1].charAt(6)+
                parameterarray[1].charAt(4)+
                parameterarray[1].charAt(0)+
                parameterarray[1].charAt(1)+
                parameterarray[1].charAt(2)+
                parameterarray[1].charAt(3);
        updateTime=parameterarray[1].substring(11,16);


        tv_name= findViewById(R.id.tvpatname);
        tv_bed= findViewById(R.id.tvbedno);
        tv_updation_info= findViewById(R.id.tvlastupdate);
        dial= findViewById(R.id.dialysis);

        tv_name.setText(patientname);
        tv_bed.setText("Bed No- "+patientBedNumber);
        tv_updation_info.setText("updated by "+updatorName+" on "+updateDate+" at "+updateTime);
        dial.setChecked(Dialysis);
        dial.setClickable(false);




        final ArrayList<parameter_Circular_Item> parameterlist =new ArrayList<>();

        parameterlist.add(new parameter_Circular_Item(getString(R.string.hb),0,0,1,20,parameterarray[2]));
        parameterlist.add(new parameter_Circular_Item(getString(R.string.tlc),0,0,1,500,parameterarray[3]));
        parameterlist.add(new parameter_Circular_Item(getString(R.string.platelet),0,0,1,1000,parameterarray[4]));
        parameterlist.add(new parameter_Circular_Item(getString(R.string.ferritin),0,0,1,50000,parameterarray[5]));
        parameterlist.add(new parameter_Circular_Item(getString(R.string.crp),0,0,1,200,parameterarray[6]));
        parameterlist.add(new parameter_Circular_Item(getString(R.string.peak_pressure),0,0,1,100,parameterarray[7]));
        parameterlist.add(new parameter_Circular_Item(getString(R.string.f1o2),0,0,1,100,parameterarray[8]));
        parameterlist.add(new parameter_Circular_Item(getString(R.string.pf_ratio),0,0,1,500,parameterarray[9]));
        parameterlist.add(new parameter_Circular_Item(getString(R.string.peep),0,0,1,30,parameterarray[10]));
        parameterlist.add(new parameter_Circular_Item(getString(R.string.one_e),0,0,0,0,parameterarray[11]));
        parameterlist.add(new parameter_Circular_Item(getString(R.string.ps_tv),0,0,0,0,parameterarray[12]));
        parameterlist.add(new parameter_Circular_Item(getString(R.string.bp),0,0,0,200,parameterarray[13]));
        parameterlist.add(new parameter_Circular_Item(getString(R.string.norad_dose),0,0,1,30,parameterarray[14]));
        parameterlist.add(new parameter_Circular_Item(getString(R.string.vaso),0,0,1,8,parameterarray[15]));
        parameterlist.add(new parameter_Circular_Item(getString(R.string.heartrate),0,0,0,300,parameterarray[17]));
        parameterlist.add(new parameter_Circular_Item(getString(R.string.resp_rate),0,0,0,150,parameterarray[18]));
        parameterlist.add(new parameter_Circular_Item(getString(R.string.spo2),0,0,0,100,parameterarray[19]));
        parameterlist.add(new parameter_Circular_Item(getString(R.string.temp),0,0,0,120,parameterarray[20]));







        sRecyclerview =findViewById (R.id.s_recyclerView);
        sRecyclerview.setHasFixedSize(true);
        sAdapter = new Parameter_circular_Adapter(parameterlist);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        sRecyclerview.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        sRecyclerview.setAdapter(sAdapter); // set the Adapter to RecyclerView


    }
}