package com.platine.myFitBuddy.features.sessionContent.repository;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionContentRepository extends JpaRepository<SessionContent, Long> {
    SessionContent findBySessionIdAndUser(Long sessionId, DBUser user);

    List<SessionContent> findSessionContentBySessionIdAndUser(Long sessionId, DBUser user);
}
