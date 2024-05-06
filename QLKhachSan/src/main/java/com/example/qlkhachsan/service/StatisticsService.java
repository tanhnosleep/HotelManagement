package com.example.qlkhachsan.service;

import com.example.qlkhachsan.dto.RevenueStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
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
        String sql="SELECT MONTH(check_out_date) AS thang," +
                   "SUM(payment) AS tong_doanh_thu " +
                   "FROM rental " +
                   "WHERE YEAR(check_out_date) = '"+year+"'" +
                   "GROUP BY MONTH(check_out_date)" +
                   "ORDER BY thang";
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();
        for (Object[] result : results){
            RevenueStatistics revenueStatistics = new RevenueStatistics();
            revenueStatistics.setMonth(Integer.parseInt(result[0].toString()));
            revenueStatistics.setRevenue((BigDecimal) result[1]);
            revenueStatisticsList.add(revenueStatistics);
        }
        return revenueStatisticsList;
    }

    public List<RevenueStatistics> getRevenueByYear(){
        List<RevenueStatistics> revenueStatisticsList = new ArrayList<>();
        String sql="SELECT YEAR(check_out_date) AS nam," +
                "SUM(payment) AS tong_doanh_thu " +
                "FROM rental " +
                "GROUP BY YEAR(check_out_date)" +
                "ORDER BY nam";
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();
        for (Object[] result : results){
            RevenueStatistics revenueStatistics = new RevenueStatistics();
            revenueStatistics.setMonth(Integer.parseInt(result[0].toString()));
            revenueStatistics.setRevenue((BigDecimal) result[1]);
            revenueStatisticsList.add(revenueStatistics);
        }
        return revenueStatisticsList;
    }
}
