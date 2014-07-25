package com.launchcode.metrolink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by r.harkins on 7/24/2014.
 */
public class StationTracker {

    private SqliteJDBCDao db;
    private AppOutput appOutput;

    public StationTracker(){
        db = new SqliteJDBCDao();
        appOutput = new AppOutput();
    }

    public List<Stop> getMetroLinkStations(){
        List<Stop> stops = db.getStopsLike("METROLINK STATION");
        for(Stop stop : stops) {
            appOutput.print(stop.getStopID() + ": " + stop.getStopName());
        }
        return stops;
    }

    public Stop getCurrentStation(){
        List<Stop> stops = getMetroLinkStations();
        appOutput.print("Enter the ID of your current station:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int stopId = 0;
        try {
            String id = br.readLine();
            stopId = Integer.parseInt(id);
        } catch (IOException e) {

        }
        for(Stop stop : stops) {
            if(stop.getStopID()==stopId) {
                return stop;
            }
        }
        return null;
    }
}
