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
import java.util.Date;
import java.util.List;

//@NoArgsConstructor
//@AllArgsConstructor
@Controller
@RequestMapping("/checkin")
public class CheckinController {

    private final CheckinService checkinService;

//    @Autowired
//    private RoomRepository roomRepo;
//    @Autowired
//    private RentalRepository renRepo;
//    @Autowired
//    private GuestRepository gueRepo;
//    @Autowired
//    private UserRepository useRepo;

    @Autowired
    public CheckinController(CheckinService checkinService){
        this.checkinService=checkinService;
    }

//    @GetMapping
//    public String showListRoom(Model model, Principal principal) {
//        String message = principal.getName() ;
//        model.addAttribute("message1", message);
//
//        List<Room> lr = (List<Room>) roomRepo.findAll();
//        model.addAttribute("Rooms",lr);
//        model.addAttribute("Check1","All");
//        return "checkin";
//    }
    @GetMapping
    public String showListRoom(Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1", message);
        List<Room> lr = checkinService.showListRoom();
        model.addAttribute("Rooms",lr);
        model.addAttribute("Check1","All");
        return "checkin";
    }


//    @GetMapping("/search")
//    public String searchRoom(@Param("keyword") String keyword, Model model, Principal principal) {
//        String message = principal.getName() ;
//        model.addAttribute("message1", message);
//        List<Room> lr = roomRepo.findAll();
//        keyword = keyword.trim();
//        if(keyword.equalsIgnoreCase("All")) {
//            model.addAttribute("check1",keyword);
//            model.addAttribute("Rooms",lr);
//            return "checkin";
//        }
//
//        List<Room> result  = new ArrayList<Room>();
//        for (Room r : lr) {
//            if(r.getType().equalsIgnoreCase(keyword)) {
//                result.add(r);
//            }
//        }
//        model.addAttribute("check1",keyword);
//        model.addAttribute("Rooms",result);
//        return "checkin";
//    }
    @GetMapping("/search")
    public String searchRoom(@RequestParam("keyword") String keyword, Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1", message);
        List<Room> rooms = checkinService.searchRoom(keyword);
        model.addAttribute("check1",keyword);
        model.addAttribute("Rooms",rooms);
        return "checkin";
    }

//    @GetMapping("/add/{id}")
//    public  String showListGuest(@PathVariable(name = "id") Long id, Model model, Principal principal) {
//        String message = principal.getName() ;
//        model.addAttribute("message1", message);
//        AppUser au = useRepo.findUserName(message);
//        Guest guest = au.getGuest();
//        Room room = new Room();
//        Optional<Room> optRoom = roomRepo.findById(id);
//        if(optRoom.isPresent()){
//            room = optRoom.get();
//        }
////        Rental rental = new Rental();
////        rental.setRoom(room);
////        rental.setCheckInDate(new Date());
////        model.addAttribute("Rental",rental);
//        if(room.getIsEmpty().equalsIgnoreCase("Đã SD")) {
//            return "datloi";
//        }
//        model.addAttribute("Room", room );
//        model.addAttribute("Guest",guest);
//        return "formdat";
//    }
    @GetMapping("/add/{id}")
    public String showRoomAndGuest(@PathVariable("id") Long id, Model model, Principal principal){
//        boolean check=false;
//        String message = principal.getName();
//        model.addAttribute("message1", message);
//        AppUser appUser = checkinService.findUserName(message);
//        Guest guest = appUser.getGuest();
//        Room room = checkinService.findRoomById(id);
//        if(room.getIsEmpty().equalsIgnoreCase("Đã SD")){
//            return "datloi";
//        }
//        model.addAttribute("Room",room);
//        model.addAttribute("Guest",guest);
        if (!checkinService.showRoomAndGuest(id, model, principal)){
            return "datloi";
        }
        else {
            return "formdat";
        }
    }

//    @GetMapping("/add/{id}/submit")
//    public String checkinForm(@PathVariable(name = "id") Long id,Model model,Principal principal) {
//        String message = principal.getName() ;
//        model.addAttribute("message1", message);
//        AppUser au = useRepo.findUserName(message);
//        Guest guest = au.getGuest();
//        Room room = new Room();
//        Optional<Room> optRoom = roomRepo.findById(id);
//        if(optRoom.isPresent()){
//            room = optRoom.get();
//            room.setIsEmpty("Đã SD");
//            roomRepo.save(room);
//        }
//
//        Rental rental = new Rental();
//        rental.setRoom(room);
//        rental.setGuest(guest);
//        rental.setCheckInDate(new Date());
//        renRepo.save(rental);
//        model.addAttribute("Rental",rental);
//
//        return "datthanhcong";
//    }
    @GetMapping("/add/{id}/submit")
    public String checkinForm(@PathVariable(name = "id") Long id, Model model, Principal principal){
//        String message = principal.getName();
//        model.addAttribute("message1",message);
//        AppUser appUser = checkinService.findUserName(message);
//        Guest guest = appUser.getGuest();
//        Room room = checkinService.findRoomById(id);
//        room.setIsEmpty("Đã SD");
//        checkinService.saveRoom(room);
//        Rental rental = new Rental();
//        rental.setRoom(room);
//        rental.setGuest(guest);
//        rental.setCheckInDate(new Date());
//        checkinService.saveRental(rental);
//        model.addAttribute("Rental",rental);
        checkinService.checkinForm(id, model, principal);
        Rental rental = (Rental) model.getAttribute("Rental");
        String formattedCheckInDate = checkinService.formatDate(rental.getCheckInDate());
        model.addAttribute("formattedCheckInDate",formattedCheckInDate);
        return "datthanhcong";
    }
}
