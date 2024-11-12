package com.platine.myFitBuddy.features.sessions.controller;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.service.DBUserServiceImpl;
import com.platine.myFitBuddy.features.sessions.model.FitSession;
import com.platine.myFitBuddy.features.sessions.model.SessionCreateForm;
import com.platine.myFitBuddy.features.sessions.model.SessionUpdateForm;
import com.platine.myFitBuddy.features.sessions.service.SessionServiceImpl;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SessionControllerImpl implements SessionController {
  private final SessionServiceImpl sessionService;
  private final DBUserServiceImpl dbUserService;

  @Override
  public ResponseEntity<FitSession> findById(final long sessionId) {
    DBUser user = dbUserService.getCurrentUser();
    Optional<FitSession> sessionOptional = sessionService.findById(sessionId, user);
    return sessionOptional
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<List<FitSession>> findByUser() {
    DBUser user = dbUserService.getCurrentUser();
    List<FitSession> sessionsList = sessionService.findByUserId(user);
    return ResponseEntity.ok(sessionsList);
  }

  @Override
  public ResponseEntity<FitSession> create(final SessionCreateForm createForm) {
    DBUser user = dbUserService.getCurrentUser();
    return ResponseEntity.ok(sessionService.create(createForm, user));
  }

  @Override
  public ResponseEntity<List<FitSession>> getAllSessions() {
    return ResponseEntity.ok(sessionService.findAll());
  }

  @Override
  public ResponseEntity<FitSession> update(
    long sessionId,
    SessionUpdateForm updatedSession
  ) {
    DBUser user = dbUserService.getCurrentUser();
    return ResponseEntity.ok(sessionService.update(updatedSession, user));
  }

  @Override
  public ResponseEntity<Boolean> delete(long sessionId) {
    DBUser user = dbUserService.getCurrentUser();
    sessionService.delete(sessionId, user);
    return ResponseEntity.ok(true);
  }
}
