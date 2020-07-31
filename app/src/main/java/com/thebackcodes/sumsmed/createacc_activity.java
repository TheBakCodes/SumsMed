package com.thebackcodes.sumsmed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import de.mateware.snacky.Snacky;


public class createacc_activity extends AppCompatActivity {
    Button btcracccontinue;
    EditText etcraccname,etcraccid,etcraccemail,etcracccontact;
    public static final String MyPREFERENCES = "UserData" ;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    String name,email,id,contact;
    //BottomSheetMaterialDialog mBottomSheetDialog;
    int etcounter=0;// to count number of edittext are correctly filled
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createacc_activity);
        btcracccontinue=(Button)findViewById(R.id.btcracccontinue);
        etcracccontact=(EditText)findViewById(R.id.etcracccontact);
        etcraccemail=(EditText)findViewById(R.id.ercraccemail);
        etcraccid=(EditText)findViewById(R.id.etcraccid);
        etcraccname=(EditText)findViewById(R.id.etcraccname);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
      /* mBottomSheetDialog = new BottomSheetMaterialDialog.Builder(this)
                .setTitle("Confirm?")
                .setMessage("Are you sure details are correct?")
                .setCancelable(false)
                .setPositiveButton("Confirm", R.drawable.ok_icon, new BottomSheetMaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(getApplicationContext(), "Account Created!", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                        Intent intent=new Intent(createacc_activity.this,Home_Activity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", R.drawable.cancel_icon, new BottomSheetMaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(getApplicationContext(), "Cancelled!", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                })
                .build();*/
        Intent intent = getIntent();
        String UserType = intent.getExtras().getString("User_Type");

        if(UserType.equalsIgnoreCase("Doctor"))
        {
            etcraccid.setHint("Doctor ID");
        }
        else if (UserType.equalsIgnoreCase("Nurse"))
        {
            etcraccid.setHint("Nurse ID");
        }

        final Drawable ettickIcon = getResources().getDrawable(R.drawable.ettick_icon);
        ettickIcon.setBounds(0, 0, ettickIcon.getIntrinsicWidth(), ettickIcon.getIntrinsicHeight());



        btcracccontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etcounter=0;
                if(!(name=etcraccname.getText().toString()).equalsIgnoreCase(""))
                {
                    etcraccname.setError("Done",ettickIcon);
                    etcounter++;
                    editor.putString("Name", name);
                }
                else {
                    etcraccname.setError("Should not be blank");
                }

                if(!(contact=etcracccontact.getText().toString()).equalsIgnoreCase(""))
                {
                    etcracccontact.setError("Done",ettickIcon);
                    etcounter++;
                    editor.putString("Contact", contact);
                }
                else {
                    etcracccontact.setError("Should not be blank");
                }

                if(!(id=etcraccid.getText().toString()).equalsIgnoreCase(""))
                {
                    etcraccid.setError("Done",ettickIcon);
                    etcounter++;
                    editor.putString("ID", id);

                }
                else {
                    etcraccid.setError("Should not be blank");
                }

                if(!(email=etcraccemail.getText().toString()).equalsIgnoreCase(""))
                {
                    etcraccemail.setError("Done",ettickIcon);
                    etcounter++;
                    editor.putString("Email", email);
                }
                else {
                    etcraccemail.setError("Should not be blank");
                }

                if(etcounter==4)
                {
                    editor.commit();
                    Snacky.builder()
                            .setActivity(createacc_activity.this)
                            .setText("Account Successfully Created.")
                            .setDuration(Snacky.LENGTH_SHORT)
                            .success()
                            .show();
                    Intent intent=new Intent(createacc_activity.this,Home_Activity.class);
                    startActivity(intent);
                    //mBottomSheetDialog.show();
                }

            }
        });





    }
}