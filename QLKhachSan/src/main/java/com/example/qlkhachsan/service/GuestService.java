package com.example.qlkhachsan.service;

import com.example.qlkhachsan.Repository.GuestRepository;
import com.example.qlkhachsan.Repository.RoleRepository;
import com.example.qlkhachsan.Repository.UserRepository;
import com.example.qlkhachsan.Repository.UserRollRepository;
import com.example.qlkhachsan.model.AppRole;
import com.example.qlkhachsan.model.AppUser;
import com.example.qlkhachsan.model.Guest;
import com.example.qlkhachsan.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class GuestService {
    private final GuestRepository guestRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRollRepository userRollRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository, UserRepository userRepository,
                        RoleRepository roleRepository, UserRollRepository userRollRepository){
        this.guestRepository=guestRepository;
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
        this.userRollRepository=userRollRepository;
    }

    @Transactional
    public void registerUser(AppUser appUser, Guest guest){
        appUser.setGuest(guest);
        String pass = appUser.getEncrytedPassword();
        appUser.setEncrytedPassword(BCrypt.hashpw(pass, BCrypt.gensalt(12)));

        guestRepository.save(guest);
        userRepository.save(appUser);
        AppRole appRole = new AppRole();
        Optional<AppRole> optionalAppRole = roleRepository.findById(2L);
        if(optionalAppRole.isPresent()){
            appRole=optionalAppRole.get();
        }
        UserRole userRole = new UserRole(appUser,appRole);
        userRollRepository.save(userRole);
    }


}
