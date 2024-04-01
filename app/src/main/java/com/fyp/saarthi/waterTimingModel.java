package com.fyp.saarthi;

public class waterTimingModel {
    public String area,time,date;

    public waterTimingModel(String area, String date,String time) {
        this.area = area;
        this.date = date;
        this.time= time;
    }
    public waterTimingModel(){}

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
