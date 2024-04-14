package com.example.qlkhachsan.service;

import com.example.qlkhachsan.model.Guest;
import com.example.qlkhachsan.repository.RentalRepository;
import com.example.qlkhachsan.repository.RoomRepository;
import com.example.qlkhachsan.repository.UserRepository;
import com.example.qlkhachsan.model.AppUser;
import com.example.qlkhachsan.model.Rental;
import com.example.qlkhachsan.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CheckinService {

    private final RoomRepository roomRepository;
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

    @Autowired
    public CheckinService(RoomRepository roomRepository,
                          RentalRepository rentalRepository,
                          UserRepository userRepository){
        this.roomRepository=roomRepository;
        this.rentalRepository=rentalRepository;
        this.userRepository=userRepository;
    }

    public String formatDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateFormat.format(date);
    }

    public List<Room> showListRoom(){
        return roomRepository.findAll();
    }

    public List<Room> searchRoom(String keyword){
        if("All".equalsIgnoreCase(keyword.trim())){
            return roomRepository.findAll();
        }
        List<Room> roomList = roomRepository.findAll();
        List<Room> result  = new ArrayList<Room>();
        for (Room r : roomList) {
            if(r.getType().equalsIgnoreCase(keyword)) {
                result.add(r);
            }
        }
        return result;
    }

    public AppUser findUserName(String username){
        return userRepository.findUserName(username);
    }

    public Room findRoomById(Long id){
        return roomRepository.findById(id).orElse(null);
    }

    public void saveRoom(Room room){
        roomRepository.save(room);
    }

    public void saveRental(Rental rental){
        rentalRepository.save(rental);
    }

    public Boolean showRoomAndGuest(Long id, Model model, Principal principal){
        boolean check=true;
        String message = principal.getName();
        model.addAttribute("message1", message);
        AppUser appUser = userRepository.findUserName(message);
        Guest guest = appUser.getGuest();
        Room room = roomRepository.findById(id).orElse(null);
        if(room.getIsEmpty().equalsIgnoreCase("Đã SD")){
            check=false;
        }
        model.addAttribute("Room",room);
        model.addAttribute("Guest",guest);
        return  check;
    }

    public void checkinForm(Long id, Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1",message);
        AppUser appUser = userRepository.findUserName(message);
        Guest guest = appUser.getGuest();
        Room room = roomRepository.findById(id).orElse(null);;
        room.setIsEmpty("Đã SD");
        roomRepository.save(room);
        Rental rental = new Rental();
        rental.setRoom(room);
        rental.setGuest(guest);
        rental.setCheckInDate(new Date());
        rentalRepository.save(rental);
        model.addAttribute("Rental",rental);
    }
}
