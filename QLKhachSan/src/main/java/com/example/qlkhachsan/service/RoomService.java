package com.example.qlkhachsan.service;

import com.example.qlkhachsan.repository.RoomRepository;
import com.example.qlkhachsan.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository){
        this.roomRepository=roomRepository;
    }

    public String formatPrice(double priceDay){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("#,##0",symbols);
        return formatter.format(priceDay)+ " VNĐ";
    }

    public List<Room> showListRoom(){
        return roomRepository.findAll();
    }

    public Optional<Room> showRoomById(Long id){
        return roomRepository.findById(id);
    }

    public List<Room> searchRoom(String keyword){
        List<Room> lr = roomRepository.findAll();
        List<Room> result  = new ArrayList<Room>();
        for (Room r : lr) {
            if(r.getRoom_id().toString().contains(keyword) ||
                    r.getType().toLowerCase().contains(keyword.toLowerCase()) ||
                    r.getIsEmpty().toLowerCase().contains(keyword.toLowerCase())||
                    r.getPriceDay().toString().contains(keyword)) {
                result.add(r);
            }
        }
        return result;
    }

    public void addRoom(Room room){
        roomRepository.save(room);
    }

    public Room showEditRoom(Long id){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        return optionalRoom.orElse(null);
    }

    public void editRoom(Room room){
        roomRepository.save(room);
    }

    public void deleteRoom(Long id){
        roomRepository.deleteById(id);
    }
}
