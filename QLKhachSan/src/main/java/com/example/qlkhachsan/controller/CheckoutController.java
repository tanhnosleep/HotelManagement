package com.example.qlkhachsan.controller;


import com.example.qlkhachsan.model.AppUser;
import com.example.qlkhachsan.model.Guest;
import com.example.qlkhachsan.model.Rental;
import com.example.qlkhachsan.model.Room;
import com.example.qlkhachsan.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;


    @Autowired
    public CheckoutController(CheckoutService checkoutService){
        this.checkoutService=checkoutService;
    }

    @GetMapping
    public String searchRoom(Model model, Principal principal){
        checkoutService.searchRoom(model,principal);
        return "checkout";
    }

    @GetMapping("/add/{id}/{ud}")
    public String checkoutForm(@PathVariable(name = "id") Long id,
                              @PathVariable(name = "ud") Long ud ,
                              Model model, Principal principal) {
        checkoutService.checkoutForm(id,ud,model,principal);
        return "paymentkh";
    }

    @GetMapping("/add/{id}/{ud}/payment")
    public  String showResult(@PathVariable(name = "id") Long id,
                              @PathVariable(name = "ud") Long ud ,
                              Model model) {
        checkoutService.showResult(id, ud, model);
        Rental rental = (Rental) model.getAttribute("Rental");
        String formattedCheckOutDate = checkoutService.formatDate(rental.getCheckOutDate());
        model.addAttribute("formattedCheckOutDate",formattedCheckOutDate);
        return "trathanhcong";
    }
}