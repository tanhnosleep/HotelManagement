package com.example.qlkhachsan.service;

import com.example.qlkhachsan.repository.GuestRepository;
import com.example.qlkhachsan.model.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManageGuestService {

    private final GuestRepository guestRepository;

    @Autowired
    public ManageGuestService(GuestRepository guestRepository){
        this.guestRepository=guestRepository;
    }

    public List<Guest> showListGuest(){
        return guestRepository.showListGuest();
    }

    public List<Guest> searchGuest(String keyword){
        List<Guest> le = guestRepository.findAll();
        List<Guest> result  = new ArrayList<Guest>();
        for (Guest e : le) {
            Long x = e.getGuestId();
            if(x.toString().toLowerCase().contains((keyword).toLowerCase())||
                    e.getGuestName().toLowerCase().contains(keyword.toLowerCase()) ||
                    e.getBirth().toLowerCase().contains(keyword.toLowerCase())||
                    e.getAddress().toLowerCase().contains(keyword.toLowerCase())||
                    e.getEmail().toLowerCase().contains(keyword.toLowerCase())||
                    e.getIdCard().toLowerCase().contains(keyword.toLowerCase())||
                    e.getPhoneNumber().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(e);
            }
        }
        return result;
    }

    public Guest showEditGuest(Long id){
        Optional<Guest> optionalGuest = guestRepository.findById(id);
        return optionalGuest.orElse(null);
    }

    public void editGuest(Guest guest){
        guestRepository.save(guest);
    }

    public void deleteGuest(Long id){
        guestRepository.deleteById(id);
    }
}
