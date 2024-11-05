package com.platine.myFitBuddy.features.sessions.repository;

import com.platine.myFitBuddy.features.sessions.model.FitSession;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<FitSession, Long> {
  List<FitSession> findByUserId(Long userId);
}
