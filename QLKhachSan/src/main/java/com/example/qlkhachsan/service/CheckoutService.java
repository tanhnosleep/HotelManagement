package com.example.qlkhachsan.service;

import com.example.qlkhachsan.repository.RentalRepository;
import com.example.qlkhachsan.repository.RoomRepository;
import com.example.qlkhachsan.repository.UserRepository;
import com.example.qlkhachsan.model.AppUser;
import com.example.qlkhachsan.model.Rental;
import com.example.qlkhachsan.model.Room;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
