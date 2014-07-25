package com.ricketysplit.metrolink;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by r.harkins on 7/24/2014.
 */
public class Metrolink {
    private StationTracker stationTracker;

    public static void main(String[] args){

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        Metrolink metrolink = (Metrolink) context.getBean("metrolink");
        metrolink.stationTracker.getNextArrivaltimes();

    }

    public Metrolink(StationTracker stationTracker){
        this.stationTracker = stationTracker;
    }
}
