package com.platine.myFitBuddy.features.dbUsers.controller;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.model.DBUserRegisterForm;
import com.platine.myFitBuddy.features.dbUsers.service.DBUserServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
  @Autowired
  private DBUserServiceImpl dbUserServiceImpl;

  public ResponseEntity<DBUser> getCurrentUser() {
    return ResponseEntity.ok(dbUserServiceImpl.getCurrentUser());
  }

  @Override
  public ResponseEntity<List<DBUser>> getAllUsers() {
    return ResponseEntity.ok(dbUserServiceImpl.getAllUsers());
  }

  @Override
  public ResponseEntity<String> getCurrentUsername() {
    final DBUser user = dbUserServiceImpl.getCurrentUser();
    return ResponseEntity.ok(user.getUsername());
  }

  @Override
  public ResponseEntity<String> getCurrentEmail() {
    final DBUser user = dbUserServiceImpl.getCurrentUser();
    return ResponseEntity.ok(user.getEmail());
  }

  @Override
  public ResponseEntity<Boolean> deleteUser(){
    return ResponseEntity.ok(dbUserServiceImpl.deleteuser());
  }
}
