package com.platine.myFitBuddy.features.dbUsers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;

public interface DBUserRepository extends JpaRepository<DBUser, Long>{
    public DBUser findByUsername(String username);

}
