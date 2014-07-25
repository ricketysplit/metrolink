package com.launchcode.metrolink;

/**
 * Created by r.harkins on 7/24/2014.
 */
public class Stop {
    private String stopName;
    private String stopDescription;
    private int stopID;
    private int order;
    private static int counter = 1;

    public Stop() {
        this.order = counter;
        counter++;
    }

    public void setStopName(String stopName){
        this.stopName = stopName;
    }

    public void setStopDescription(String stopDescription) {
        this.stopDescription = stopDescription;
    }

    public void setOrder(int order){
        this.order = order;
    }

    public void setStopID(int stopID) {
        this.stopID = stopID;
    }

    public String getStopName() {
        return stopName;
    }

    public String getStopDescription() {
        return stopDescription;
    }

    public int getOrder(){
        return order;
    }

    public int getStopID(){
        return stopID;
    }
}
