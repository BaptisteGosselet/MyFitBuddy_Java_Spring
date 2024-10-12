package com.platine.myFitBuddy.features.dbUsers.controller;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.model.DBUserRegisterForm;
import com.platine.myFitBuddy.features.dbUsers.service.DBUserServiceImpl;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private DBUserServiceImpl dbUserServiceImpl;

    public ResponseEntity<DBUser> getCurrentUser() {
        System.out.println("Get current User");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DBUser currentUser = (DBUser) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @Override
    public ResponseEntity<List<DBUser>> getAllUsers() {
        return ResponseEntity.ok(dbUserServiceImpl.getAllUsers());
    }
    
}
