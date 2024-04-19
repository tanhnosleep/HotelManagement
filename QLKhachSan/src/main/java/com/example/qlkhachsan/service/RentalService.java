package com.example.qlkhachsan.service;

import com.example.qlkhachsan.model.Room;
import com.example.qlkhachsan.repository.RentalRepository;
import com.example.qlkhachsan.repository.RoomRepository;
import com.example.qlkhachsan.model.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;
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

    public String formatDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if(date == null){
            return null;
        }
        return dateFormat.format(date);
    }

    public String formatPayment(double payment){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("#,##0",symbols);
        if(Double.isNaN(payment)){
            return "";
        }
        return formatter.format(payment)+ " VNĐ";
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


    public void showPayment(Long id, Long ud, Model model, Principal principal){
        Room room = new Room();
        Optional<Room> optRoom =roomRepository.findById(id);
        if(optRoom.isPresent()){
            room=optRoom.get();
        }
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
        Room room = new Room();
        Optional<Room> optRoom = roomRepository.findById(id);
        if(optRoom.isPresent()){
            room=optRoom.get();
        }
        room.setIsEmpty("Trống");
        roomRepository.save(room);
        Rental rental = new Rental();
        List<Rental> lrent = rentalRepository.findByGuestIDandRoomID(ud,id);
        rental = lrent.get(lrent.size()-1);
        model.addAttribute("Rental",rental);
    }

    public void editRental(Rental rental){
        rentalRepository.save(rental);
    }

}
