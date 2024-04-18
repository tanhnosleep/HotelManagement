package com.example.qlkhachsan.repository;

import com.example.qlkhachsan.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<AppUser, Long> {


    @Query(value = "SELECT * FROM app_user WHERE user_name = :user_name", nativeQuery = true)
    public AppUser findUserName(@Param("user_name") String userName);


}