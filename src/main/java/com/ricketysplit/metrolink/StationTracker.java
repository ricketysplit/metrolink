package com.ricketysplit.metrolink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by r.harkins on 7/24/2014.
 */
public class StationTracker {

    private SqliteJDBCDao db;
    private AppOutput appOutput;

    public StationTracker(SqliteJDBCDao db, AppOutput appOutput){
        this.db = db;
        this.appOutput = appOutput;
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
        int stopId = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));){
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

    public String getNextArrivaltimes(){
        Stop stop = getCurrentStation();
        String sql = "SELECT DISTINCT arrival_time FROM stop_times WHERE stop_id = '" + stop.getStopID()
                + "' AND arrival_time > (SELECT strftime('%H:%M:%S',datetime(strftime('%s','now'),'unixepoch','localtime'))) ORDER BY arrival_time LIMIT 1;";
        appOutput.print("Finding next arrival time for " + stop.getStopName());
        try (Connection connection = SqliteJDBCDao.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Stop> stops = new ArrayList<>();
            while(resultSet.next()) {
                System.out.println("The next train should arrive at " + formatStationTime(resultSet.getString("arrival_time")));
            }
            return "test";
        } catch (SQLException e) {
            throw new RuntimeException("Query failed");
        }
    }

    public String formatStationTime(String time){
        String formattedTime = time.substring(0,5);
        String hours = formattedTime.substring(0,2);
        int hour = Integer.parseInt(hours);
        if(hour>12){
            hour -= 12;
            formattedTime += " PM";
        } else {
            formattedTime += " AM";
        }
        return hour+formattedTime.substring(2,8);
    }
}
