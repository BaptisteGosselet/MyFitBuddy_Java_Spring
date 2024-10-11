package com.platine.myFitBuddy.features.dbUsers.service;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.model.DBUserRegisterForm;
import com.platine.myFitBuddy.features.dbUsers.repository.DBUserRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DBUserServiceImpl implements DBUserService {

    @Autowired
    private DBUserRepository dbUserRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public DBUser register(DBUserRegisterForm registerForm) {
        // TODO : checks (if exists ? insultes ? normalisation ? etc.)
        DBUser userToCreate = new DBUser(registerForm.getUsername(), passwordEncoder.encode(registerForm.getPassword()), "USER");
        return dbUserRepository.save(userToCreate);
    }

    public DBUser getCurrentDBUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                return dbUserRepository.findByUsername(userDetails.getUsername());
            }
        }
        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public DBUser deleteUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public List<DBUser> getAllUsers() {
        return dbUserRepository.findAll();
    }


    
}
