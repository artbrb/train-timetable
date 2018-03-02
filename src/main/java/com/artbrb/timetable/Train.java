package com.artbrb.timetable;

import java.time.Instant;

public class Train {
    private String name;
    private Instant departureTime;
    private String arrivalStation;

    public Train (String name, Instant departureTime, String arrivalStation) {
        this.name = name;
        this.departureTime = departureTime;
        this.arrivalStation = arrivalStation;
    }


    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Instant departureTime) {
        this.departureTime = departureTime;
    }
}
