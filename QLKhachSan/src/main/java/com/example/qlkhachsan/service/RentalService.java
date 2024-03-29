package com.example.qlkhachsan.service;

import com.example.qlkhachsan.Repository.RentalRepository;
import com.example.qlkhachsan.Repository.RoomRepository;
import com.example.qlkhachsan.model.Rental;
import com.example.qlkhachsan.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository, RoomRepository roomRepository){
        this.rentalRepository=rentalRepository;
        this.roomRepository=roomRepository;
    }

    public List<Rental> showListRental(){
        return rentalRepository.findAll();
    }

    public List<Rental> searchRental(String keyword){
        List<Rental> lr = rentalRepository.findAll();
        List<Rental> result  = new ArrayList<Rental>();
        for (Rental r : lr) {
            if((r.getRoom().getRoom_id().toString().contains(keyword))||
                    (r.getGuest().getGuestId().toString().contains(keyword))) {
                result.add(r);
            }
        }
        return result;
    }

    public List<Rental> findByGuestIDandRoomID(Long ud, Long id){
        return rentalRepository.findByGuestIDandRoomID(ud,id);
    }

    public void editRental(Rental rental){
        rentalRepository.save(rental);
    }

}
