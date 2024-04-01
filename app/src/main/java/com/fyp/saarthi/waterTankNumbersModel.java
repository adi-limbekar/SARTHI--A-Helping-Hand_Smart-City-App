package com.fyp.saarthi;

public class waterTankNumbersModel {
    private String tankName;
    private String tankNumber;
    private String area;
    private String item_id;
    public waterTankNumbersModel(){}
    public waterTankNumbersModel(String tankName,String tankNumber,String area,String item_id) {
        this.tankName = tankName;
        this.tankNumber = tankNumber;
        this.area = area;
        this.item_id=item_id;
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

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

}
