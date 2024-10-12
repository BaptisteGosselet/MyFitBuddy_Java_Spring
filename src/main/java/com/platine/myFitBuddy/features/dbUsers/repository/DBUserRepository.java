package com.platine.myFitBuddy.features.dbUsers.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;

@Repository
public interface DBUserRepository extends JpaRepository<DBUser, Long>{
    public Optional<DBUser> findByUsername(String username);

}
