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

    // Consultation des informations de l'utilisateur
    @GetMapping("/me")
    public ResponseEntity<DBUser> getCurrentUser();

    // Consultation les informations de tous les utilisateurs
    @GetMapping("/all")
    public ResponseEntity<List<DBUser>> getAllUsers();
    
}
