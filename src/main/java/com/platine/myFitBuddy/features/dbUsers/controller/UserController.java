package com.platine.myFitBuddy.features.dbUsers.controller;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
public interface UserController {
  // Consultation des informations de l'utilisateur
  @GetMapping("/me")
  public ResponseEntity<DBUser> getCurrentUser();

  @GetMapping("/me/username")
  public ResponseEntity<String> getCurrentUsername();

  @GetMapping("/me/email")
  public ResponseEntity<String> getCurrentEmail();

  // Consultation les informations de tous les utilisateurs
  @GetMapping("/all")
  public ResponseEntity<List<DBUser>> getAllUsers();
}
