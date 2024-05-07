package com.example.qlkhachsan.controller;

import com.example.qlkhachsan.dto.RevenueStatistics;
import com.example.qlkhachsan.service.StatisticsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StatisticsController {
    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService){
        this.statisticsService=statisticsService;
    }

    @GetMapping("/thongkethang")
    public String getRevenueByMonth(@RequestParam(value = "year") String year, Model model, Principal principal) throws JsonProcessingException {
        String message = principal.getName();
        model.addAttribute("message1",message);
        List<RevenueStatistics> revenueStatisticsList = statisticsService.getRevenueByMonth(year);
        model.addAttribute("disYear", year);
        List<String> yearList = statisticsService.Year();
        model.addAttribute("yearList", yearList);
        Double[] revenueMonth = new Double[12];
        for (int i = 0; i < 12; i++) {
            revenueMonth[i] = Double.valueOf(0);
        }
        for (RevenueStatistics revenueStatistics : revenueStatisticsList) {
            revenueMonth[revenueStatistics.getMonth() - 1] = revenueStatistics.getRevenue();
        }
        ObjectMapper mapper = new ObjectMapper();
        String JsOn = mapper.writeValueAsString(revenueMonth);
        // truyền dữ liệu vào model attribute "data"
        model.addAttribute("revenueByMonth", JsOn);
        return "thongkethang";
    }

    @GetMapping("/thongkenam")
    public String getRevenueByYear(Model model, Principal principal) throws JsonProcessingException {
        String message = principal.getName();
        model.addAttribute("message1",message);
        List<RevenueStatistics> revenueStatisticsList = statisticsService.getRevenueByYear();
        List<Double> revenueYear = new ArrayList<>();
        List<Integer> year = new ArrayList<>();
        for (RevenueStatistics revenueStatistics : revenueStatisticsList){
            year.add(revenueStatistics.getMonth());
            revenueYear.add(revenueStatistics.getRevenue());
        }
        ObjectMapper mapper = new ObjectMapper();
        String revenueJSON = mapper.writeValueAsString(revenueYear);
        String yearJSON = mapper.writeValueAsString(year);
        model.addAttribute("years", yearJSON);
        model.addAttribute("revenueByYear", revenueJSON);
        return "thongkenam";
    }
}
