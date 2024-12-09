package com.platine.myFitBuddy.features.sessionContent.repository;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContent;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionContentRepository extends JpaRepository<SessionContent, Long> {
  List<SessionContent> findSessionContentBySessionIdAndUser(Long sessionId, DBUser user);

  void deleteByIdAndUser(Long id, DBUser user);

  @Query(
    "SELECT COALESCE(MAX(s.index), 0) FROM SessionContent s WHERE s.sessionId = :sessionId"
  )
  int findMaxIndexBySessionId(@Param("sessionId") Long sessionId);
}
