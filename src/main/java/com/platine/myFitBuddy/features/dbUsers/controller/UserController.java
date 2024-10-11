package com.platine.myFitBuddy.features.dbUsers.controller;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.model.DBUserRegisterForm;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
public interface UserController {

    //Login et Logout sont gérés par Spring Security

    // Inscription
    @PostMapping("/register")
    public ResponseEntity<DBUser> register(@RequestBody DBUserRegisterForm form);

    // Consultation des informations de l'utilisateur
    @GetMapping("/me")
    public ResponseEntity<DBUser> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails);

    // Suppression du compte utilisateur
    @DeleteMapping("/delete")
    public ResponseEntity<DBUser> deleteUser(@AuthenticationPrincipal UserDetails userDetails);

    // Consultation les informations de tous les utilisateurs
    @GetMapping("/all")
    public ResponseEntity<List<DBUser>> getAllUsers();
    
}
