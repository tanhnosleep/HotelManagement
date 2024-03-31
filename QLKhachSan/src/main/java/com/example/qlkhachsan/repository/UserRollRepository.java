package com.example.qlkhachsan.repository;

import com.example.qlkhachsan.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRollRepository extends JpaRepository<UserRole,Long> {

//    @Modifying
//    @Query(value = "INSERT INTO user_role (id,user_id,role_id) VALUES (:id,:user_id,:role_id)",nativeQuery = true)
//    void addUserRoll(@Param("id") Long id,@Param("user_id") Long userId,@Param("role_id") Long roleId);
}