package com.thebackcodes.sumsmed;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BedAdapter extends RecyclerView.Adapter<BedAdapter.MyViewHolder> {
     String list[];
     Context context;

    ProgressDialog pd;
    String jsonstring ;

     BedAdapter(String lis[], Context ct)
     {
         list=lis;
         context=ct;
     }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bednoui, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.tvbed.setText(list[position]);
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientID = "1"; // testing value
              //  String id =   save patient id here

                jsondownload jsd=new jsondownload();
                jsd.execute(" https://rahman09.pythonanywhere.com/report?patient_id="+patientID);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvbed;
        CardView cv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvbed=(TextView)itemView.findViewById(R.id.tvbedno);
            cv=(CardView)itemView.findViewById(R.id.cv);

        }
    }

    private class jsondownload extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(context);
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
            //tv.setText(result);
            jsonstring=result;

            Toast.makeText(context, jsonstring, Toast.LENGTH_SHORT).show();

            Intent myIntent = new Intent(context, Reports_activity.class);
            myIntent.putExtra("jsonkey", jsonstring); //Optional parameters
            context.startActivity(myIntent);


        }



    }
}



