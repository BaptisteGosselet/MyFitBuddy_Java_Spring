package com.platine.myFitBuddy.features.dbUsers.service;

import com.platine.myFitBuddy.exceptions.EmailAlreadyExistsException;
import com.platine.myFitBuddy.exceptions.UsernameAlreadyExistsException;
import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.repository.DBUserRepository;
import com.platine.myFitBuddy.utils.MyUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
  public DBUser setUsername(final String newUsername) {
    final String normalNewUsername = MyUtils.normalize(newUsername);

    if (dbUserRepository.findByUsername(normalNewUsername).isPresent()) {
      throw new UsernameAlreadyExistsException(
        "Username already exists: " + normalNewUsername
      );
    }

    final DBUser userToEdit = getCurrentUser();
    if (userToEdit != null) {
      userToEdit.setUsername(newUsername);
    }

    return dbUserRepository.save(userToEdit);
  }

  @Override
  public DBUser setEmail(final String newEmail) {
    final String normalNewEmail = MyUtils.normalize(newEmail);

    if (dbUserRepository.findByEmail(normalNewEmail).isPresent()) {
      throw new EmailAlreadyExistsException("Email already exists: " + normalNewEmail);
    }

    final DBUser userToEdit = getCurrentUser();
    if (userToEdit != null) {
      userToEdit.setEmail(normalNewEmail);
    }

    return dbUserRepository.save(userToEdit);
  }
}
