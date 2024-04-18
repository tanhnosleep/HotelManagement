package com.example.qlkhachsan.service;

import com.example.qlkhachsan.model.Employee;
import com.example.qlkhachsan.repository.GuestRepository;
import com.example.qlkhachsan.model.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        if (optionalGuest.isPresent()){
            Guest guest = optionalGuest.get();
            String birthDateStr = guest.getBirth();
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                //parse de doi String thanh Date
                //format de doi Date thanh String
                Date birthDate = inputDateFormat.parse(birthDateStr);
                String formattedBirthDate = outputDateFormat.format(birthDate);
                guest.setBirth(formattedBirthDate);
            }catch (ParseException e){
                throw new RuntimeException(e);
            }
        }
        return optionalGuest.orElse(null);
    }

    public void editGuest(Guest guest){
        String inputDateStr = guest.getBirth();
        String outputDateFormat = "dd/MM/yyyy";
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputDateFormatObj = new SimpleDateFormat(outputDateFormat);
        try {
            Date inputDate = inputDateFormat.parse(inputDateStr);
            String outputDateStr = outputDateFormatObj.format(inputDate);
            guest.setBirth(outputDateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        guestRepository.save(guest);
    }

    public void deleteGuest(Long id){
        guestRepository.deleteById(id);
    }
}
