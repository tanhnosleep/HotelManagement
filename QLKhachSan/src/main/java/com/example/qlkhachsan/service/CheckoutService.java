package com.example.qlkhachsan.service;

import com.example.qlkhachsan.model.Guest;
import com.example.qlkhachsan.repository.RentalRepository;
import com.example.qlkhachsan.repository.RoomRepository;
import com.example.qlkhachsan.repository.UserRepository;
import com.example.qlkhachsan.model.AppUser;
import com.example.qlkhachsan.model.Rental;
import com.example.qlkhachsan.model.Room;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class CheckoutService {
    private final RoomRepository roomRepository;
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

    public CheckoutService(RoomRepository roomRepository,
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

    public AppUser findUserName(String username){
        return userRepository.findUserName(username);
    }

    public List<Long> findRoomByGuestID(Long id){
        return rentalRepository.findRoomByGuestID(id);
    }

    public List<Room> showListRoom(){
        return roomRepository.findAll();
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

    public List<Rental> findByGuestIDandRoomID(Long ud, Long id){
        return rentalRepository.findByGuestIDandRoomID(ud,id);
    }

    public void searchRoom(Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1",message);
        AppUser appUser = userRepository.findUserName(message);
        Guest guest = appUser.getGuest();
        List<Long> lr = rentalRepository.findRoomByGuestID(guest.getGuestId());
        List<Room> rooms = roomRepository.findAll();

        Set<Room> result = new HashSet<>();
        for (Long r : lr){
            Room room = roomRepository.findById(r).orElse(null);;
            if(room != null && room.getIsEmpty().equalsIgnoreCase("Đã SD")){
                result.add(room);
            }
        }
        model.addAttribute("Guest",guest);
        model.addAttribute("Rooms", result);
    }

    public void checkoutForm(Long id, Long ud, Model model, Principal principal){
        String message = principal.getName() ;
        model.addAttribute("message1", message);

        Room room = roomRepository.findById(id).orElse(null);;
        Rental rental = new Rental();
        List<Rental> lrent = rentalRepository.findByGuestIDandRoomID(ud,id);
        rental = lrent.get(lrent.size()-1);
        rental.setCheckOutDate(new Date());

        room.setIsEmpty("Trống");
        roomRepository.save(room);

        Long getDiff = rental.getCheckOutDate().getTime()-rental.getCheckInDate().getTime();
        Long getDaysDiff = TimeUnit.MILLISECONDS.toSeconds(getDiff);
        rental.setPayment((Math.ceil(Double.parseDouble(getDaysDiff.toString())/86400))*(room.getPriceDay()));
        rentalRepository.save(rental);

        model.addAttribute("Rental",rental);
    }

    public void showResult(Long id, Long ud, Model model){
        Room room = roomRepository.findById(id).orElse(null);
        Rental rental = new Rental();
        List<Rental> lrent = rentalRepository.findByGuestIDandRoomID(ud,id);
        rental = lrent.get(lrent.size()-1);
        model.addAttribute("Rental",rental);
    }
}
