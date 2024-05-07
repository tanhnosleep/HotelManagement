package com.example.qlkhachsan.service;

import com.example.qlkhachsan.dto.RevenueStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsService {
    private final EntityManager entityManager;

    @Autowired
    public StatisticsService(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    public List<RevenueStatistics> getRevenueByMonth(String year){
        List<RevenueStatistics> revenueStatisticsList = new ArrayList<>();
        String sql="SELECT MONTH(check_out_date) AS month," +
                   "SUM(payment) AS revenue " +
                   "FROM rental " +
                   "WHERE YEAR(check_out_date) = '"+year+"'" +
                   "GROUP BY MONTH(check_out_date)" +
                   "ORDER BY month";
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();
        for (Object[] result : results){
            RevenueStatistics revenueStatistics = new RevenueStatistics();
            revenueStatistics.setMonth(Integer.parseInt(result[0].toString()));
            revenueStatistics.setRevenue((Double) result[1]);
            revenueStatisticsList.add(revenueStatistics);
        }
        return revenueStatisticsList;
    }

    public List<String> Year(){
        List<String> year;
        String sql = "select distinct year(check_out_date) as year " +
                     "FROM rental " +
                     "WHERE check_out_date IS NOT NULL " +
                     "ORDER BY year ASC";
        Query query = entityManager.createNativeQuery(sql);
        year = query.getResultList();
        return year;
    }

    public List<RevenueStatistics> getRevenueByYear(){
        List<RevenueStatistics> revenueStatisticsList = new ArrayList<>();
        String sql="SELECT YEAR(r.check_out_date) AS year," +
                "SUM(r.payment) AS revenue " +
                "FROM rental r " +
                "WHERE r.check_out_date IS NOT NULL " +
                "GROUP BY YEAR(r.check_out_date) " +
                "ORDER BY year";
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();
        for (Object[] result : results){
            RevenueStatistics revenueStatistics = new RevenueStatistics();
            revenueStatistics.setMonth(Integer.parseInt(result[0].toString()));
            revenueStatistics.setRevenue((Double) result[1]);
            revenueStatisticsList.add(revenueStatistics);
        }
        return revenueStatisticsList;
    }
}
