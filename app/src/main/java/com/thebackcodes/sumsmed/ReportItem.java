package com.thebackcodes.sumsmed;

public class ReportItem {

    private  String time;
    private  String date;
    private String updater ;
    private String status ;


    public ReportItem(String time, String date, String updater, String status){
        this.time=time;
        this.date=date;
        this.updater=updater;
        this.status=status;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public String getUpdater() {
        return updater;
    }
}
