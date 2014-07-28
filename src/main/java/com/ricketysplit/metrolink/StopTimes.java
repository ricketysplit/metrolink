package com.ricketysplit.metrolink;

import javax.persistence.*;

/**
 * Created by r.harkins on 7/28/2014.
 */
@Entity
@Table(name = "stop_times")
@IdClass(value = StopTimesPK.class)
public class StopTimes {

    @Id
    private String stop_id;
    @Id
    private String trip_id;

    @Column(name = "arrival_time")
    private String arrivalTime;

    public String getStop_id() {
        return stop_id;
    }

    public void setStop_id(String stop_id) {
        this.stop_id = stop_id;
    }

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
