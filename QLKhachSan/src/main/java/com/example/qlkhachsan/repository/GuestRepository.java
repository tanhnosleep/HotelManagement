package com.example.qlkhachsan.repository;



import com.example.qlkhachsan.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface GuestRepository extends JpaRepository<Guest,Long> {

    @Query(value = "SELECT * FROM guest",nativeQuery = true)
    List<Guest> showListGuest();

//    @Modifying
//    @Query(value = "INSERT INTO guest (guest_id,guest_name,birth,id_card,address,phone_number,email) " +
//            "VALUES (:guest_id,:guest_name,:birth,:id_card,:address,:phone_number,:email)",nativeQuery = true)
//    void addGuest(@Param("guest_id") Long guestId, @Param("guest_name") String guestName,
//                  @Param("birth") String birth, @Param("id_card") String idCard, @Param("address") String address,
//                  @Param("phone_number") String phoneNumber, @Param("email") String email);


}