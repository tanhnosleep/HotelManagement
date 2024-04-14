package com.example.qlkhachsan.controller;


import com.example.qlkhachsan.model.Rental;
import com.example.qlkhachsan.model.Room;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/quanlythue")
public class RentalController {

//    @Autowired
//    private RentalRepository renrepo;
//    @Autowired
//    private RoomRepository romrepo;
//    @Autowired
//    private GuestRepository guerepo;

    private final RentalService rentalService;
    private final RoomService roomService;

    @Autowired
    public RentalController(RentalService rentalService, RoomService roomService){
        this.rentalService=rentalService;
        this.roomService=roomService;
    }

//    @GetMapping
//    public String showListRoom(Model model, Principal principal) {
//        String message = principal.getName() ;
//        model.addAttribute("message1", message);
//        List<Rental> lr = (List<Rental>) renrepo.findAll();
//        model.addAttribute("Rentals",lr);
//        return "quanlythue";
//    }
    @GetMapping
    public String showListRental(Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1",message);
        List<Rental> lr = rentalService.showListRental();
        model.addAttribute("Rentals",lr);
        return "quanlythue";
    }

//    @GetMapping("/search")
//    public String searchRoom(@Param("keyword") String keyword, Model model, Principal principal) {
//        String message = principal.getName() ;
//        model.addAttribute("message1", message);
//        keyword = keyword.trim();
//        List<Rental> lr = renrepo.findAll();
//        List<Rental> result  = new ArrayList<Rental>();
//        for (Rental r : lr) {
//            if((r.getRoom().getRoom_id().toString().contains(keyword))||(r.getGuest().getGuestId().toString().contains(keyword))) {
//                result.add(r);
//            }
//        }
//        model.addAttribute("Rentals",result);
//        return "quanlythue";
//    }
    @GetMapping("/search")
    public String searchRental(@RequestParam("keyword") String keyword,Model model,Principal principal){
        String message = principal.getName();
        model.addAttribute("message1",message);
        keyword=keyword.trim();
        model.addAttribute("Rentals",rentalService.searchRental(keyword));
        return "quanlythue";
    }

//    @GetMapping("/pay/{id}/{ud}")
//    public  String showEditRoom(@PathVariable(name = "id") Long id,@PathVariable(name = "ud") Long ud , Model model, Principal principal) {
//        Room room = new Room();
//        Optional<Room> optRoom = romrepo.findById(id);
//        if(optRoom.isPresent()){
//            room=optRoom.get();
//        }
//        Rental rental = new Rental();
//        List<Rental> lrent = renrepo.findByGuestIDandRoomID(ud,id);
//        rental = lrent.get(lrent.size()-1);
//
//        rental.setCheckOutDate(new Date());
//        room.setIsEmpty("Trống");
//        romrepo.save(room);
////        renrepo.save(rental);
//        Long getDiff = rental.getCheckOutDate().getTime()-rental.getCheckInDate().getTime();
//        Long getDaysDiff = TimeUnit.MILLISECONDS.toSeconds(getDiff);
//        Double payment = (Math.ceil(Double.parseDouble(getDaysDiff.toString())/86400))*(room.getPriceDay());
//        renrepo.save(rental);
//        model.addAttribute("Rental",rental);
//        model.addAttribute("payment",payment);
//        return "payment";
//    }
    @GetMapping("/pay/{id}/{ud}")
    public String showEditRoom(@PathVariable(name = "id") Long id,
                               @PathVariable(name = "ud") Long ud,
                               Model model,Principal principal){
//        Room room = new Room();
//        Optional<Room> optRoom =roomService.showRoomById(id);
//        if(optRoom.isPresent()){
//            room=optRoom.get();
//        }
//        Rental rental = new Rental();
//        List<Rental> lrent = rentalService.findByGuestIDandRoomID(ud,id);
//        rental = lrent.get(lrent.size()-1);
//
//        rental.setCheckOutDate(new Date());
//        room.setIsEmpty("Trống");
//        roomService.editRoom(room);
////        renrepo.save(rental);
//        Long getDiff = rental.getCheckOutDate().getTime()-rental.getCheckInDate().getTime();
//        Long getDaysDiff = TimeUnit.MILLISECONDS.toSeconds(getDiff);
//        Double payment = (Math.ceil(Double.parseDouble(getDaysDiff.toString())/86400))*(room.getPriceDay());
//        rentalService.editRental(rental);
//        model.addAttribute("Rental",rental);
//        model.addAttribute("payment",payment);
        rentalService.showEditRental(id,ud,model,principal);
        return "payment";
    }

//    @GetMapping("/pay/{id}/{ud}/payment")
//    public  String showResult(@PathVariable(name = "id") Long id,@PathVariable(name = "ud") Long ud, Model model, Principal principal) {
//        Room room = new Room();
//        Optional<Room> optRoom = romrepo.findById(id);
//        if(optRoom.isPresent()){
//            room=optRoom.get();
//        }
//        Rental rental = new Rental();
//        List<Rental> lrent = renrepo.findByGuestIDandRoomID(ud,id);
//        rental = lrent.get(lrent.size()-1);
//
//        model.addAttribute("Rental",rental);
//        return "trathanhcongadmin";
//    }
    @GetMapping("/pay/{id}/{ud}/payment")
    public String showResult(@PathVariable(name = "id") Long id,
                             @PathVariable(name = "ud") Long ud,
                             Model model){
//        Room room = new Room();
//        Optional<Room> optRoom = roomService.showRoomById(id);
//        if(optRoom.isPresent()){
//            room=optRoom.get();
//        }
//        Rental rental = new Rental();
//        List<Rental> lrent = rentalService.findByGuestIDandRoomID(ud,id);
//        rental = lrent.get(lrent.size()-1);
//
//        model.addAttribute("Rental",rental);
        rentalService.showResult(id, ud, model);
        Rental rental = (Rental) model.getAttribute("Rental");
        String formattedCheckOutDate = rentalService.formatDate(rental.getCheckOutDate());
        model.addAttribute("formattedCheckOutDate",formattedCheckOutDate);
        return "trathanhcongadmin";
    }


}