package com.platine.myFitBuddy.features.sessions.controller;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.service.DBUserServiceImpl;
import com.platine.myFitBuddy.features.sessions.model.Session;
import com.platine.myFitBuddy.features.sessions.model.SessionCreateForm;
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
  public ResponseEntity<Session> findById(final long sessionId) {
    DBUser user = dbUserService.getCurrentUser();
    Optional<Session> sessionOptional = sessionService.findById(sessionId, user);
    return sessionOptional
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<List<Session>> findByUser() {
    DBUser user = dbUserService.getCurrentUser();
    List<Session> sessionsList = sessionService.findByUserId(user);
    return ResponseEntity.ok(sessionsList);
  }

  @Override
  public ResponseEntity<Session> create(final SessionCreateForm createForm) {
    DBUser user = dbUserService.getCurrentUser();
    Session createdSession = sessionService.create(createForm, user);
    return ResponseEntity.ok(createdSession);
  }

  @Override
  public ResponseEntity<List<Session>> getAllSessions() {
    return ResponseEntity.ok(sessionService.findAll());
  }
}
