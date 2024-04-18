package com.example.qlkhachsan.controller;

import com.example.qlkhachsan.model.Room;
import com.example.qlkhachsan.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/quanlyphong")


public class RoomController {
    @Autowired
    private final RoomService roomService;

    public RoomController(RoomService roomService){
        this.roomService=roomService;
    }

    @GetMapping
    public String showListRoom(Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1",message);
        List<Room> lr = roomService.showListRoom();
        model.addAttribute("Rooms",lr);
        return "quanlyphong";
    }

    @GetMapping("/search")
    public String searchRoom(@RequestParam("keyword") String keyword, Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1", message);
        keyword = keyword.trim();
        model.addAttribute("Rooms",roomService.searchRoom(keyword));
        return "quanlyphong";
    }

    @GetMapping("/add")
    public  String showAddRoom(Model model, Principal principal) {
        String message = principal.getName() ;
        model.addAttribute("message1", message);
        model.addAttribute("Room", new Room() );
        return "themphong";
    }

    @PostMapping("/add")
    public String addRoom(Room room){
        roomService.addRoom(room);
        return "redirect:/quanlyphong";
    }

    @GetMapping("/edit/{id}")
    public String showEditRoom(@PathVariable(name = "id") Long id, Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1", message);
        model.addAttribute("Room",roomService.showEditRoom(id));
        return "suaphong";
    }

    @PostMapping("/edit/{id}")
    public String editRoom(Room room){
        roomService.editRoom(room);
        return "redirect:/quanlyphong";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable(name = "id") Long id, Model model){
        try {
            roomService.deleteRoom(id);
            return "redirect:/quanlyphong";
        }catch (Exception e){
            String message = "Phòng đang được sử dụng";
            model.addAttribute("message",message);
            return "403Page";
        }
    }

}