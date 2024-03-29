package com.example.qlkhachsan.controller;

import com.example.qlkhachsan.Repository.GuestRepository;
import com.example.qlkhachsan.Repository.RoleRepository;
import com.example.qlkhachsan.Repository.UserRepository;
import com.example.qlkhachsan.Repository.UserRollRepository;
import com.example.qlkhachsan.model.*;
import com.example.qlkhachsan.service.GuestService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/dangky")
public class GuestController {

    private final GuestService guestService;

//    @Autowired
//    private GuestRepository grepo;
//    private UserRepository urepo;
//    private UserRollRepository urrepo;
//    private RoleRepository arrepo;

    @GetMapping
    public  String showAddGuest(Model model) {
//        String message = principal.getName() ;
//        model.addAttribute("message1", message);
        model.addAttribute("AppUser", new AppUser());
        model.addAttribute("Guest", new Guest() );
        return "dangky";
    }

//    @PostMapping()
//    public  String addGuest(AppUser appUser,Guest guest ,Model model){
//        appUser.setGuest(guest);
//        String pass = appUser.getEncrytedPassword();
//        appUser.setEncrytedPassword(BCrypt.hashpw(pass, BCrypt.gensalt(12)));
//        grepo.save(guest);
//        urepo.save(appUser);
//        AppRole ar = new AppRole();
//        Optional<AppRole> optAppRole = arrepo.findById(2L);
//        if(optAppRole.isPresent()){
//            ar = optAppRole.get();
//        }
//        UserRole ur = new UserRole(appUser,ar);
//        urrepo.save(ur);
//        model.addAttribute("AppUser",appUser);
//        return "dangkythanhcong";
//    }
    @PostMapping
    public String addGuest(@ModelAttribute("AppUser") AppUser appUser, @ModelAttribute("Guest") Guest guest, Model model){
        guestService.registerUser(appUser, guest);
        return "dangkythanhcong";
    }
}