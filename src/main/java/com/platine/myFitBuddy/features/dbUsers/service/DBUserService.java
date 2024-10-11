package com.platine.myFitBuddy.features.dbUsers.service;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.model.DBUserRegisterForm;

import java.util.List;

public interface DBUserService {

    //Login et Logout sont gérés par Spring Security

    public DBUser register(DBUserRegisterForm registerForm);

    public DBUser getCurrentDBUser();

    public DBUser deleteUser();

    public List<DBUser> getAllUsers();
    
}
