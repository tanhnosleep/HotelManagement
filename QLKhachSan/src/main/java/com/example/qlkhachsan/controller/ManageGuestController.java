package com.example.qlkhachsan.controller;


import com.example.qlkhachsan.model.Guest;
import com.example.qlkhachsan.service.ManageGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

//@NoArgsConstructor
@Controller
@RequestMapping("/khachhang")
public class ManageGuestController {

//    @Autowired
//    private GuestRepository repo;

    private final ManageGuestService manageGuestService;

    @Autowired
    public ManageGuestController(ManageGuestService manageGuestService){
        this.manageGuestService=manageGuestService;
    }


//    @RequestMapping()
//    public String showListEmployee(Model model, Principal principal) {
//        String message = principal.getName() ;
//        model.addAttribute("message1", message);
//        List<Guest> lg = (List<Guest>) repo.findAll();
//        model.addAttribute("Guests",lg);
//        return "quanlykhachhang";
//    }
    @RequestMapping
    public String showListGuest(Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1",message);
        List<Guest> lg = manageGuestService.showListGuest();
        model.addAttribute("Guests",lg);
        return "quanlykhachhang";
    }


//    @GetMapping("/search")
//    public String searchGuest(@Param("keyword") String keyword, Model model, Principal principal) {
//        String message = principal.getName() ;
//        model.addAttribute("message1", message);
//        keyword = keyword.trim();
//        List<Guest> le = repo.findAll();
//        List<Guest> result  = new ArrayList<Guest>();
//        for (Guest e : le) {
//            Long x = e.getGuestId();
//            if(x.toString().toLowerCase().contains((keyword).toLowerCase())||
//                    e.getGuestName().toLowerCase().contains(keyword.toLowerCase()) ||
//                    e.getBirth().toLowerCase().contains(keyword.toLowerCase())||
//                    e.getAddress().toLowerCase().contains(keyword.toLowerCase())||
//                    e.getEmail().toLowerCase().contains(keyword.toLowerCase())||
//                    e.getIdCard().toLowerCase().contains(keyword.toLowerCase())||
//                    e.getPhoneNumber().toLowerCase().contains(keyword.toLowerCase())) {
//                result.add(e);
//            }
//        }
//        model.addAttribute("Guests",result);
//        return "quanlykhachhang";
//    }
    @GetMapping("/search")
    public String searchGuest(@RequestParam("keyword") String keyword, Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1",message);
        keyword=keyword.trim();
        model.addAttribute("Guests",manageGuestService.searchGuest(keyword));
        return "quanlykhachhang";
    }

//    @GetMapping("/edit/{id}")
//    public  String showEditGuest(@PathVariable(name = "id") Long id, Model model, Principal principal) {
//        String message = principal.getName() ;
//        model.addAttribute("message1", message);
//        Guest guest = new Guest();
//        Optional<Guest> optGuest = repo.findById(id);
//        if(optGuest.isPresent()){
//            guest = optGuest.get();
//        }
//        model.addAttribute("Guest", guest );
//        return "suakhachhang";
//    }
    @GetMapping("/edit/{id}")
    public String showEditGuest(@PathVariable(name = "id") Long id, Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1", message);
        model.addAttribute("Guest", manageGuestService.showEditGuest(id));
        return "suakhachhang";
    }

//    @PostMapping("/edit/{id}")
//    public  String editGuest(@PathVariable(name = "id") Long id,Guest guest) {
//        Optional<Guest> optGuest = repo.findById(id);
//        if( optGuest.isPresent()){
//            Guest guestExist =  optGuest.get();
//            guestExist.setIdCard(guest.getIdCard());
//            guestExist.setGuestName(guest.getGuestName());
//            guestExist.setAddress((guest.getAddress()));
//            guestExist.setBirth((guest.getBirth()));
//            guestExist.setEmail(guest.getEmail());
//            guestExist.setPhoneNumber(guest.getPhoneNumber());
//            repo.save(guestExist);
//        }
//        return "redirect:/khachhang";
//    }
    @PostMapping("/edit/{id}")
    public String editGuest(Guest guest){
        manageGuestService.editGuest(guest);
        return "redirect:/khachhang";
    }

//    @GetMapping("/delete/{id}")
//    public  String deleteGuest(Model model,@PathVariable(name = "id") Long id) {
//
//        try{
//            repo.deleteById(id);
//            return "redirect:/khachhang";
//        }catch (Exception e){
//            String message = "Khách hàng đang thuê phòng" ;
//            model.addAttribute("message", message);
//            return "403Page";
//        }
//    }
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
