package com.ricketysplit.metrolink;

import java.io.Serializable;

/**
 * Created by r.harkins on 7/28/2014.
 */
public class StopTimesPK implements Serializable{

    private String stop_id;
    private String trip_id;

    public StopTimesPK() {

    }

    public StopTimesPK(String stop_id, String trip_id) {
        this.stop_id = stop_id;
        this.trip_id = trip_id;
    }

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
}
