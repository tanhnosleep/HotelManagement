package com.example.qlkhachsan.controller;


import com.example.qlkhachsan.model.Guest;
import com.example.qlkhachsan.service.ManageGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/khachhang")
public class ManageGuestController {



    private final ManageGuestService manageGuestService;

    @Autowired
    public ManageGuestController(ManageGuestService manageGuestService){
        this.manageGuestService=manageGuestService;
    }



    @RequestMapping
    public String showListGuest(Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1",message);
        List<Guest> lg = manageGuestService.showListGuest();
        model.addAttribute("Guests",lg);
        return "quanlykhachhang";
    }



    @GetMapping("/search")
    public String searchGuest(@RequestParam("keyword") String keyword, Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1",message);
        keyword=keyword.trim();
        model.addAttribute("Guests",manageGuestService.searchGuest(keyword));
        return "quanlykhachhang";
    }


    @GetMapping("/edit/{id}")
    public String showEditGuest(@PathVariable(name = "id") Long id, Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1", message);
        model.addAttribute("Guest", manageGuestService.showEditGuest(id));
        return "suakhachhang";
    }


    @PostMapping("/edit/{id}")
    public String editGuest(Guest guest){
        manageGuestService.editGuest(guest);
        return "redirect:/khachhang";
    }


    @GetMapping("/delete/{id}")
    public String deleteGuest(@PathVariable(name = "id") Long id, Model model){
        try{
            manageGuestService.deleteGuest(id);
            return "redirect:/khachhang";
        }catch (Exception e){
            String message = "Khách hàng đang thuê phòng";
            model.addAttribute("message",message);
            return "403Page";
        }
    }

}
