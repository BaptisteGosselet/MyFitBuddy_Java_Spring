package com.platine.myFitBuddy.features.sessionContent.repository;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContent;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionContentRepository extends JpaRepository<SessionContent, Long> {
  SessionContent findBySessionIdAndUser(Long sessionId, DBUser user);

  List<SessionContent> findSessionContentBySessionIdAndUser(Long sessionId, DBUser user);
}
