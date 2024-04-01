package com.fyp.saarthi;

public class waterTimingModelAdmin {
    public String area,time,date;

    public waterTimingModelAdmin(String area, String date, String time) {
        this.area = area;
        this.date = date;
        this.time= time;

    }
    public waterTimingModelAdmin(){}



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
