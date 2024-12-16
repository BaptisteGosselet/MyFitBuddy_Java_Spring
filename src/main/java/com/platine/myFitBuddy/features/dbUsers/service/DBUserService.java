package com.platine.myFitBuddy.features.dbUsers.service;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.model.DBUserEditForm;
import java.util.List;

public interface DBUserService {
  public DBUser getCurrentUser();

  public List<DBUser> getAllUsers();

  public boolean deleteuser();

  public DBUser editUser(final DBUserEditForm form);
}
