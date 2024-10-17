package com.platine.myFitBuddy.features.dbUsers.repository;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DBUserRepository extends JpaRepository<DBUser, Long> {
  public Optional<DBUser> findByUsername(String username);
  public Optional<DBUser> findByEmail(String email);

  @Query(
    "SELECT u FROM DBUser u WHERE u.username = :usernameOrEmail OR u.email = :usernameOrEmail"
  )
  Optional<DBUser> findByUsernameOrEmail(
    @Param("usernameOrEmail") String usernameOrEmail
  );
}
