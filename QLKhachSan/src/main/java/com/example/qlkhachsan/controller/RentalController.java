package com.example.qlkhachsan.controller;

import com.example.qlkhachsan.model.Rental;
import com.example.qlkhachsan.service.RentalService;
import com.example.qlkhachsan.service.RoomService;
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
@RequestMapping("/quanlythue")
public class RentalController {

    private final RentalService rentalService;
    private final RoomService roomService;

    @Autowired
    public RentalController(RentalService rentalService, RoomService roomService){
        this.rentalService=rentalService;
        this.roomService=roomService;
    }

    @GetMapping
    public String showListRental(Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1",message);
        List<Rental> lr = rentalService.showListRental();
        model.addAttribute("Rentals",lr);
        return "quanlythue";
    }

    @GetMapping("/search")
    public String searchRental(@RequestParam("keyword") String keyword,Model model,Principal principal){
        String message = principal.getName();
        model.addAttribute("message1",message);
        keyword=keyword.trim();
        model.addAttribute("Rentals",rentalService.searchRental(keyword));
        return "quanlythue";
    }

    @GetMapping("/pay/{id}/{ud}")
    public String showEditRoom(@PathVariable(name = "id") Long id,
                               @PathVariable(name = "ud") Long ud,
                               Model model,Principal principal){
        rentalService.showEditRental(id,ud,model,principal);
        return "payment";
    }

    @GetMapping("/pay/{id}/{ud}/payment")
    public String showResult(@PathVariable(name = "id") Long id,
                             @PathVariable(name = "ud") Long ud,
                             Model model){
        rentalService.showResult(id, ud, model);
        Rental rental = (Rental) model.getAttribute("Rental");
        String formattedCheckOutDate = rentalService.formatDate(rental.getCheckOutDate());
        model.addAttribute("formattedCheckOutDate",formattedCheckOutDate);
        return "trathanhcongadmin";
    }


}