package com.launchcode.metrolink;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by r.harkins on 7/24/2014.
 */
public class SqliteJDBCDao {
    private AppOutput appOutput = new AppOutput();
    public static final String JDBC_SQLITE_METROLINK_DB = "jdbc:sqlite:metrolink.db";
    public static final String ORG_SQLITE_JDBC = "org.sqlite.JDBC";

    public List<Stop> getStopsAllStops(){
        appOutput.print("Fetching metrolink stations...");
        try (Connection connection = getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM stops");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Stop> stops = new ArrayList<>();
            while(resultSet.next()) {
                Stop stop = new Stop();
                stop.setStopName(resultSet.getString("stop_name"));
                stop.setStopDescription(resultSet.getString("stop_desc"));
                stops.add(stop);
            }
            return stops;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving stops");
        }
    }

    public String getNextArrivaltimes(Stop stop){
        String sql = "SELECT DISTINCT arrival_time FROM stop_times WHERE stop_id = '" + stop.getStopID()
                + "' AND arrival_time > (SELECT strftime('%H:%M:%S',datetime(strftime('%s','now'),'unixepoch','localtime'))) ORDER BY arrival_time LIMIT 1;";
        appOutput.print("Finding next arrival time for " + stop.getStopName());
        try (Connection connection = getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Stop> stops = new ArrayList<>();
            while(resultSet.next()) {
                System.out.println(resultSet.getString("arrival_time"));
            }
            return "test";
        } catch (SQLException e) {
            throw new RuntimeException("Query failed");
        }
    }

    public List<Stop> getStopsLike(String like){
        appOutput.print("Fetching metrolink stations like " + like + "...");
        try (Connection connection = getConnection();) {
            String sql = "SELECT * FROM stops WHERE stop_name LIKE '%" + like + "%' ORDER BY stop_name";
            System.out.println(sql);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Stop> stops = new ArrayList<>();
            while(resultSet.next()) {
                Stop stop = new Stop();
                stop.setStopName(resultSet.getString("stop_name"));
                stop.setStopDescription(resultSet.getString("stop_desc"));
                stop.setStopID(Integer.parseInt(resultSet.getString("stop_id")));
                stops.add(stop);
            }
            return stops;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving stops");
        }
    }

    private static Connection getConnection() throws SQLException {
        try {
            Class.forName(ORG_SQLITE_JDBC);
        } catch (ClassNotFoundException e){
            throw new RuntimeException("Unable to find class for loading the database", e);
        }
        return DriverManager.getConnection(JDBC_SQLITE_METROLINK_DB);
    }

    private void setAppOutput(AppOutput appOutput) {
        this.appOutput = appOutput;
    }
}
