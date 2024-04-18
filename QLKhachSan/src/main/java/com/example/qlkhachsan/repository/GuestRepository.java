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




}