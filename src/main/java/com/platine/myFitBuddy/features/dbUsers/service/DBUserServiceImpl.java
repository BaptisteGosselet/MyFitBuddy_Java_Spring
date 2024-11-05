package com.platine.myFitBuddy.features.dbUsers.service;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.repository.DBUserRepository;
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
}
