package com.thebackcodes.sumsmed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReportsAdapter extends RecyclerView.Adapter<ReportsAdapter.ExampleViewHolder> {

    private  ArrayList<ReportItem> mExampleList;

    public ReportsAdapter(ArrayList<ReportItem> exampleList){

        mExampleList=exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item,parent,false);
        ExampleViewHolder evh = new ExampleViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {

        ReportItem currentItem = mExampleList.get(position);

        holder.m_time.setText(currentItem.getTime());
        holder.m_Date.setText(currentItem.getDate());
        holder.m_updator.setText("Updated by "+currentItem.getUpdater());
        holder.m_status.setText(currentItem.getStatus());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    private  OnItemClickListener mListener;
    public interface OnItemClickListener
    {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener=listener;
    }


    public static class ExampleViewHolder extends RecyclerView.ViewHolder{

        public TextView m_time;
        public TextView m_Date;
        public TextView m_updator;
        public TextView m_status;

        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            m_Date = itemView.findViewById(R.id.date);
            m_status = itemView.findViewById(R.id.status);
            m_time = itemView.findViewById(R.id.time);
            m_updator=itemView.findViewById(R.id.updater);


            //click listener of each card
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION)
                        {
                            listener.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }

}
