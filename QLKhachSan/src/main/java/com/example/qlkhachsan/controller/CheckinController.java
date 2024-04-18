package com.example.qlkhachsan.controller;


import com.example.qlkhachsan.model.*;
import com.example.qlkhachsan.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/checkin")
public class CheckinController {

    private final CheckinService checkinService;


    @Autowired
    public CheckinController(CheckinService checkinService){
        this.checkinService=checkinService;
    }


    @GetMapping
    public String showListRoom(Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1", message);
        List<Room> lr = checkinService.showListRoom();
        model.addAttribute("Rooms",lr);
        model.addAttribute("Check1","All");
        return "checkin";
    }

    @GetMapping("/search")
    public String searchRoom(@RequestParam("keyword") String keyword, Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1", message);
        List<Room> rooms = checkinService.searchRoom(keyword);
        model.addAttribute("check1",keyword);
        model.addAttribute("Rooms",rooms);
        return "checkin";
    }

    @GetMapping("/add/{id}")
    public String showRoomAndGuest(@PathVariable("id") Long id, Model model, Principal principal){
        if (!checkinService.showRoomAndGuest(id, model, principal)){
            return "datloi";
        }
        else {
            return "formdat";
        }
    }

    @GetMapping("/add/{id}/submit")
    public String checkinForm(@PathVariable(name = "id") Long id, Model model, Principal principal){
        checkinService.checkinForm(id, model, principal);
        Rental rental = (Rental) model.getAttribute("Rental");
        String formattedCheckInDate = checkinService.formatDate(rental.getCheckInDate());
        model.addAttribute("formattedCheckInDate",formattedCheckInDate);
        return "datthanhcong";
    }
}
