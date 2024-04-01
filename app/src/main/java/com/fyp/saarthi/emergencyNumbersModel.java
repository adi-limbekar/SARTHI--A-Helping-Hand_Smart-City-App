package com.fyp.saarthi;

public class emergencyNumbersModel {
    private String emergencyName;
    private long emergencyNumber;
    private String item_id;

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    private emergencyNumbersModel() { }

    private emergencyNumbersModel(String emergencyName, long emergencyNumber,String item_id) {
        this.emergencyName = emergencyName;
        this.emergencyNumber = emergencyNumber;
        this.item_id=item_id;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName;
    }

    public long getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setEmergencyNumber(long emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }
}
