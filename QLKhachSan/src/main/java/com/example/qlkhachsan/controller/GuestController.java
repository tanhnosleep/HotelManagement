package com.example.qlkhachsan.controller;

import com.example.qlkhachsan.model.*;
import com.example.qlkhachsan.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dangky")
public class GuestController {

    private final GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService){
        this.guestService=guestService;
    }


    @GetMapping
    public  String showAddGuest(Model model) {
        model.addAttribute("AppUser", new AppUser());
        model.addAttribute("Guest", new Guest() );
        return "dangky";
    }

    @PostMapping
    public String addGuest(@ModelAttribute("AppUser") AppUser appUser,
                           @ModelAttribute("Guest") Guest guest, Model model){
        guestService.registerUser(appUser, guest);
        return "dangkythanhcong";
    }
}