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

//    @Query("select usr from AppUser usr where usr.userName = :userName")
//    public AppUser findUserName(@Param("userName") String userName);
    @Query(value = "SELECT * FROM app_user WHERE user_name = :user_name", nativeQuery = true)
    public AppUser findUserName(@Param("user_name") String userName);

//    @Modifying
//    @Query(value = "INSERT INTO app_user (user_id,user_name,encryted_password,enabled,guest_guest_id) " +
//            "VALUES (:user_id,:user_name,:encryted_password,:enabled,:guest_id)",nativeQuery = true)
//    void addAppUser(@Param("user_id") Long userId, @Param("user_name") String userName,
//                    @Param("encryted_password") String encrytedPassword, @Param("enabled") boolean enabled,
//                    @Param("guest_id") Long guestId);
}