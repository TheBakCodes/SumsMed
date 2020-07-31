package com.thebackcodes.sumsmed;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;

public class Parameter_circular_Adapter extends RecyclerView.Adapter<Parameter_circular_Adapter.ExampleViewHolder> {

    private  ArrayList<parameter_Circular_Item> sExampleList;

    public Parameter_circular_Adapter(ArrayList<parameter_Circular_Item> exampleList){

        sExampleList=exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.parameter_circular_item,parent,false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {

        parameter_Circular_Item currentItem = sExampleList.get(position);

        holder.s_current_value.setText(""+currentItem.getMy_current_value());
        holder.s_parameter_name.setText(currentItem.getMy_name_of_parameter());


        // Set Progress
        // circularProgressBar.setProgress(65f);
// or with animation

        String current= currentItem.getMy_current_value();
        float opti_min = currentItem.getMy_optimal_min_value();
        float opti_max = currentItem.getMy_optimal_max_value();
        float percentage;
      try{
           percentage = (Float.parseFloat(current)-opti_min)/(opti_max-opti_min)*100;
      }catch (Exception e)
      {
           percentage = 0;
      }


        holder.circularProgressBar.setProgressWithAnimation(percentage, 1000L); // =1s
       // holder.circularProgressBar.setProgressWithAnimation(50, 1000L); //temp for testing


        // Set Progress Max

        holder.circularProgressBar.setProgressMax(100f);

// Set ProgressBar Color
        holder.circularProgressBar.setProgressBarColor(Color.BLACK);
// or with gradient
        holder.circularProgressBar.setProgressBarColorStart(Color.parseColor("#03DAC5"));    //GRAY
        holder.circularProgressBar.setProgressBarColorEnd(Color.parseColor("#03DAC5"));      //RED
        if(percentage>90 || percentage<10)
        {
            holder.circularProgressBar.setProgressBarColorStart(Color.RED);    //GRAY
            holder.circularProgressBar.setProgressBarColorEnd(Color.RED);
        }

        holder.circularProgressBar.setProgressBarColorDirection(CircularProgressBar.GradientDirection.TOP_TO_BOTTOM);

// Set background ProgressBar Color
        holder.circularProgressBar.setBackgroundProgressBarColor(Color.GRAY);
// or with gradient
        holder.circularProgressBar.setBackgroundProgressBarColorStart(Color.WHITE);
        holder.circularProgressBar.setBackgroundProgressBarColorEnd(Color.GRAY);   //RED
        holder.circularProgressBar.setBackgroundProgressBarColorDirection(CircularProgressBar.GradientDirection.TOP_TO_BOTTOM);

// Set Width
        holder.circularProgressBar.setProgressBarWidth(7f); // in DP
        holder.circularProgressBar.setBackgroundProgressBarWidth(3f); // in DP

// Other
        holder.circularProgressBar.setRoundBorder(true);
        holder.circularProgressBar.setStartAngle(0f);
        holder.circularProgressBar.setProgressDirection(CircularProgressBar.ProgressDirection.TO_RIGHT);


    }

    @Override
    public int getItemCount() {
        return sExampleList.size();
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{


        public TextView s_parameter_name;
        public TextView s_current_value;
        public CircularProgressBar circularProgressBar;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            s_parameter_name = itemView.findViewById(R.id.parameter_name);
            s_current_value = itemView.findViewById(R.id.current_value);
            circularProgressBar = itemView.findViewById(R.id.cpbhb);
        }
    }

}
