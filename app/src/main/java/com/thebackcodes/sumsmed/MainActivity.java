package com.thebackcodes.sumsmed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btmaindoc,btmainnurse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Hello Developers, Do your job fast");
        btmaindoc=(Button)findViewById(R.id.btmaindoc);
        btmainnurse=(Button)findViewById(R.id.btmainnurse);
        btmaindoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent i = new Intent(MainActivity.this,createacc_activity.class);
                i.putExtra("User_Type", "Doctor");
                startActivity(i);*/
                Intent i = new Intent(MainActivity.this,Home_Activity.class);
                startActivity(i);
            }
        });
        btmainnurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,createacc_activity.class);
                i.putExtra("User_Type", "Nurse");
                startActivity(i);
            }
        });

    }
}
