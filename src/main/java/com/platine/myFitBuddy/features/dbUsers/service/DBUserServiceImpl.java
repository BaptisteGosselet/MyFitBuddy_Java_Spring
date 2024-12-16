package com.platine.myFitBuddy.features.dbUsers.service;

import com.platine.myFitBuddy.exceptions.EmailAlreadyExistsException;
import com.platine.myFitBuddy.exceptions.UsernameAlreadyExistsException;
import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.model.DBUserEditForm;
import com.platine.myFitBuddy.features.dbUsers.repository.DBUserRepository;
import com.platine.myFitBuddy.utils.MyUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DBUserServiceImpl implements DBUserService {
  @Autowired
  private DBUserRepository dbUserRepository;

  @Override
  public List<DBUser> getAllUsers() {
    return dbUserRepository.findAll();
  }

  @Override
  public DBUser getCurrentUser() {
    Authentication authentication = SecurityContextHolder
      .getContext()
      .getAuthentication();
    DBUser currentUser = (DBUser) authentication.getPrincipal();
    return dbUserRepository.findByUsername(currentUser.getUsername()).get();
  }

  @Override
  public boolean deleteuser() {
    final DBUser userToDelete = getCurrentUser();
    if (userToDelete != null) {
      dbUserRepository.delete(userToDelete);
      return true;
    }
    return false;
  }

  @Override
  @Transactional
  public DBUser editUser(final DBUserEditForm form) {
    DBUser userToEdit = getCurrentUser();
    if (userToEdit == null) {
      throw new IllegalStateException("Current user not found");
    }

    if (form.getUsername() != null) {
      final String newUsername = MyUtils.normalize(form.getUsername());
      if (dbUserRepository.findByUsername(newUsername).isPresent()) {
        throw new UsernameAlreadyExistsException(
          "Username already exists: " + newUsername
        );
      }
      userToEdit.setUsername(newUsername);
    }

    if (form.getEmail() != null) {
      final String newEmail = MyUtils.normalize(form.getEmail());
      if (dbUserRepository.findByEmail(newEmail).isPresent()) {
        throw new EmailAlreadyExistsException("Email already exists: " + newEmail);
      }
      userToEdit.setEmail(newEmail);
    }

    System.out.println("want: " + userToEdit.getUsername() + " " + userToEdit.getEmail());

    final DBUser savedUser = dbUserRepository.save(userToEdit);
    System.out.println("get: " + savedUser.getUsername() + " " + savedUser.getEmail());
    return savedUser;
  }
}
