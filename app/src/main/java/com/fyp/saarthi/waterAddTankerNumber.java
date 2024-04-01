package com.fyp.saarthi;

public class waterAddTankerNumber {
    private String tankName;
    private String tankNumber;
    private String area;
    public waterAddTankerNumber(){}
    public waterAddTankerNumber(String tankName, String tankNumber,String area) {
        this.tankName = tankName;
        this.tankNumber = tankNumber;
        this.area = area;
    }

    public String getTankName() {
        return tankName;
    }

    public void setTankName(String tankName) {
        this.tankName = tankName;
    }

    public String getTankNumber() {
        return tankNumber;
    }

    public void setTankNumber(String tankNumber) {
        this.tankNumber = tankNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
