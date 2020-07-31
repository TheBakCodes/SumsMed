package com.thebackcodes.sumsmed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import de.mateware.snacky.Snacky;

public class newpatient_activity extends AppCompatActivity {
Button btcontinue;
EditText etname,etage,etbedno,etcontact,etaddress;
String name,age,bedno,contact,address;
int etcounter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpatient_activity);
        btcontinue=(Button)findViewById(R.id.btcontinue);
        etaddress=(EditText)findViewById(R.id.etaddress);
        etage=(EditText)findViewById(R.id.etage);
        etbedno=(EditText)findViewById(R.id.etbedno);
        etcontact=(EditText)findViewById(R.id.etcontact);
        etname=(EditText)findViewById(R.id.etName);
        final Drawable ettickIcon = getResources().getDrawable(R.drawable.ettick_icon);
        ettickIcon.setBounds(0, 0, ettickIcon.getIntrinsicWidth(), ettickIcon.getIntrinsicHeight());
        btcontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etcounter=0;
                if(!(name=etname.getText().toString()).equalsIgnoreCase(""))
                {
                    etname.setError("Done",ettickIcon);
                    etcounter++;
                   // editor.putString("Name", name);
                }
                else {
                    etname.setError("Should not be blank");
                }

                if(!(contact=etcontact.getText().toString()).equalsIgnoreCase(""))
                {
                    etcontact.setError("Done",ettickIcon);
                    etcounter++;
                    //editor.putString("Contact", contact);
                }
                else {
                    etcontact.setError("Should not be blank");
                }

                if(!(address=etaddress.getText().toString()).equalsIgnoreCase(""))
                {
                    etaddress.setError("Done",ettickIcon);
                    etcounter++;
                    //editor.putString("ID", id);

                }
                else {
                    etaddress.setError("Should not be blank");
                }

                if(!(age=etage.getText().toString()).equalsIgnoreCase(""))
                {
                    etage.setError("Done",ettickIcon);
                    etcounter++;
                   // editor.putString("Email", email);
                }
                else {
                    etage.setError("Should not be blank");
                }

                if(!(bedno=etbedno.getText().toString()).equalsIgnoreCase(""))
                {
                    etbedno.setError("Done",ettickIcon);
                    etcounter++;
                    // editor.putString("Email", email);
                }
                else {
                    etbedno.setError("Should not be blank");
                }

                if(etcounter==5)
                {
                    //editor.commit();
                    Snacky.builder()
                            .setActivity(newpatient_activity.this)
                            .setText("Account Successfully Created.")
                            .setDuration(Snacky.LENGTH_SHORT)
                            .success()
                            .show();
                    Intent intent=new Intent(newpatient_activity.this,Home_Activity.class);
                    startActivity(intent);
                    //mBottomSheetDialog.show();
                    addpatient adp=new addpatient();
                    adp.execute(" https://rahman09.pythonanywhere.com/patient/");

                }
            }
        });


    }

    class addpatient extends AsyncTask<String,String,String>
    {
        String inputLine = null;

        @Override
        protected String doInBackground(String... strings) {

            try{
                URL url=new URL(strings[0]);
                HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                // conn.addRequestProperty("Authorization","key=AAAAHH-eN8k:APA91bEkzMmqUxPzeIRx-OSvDypQhkmIeIQgyUDquxGypSkuSKa_kyW0YxmhFYGFcbDA7JeHAsaEwf-EqF9FtmAgtj67LnN5ZPo3M-iEPgGWFMjfUiii7OYky1OHJ4qgpVCGY-a9_1SO");
                conn.setRequestProperty("Content-Type", "application/json");
                OutputStream outputStream=conn.getOutputStream();
                JSONObject parent = new JSONObject();
                parent.put("name", name);
                parent.put("age", Integer.parseInt(age));
                parent.put("contact", Integer.parseInt(contact));
                parent.put("address", address);
                parent.put("bed_no", Integer.parseInt(bedno));
                parent.put("discharge", false);
                outputStream.write(parent.toString().getBytes());
                outputStream.close();
                int responseCode = conn.getResponseCode();
                System.out.println("POST Response Code :: " + responseCode);

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();


                    System.out.println("Response="+response.toString());
                } else {
                    inputLine="Error";
                    System.out.println("POST request not worked");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return inputLine;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
           // tv.setText("Done");
            Toast.makeText(newpatient_activity.this, inputLine, Toast.LENGTH_SHORT).show();
            if(inputLine.equalsIgnoreCase("Error"))
            {
                Snacky.builder()
                        .setActivity(newpatient_activity.this)
                        .setText("Something Went Wrong! \n Try Again..")
                        .setDuration(Snacky.LENGTH_SHORT)
                        .error()
                        .show();
            }
            else
            {
                //finish();
                Snacky.builder()
                        .setActivity(newpatient_activity.this)
                        .setText("Successfully Added new Patient..")
                        .setDuration(Snacky.LENGTH_SHORT)
                        .success()
                        .show();

            }


        }
    }


}