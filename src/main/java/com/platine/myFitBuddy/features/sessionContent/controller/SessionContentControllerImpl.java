package com.platine.myFitBuddy.features.sessionContent.controller;

import com.platine.myFitBuddy.features.dbUsers.service.DBUserServiceImpl;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContent;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentCreateForm;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentUpdateForm;
import com.platine.myFitBuddy.features.sessionContent.service.SessionContentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SessionContentControllerImpl implements SessionContentController {
  private final SessionContentServiceImpl sessionContentService;
  private final DBUserServiceImpl dbUserService;

  @Override
  public ResponseEntity<List<SessionContent>> findByUserBySessionId(Long sessionId) {
    return ResponseEntity.ok(sessionContentService.findByUserIdBySession(sessionId, dbUserService.getCurrentUser()));
  }

  @Override
  public ResponseEntity<SessionContent> create(SessionContentCreateForm createForm) {
    return ResponseEntity.ok(sessionContentService.create(createForm, dbUserService.getCurrentUser()));
  }

  @Override
  public ResponseEntity<SessionContent> update(SessionContentUpdateForm updateForm, Long sessionId) {
    return ResponseEntity.ok(sessionContentService.update(updateForm, sessionId, dbUserService.getCurrentUser()));

  }
}