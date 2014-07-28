package com.ricketysplit.metrolink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by r.harkins on 7/24/2014.
 */
@Component
public class Metrolink {
    @Autowired
    private StationTracker stationTracker;
    @Autowired
    private AppOutput appOutput;

    public static void main(String[] args){

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        Metrolink metrolink = (Metrolink) context.getBean("metrolink");
        metrolink.stationTracker.getNextArrivaltimes();
    }

    private void run(){
        List<Stop> stopsAllStops = stationTracker.getMetroLinkStations();
        for(Stop stop : stopsAllStops) {
            appOutput.print(stop.getStopName());
        }
    }
    public Metrolink(){

    }

    public Metrolink(StationTracker stationTracker){
        this.stationTracker = stationTracker;
    }
}
