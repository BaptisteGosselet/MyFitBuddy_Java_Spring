package com.platine.myFitBuddy.features.dbUsers.controller;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.model.DBUserRegisterForm;
import com.platine.myFitBuddy.features.dbUsers.repository.DBUserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    //TODO : controller minimaliste, faire un service ensuite
    private DBUserRepository dbUserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //Login et Logout sont gérés par Spring Security

    // Inscription
    @PostMapping("/register")
    public String register(@RequestBody DBUserRegisterForm form) {
        System.out.println("Register method called"); // Debug output
        System.out.println("Register " + form.getUsername());
        DBUser newUser = new DBUser(form.getUsername(), form.getPassword(), "USER");
        
        if (dbUserRepository.findByUsername(newUser.getUsername()) != null) {
            return "Username already exists!";
        }
        
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        dbUserRepository.save(newUser);
        return "User registered successfully!";
    }

    // Consultation des informations de l'utilisateur
    @GetMapping("/me")
    public DBUser getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        DBUser user = dbUserRepository.findByUsername(userDetails.getUsername());
        return user;
    }

    // Suppression du compte utilisateur
    @DeleteMapping("/delete")
    public String deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
        DBUser user = dbUserRepository.findByUsername(userDetails.getUsername());
        if (user != null) {
            dbUserRepository.delete(user);
            return "User deleted successfully!";
        }
        return "User not found!";
    }

    // Consultation les informations de tous les utilisateurs
    @GetMapping("/all")
    public List<DBUser> getAllUsers(@AuthenticationPrincipal UserDetails userDetails) {
        List<DBUser> users = dbUserRepository.findAll();
        return users;
    }
    
}
