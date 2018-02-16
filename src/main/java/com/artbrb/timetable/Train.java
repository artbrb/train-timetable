package com.artbrb.timetable;

public class Train {
    private String name;
    private String departureTime;
    private String arrivalStation;

    public Train (String name, String departureTime, String arrivalStation) {
        this.name = name;
        this.departureTime = departureTime;
        this.arrivalStation = arrivalStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }
    public void setArrivalStation(java.lang.String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
}
