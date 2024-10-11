package com.platine.myFitBuddy.features.dbUsers.controller;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.model.DBUserRegisterForm;
import com.platine.myFitBuddy.features.dbUsers.service.DBUserServiceImpl;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private DBUserServiceImpl dbUserServiceImpl;

    @Override
    public ResponseEntity<DBUser> register(@RequestBody DBUserRegisterForm form) {
        return ResponseEntity.ok(dbUserServiceImpl.register(form));
    }

    @Override
    public ResponseEntity<DBUser> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(dbUserServiceImpl.getCurrentDBUser());
    }

    @Override
    public ResponseEntity<DBUser> deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(dbUserServiceImpl.deleteUser());
    }

    @Override
    public ResponseEntity<List<DBUser>> getAllUsers() {
        return ResponseEntity.ok(dbUserServiceImpl.getAllUsers());
    }
    
}
