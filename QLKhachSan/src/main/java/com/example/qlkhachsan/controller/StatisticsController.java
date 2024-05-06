package com.example.qlkhachsan.controller;

import com.example.qlkhachsan.dto.RevenueStatistics;
import com.example.qlkhachsan.service.StatisticsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.security.Principal;
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
        model.addAttribute("RevenueMonth", revenueStatisticsList);
        model.addAttribute("disYear", year);
        List<String> yearList = statisticsService.Year();
        model.addAttribute("yearList", yearList);
        Double[] dataSales = new Double[12];
        for (int i = 0; i < 12; i++) {
            dataSales[i] = Double.valueOf(0);
        }
        for (RevenueStatistics sales : revenueStatisticsList) {
            dataSales[sales.getMonth() - 1] = sales.getRevenue();
        }
        ObjectMapper mapper = new ObjectMapper();
        String JsOn = mapper.writeValueAsString(dataSales);
        // truyền dữ liệu vào model attribute "data"
        model.addAttribute("dataSales", JsOn);
        return "thongkethang";
    }

//    @GetMapping("/thongkenam")
//    public String getRevenueByYear(Model model, Principal principal){
//        String message = principal.getName();
//        model.addAttribute("message1",message);
//        List<RevenueStatistics> revenueStatisticsList = statisticsService.getRevenueByYear();
//        model.addAttribute("RevenueYear",revenueStatisticsList);
//        return "thongkenam";
//    }
}
