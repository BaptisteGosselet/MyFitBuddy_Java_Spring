package com.platine.myFitBuddy.features.dbUsers.controller;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.model.DBUserEditForm;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
public interface UserController {
  @GetMapping("/me")
  public ResponseEntity<DBUser> getCurrentUser();

  @GetMapping("/me/username")
  public ResponseEntity<String> getCurrentUsername();

  @GetMapping("/me/email")
  public ResponseEntity<String> getCurrentEmail();

  @DeleteMapping("/me/delete")
  public ResponseEntity<Boolean> deleteUser();

  @GetMapping("/all")
  public ResponseEntity<List<DBUser>> getAllUsers();

  @PutMapping("me/edit")
  public ResponseEntity<DBUser> editUser(@RequestBody final DBUserEditForm form);
}
