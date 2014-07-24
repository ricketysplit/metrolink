package com.launchcode.metrolink;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by r.harkins on 7/24/2014.
 */
public class Metrolink {

    public static void main(String[] args){
        SqliteJDBCDao db = new SqliteJDBCDao();
        List<Stop> stops = db.getStopsAllStops();
        for(Stop stop : stops) {
            System.out.println(stop.getStopName());
        }
    }
}
