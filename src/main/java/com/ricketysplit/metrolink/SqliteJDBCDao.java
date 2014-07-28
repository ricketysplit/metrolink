package com.ricketysplit.metrolink;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by r.harkins on 7/24/2014.
 */
@Component
public class SqliteJDBCDao {
    @Autowired
    private AppOutput appOutput;
    @Autowired
    private SessionFactory sessionFactoryBean;

    public SqliteJDBCDao(){

    }

    public SqliteJDBCDao(AppOutput appOutput){
        this.appOutput = appOutput;
    }

    public List<Stop> getStopsAllStops(){
        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria criteria = sessionFactoryBean.getCurrentSession().createCriteria(Stop.class);
        List<Stop> list = criteria.list();
        sessionFactoryBean.getCurrentSession().getTransaction().commit();
        return list;
    }

    public List<Stop> getStopsLike(String like){
        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria criteria = sessionFactoryBean.getCurrentSession().createCriteria(Stop.class);
        criteria.add(Restrictions.like("stopName", "%"+like+"%"));
        List<Stop> list = criteria.list();
        sessionFactoryBean.getCurrentSession().getTransaction().commit();
        return list;
    }

    public StopTimes getNextArrivalTime(Stop stop){
        sessionFactoryBean.getCurrentSession().beginTransaction();
        String sql = "SELECT arrival_time, stop_id, trip_id FROM stop_times a WHERE a.stop_id = :stoptimes AND arrival_time > (SELECT strftime('%H:%M:%S',datetime(strftime('%s','now'),'unixepoch','localtime'))) ORDER BY arrival_time LIMIT 1;";
        Query query = sessionFactoryBean.getCurrentSession().createSQLQuery(sql).addEntity(StopTimes.class).setParameter("stoptimes", stop.getStopID());
        /*sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria criteria = sessionFactoryBean.getCurrentSession().createCriteria(StopTimes.class);
        criteria.add(Restrictions.eq("stop_id", "" + stop.getStopID()));
        List<StopTimes> arrivalTimes = criteria.list();
        sessionFactoryBean.getCurrentSession().getTransaction().commit();
        for(StopTimes a : arrivalTimes) {
            System.out.println(a.getArrivalTime());
        }
        return arrivalTimes.get(0);*/
        List<StopTimes> arrivalTimes = query.list();
        sessionFactoryBean.getCurrentSession().getTransaction().commit();
        return arrivalTimes.get(0);
    }

    private void setAppOutput(AppOutput appOutput) {
        this.appOutput = appOutput;
    }
}
