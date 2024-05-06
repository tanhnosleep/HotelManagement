package com.example.qlkhachsan.controller;

import com.example.qlkhachsan.dto.RevenueStatistics;
import com.example.qlkhachsan.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class StatisticsController {
    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService){
        this.statisticsService=statisticsService;
    }

    @GetMapping("/thongkethang/{year}")
    public String getRevenueByMonth(@PathVariable(value = "year") String year, Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1",message);
        List<RevenueStatistics> revenueStatisticsList = statisticsService.getRevenueByMonth(year);
        model.addAttribute("RevenueMonth", revenueStatisticsList);
        return "thongkethang";
    }

    @GetMapping("/thongkenam")
    public String getRevenueByYear(Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1",message);
        List<RevenueStatistics> revenueStatisticsList = statisticsService.getRevenueByYear();
        model.addAttribute("RevenueYear",revenueStatisticsList);
        return "thongkenam";
    }
}
