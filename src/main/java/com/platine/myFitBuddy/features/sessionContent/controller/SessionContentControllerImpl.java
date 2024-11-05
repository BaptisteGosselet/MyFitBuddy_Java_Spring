package com.platine.myFitBuddy.features.sessionContent.controller;

import com.platine.myFitBuddy.features.dbUsers.service.DBUserServiceImpl;
import com.platine.myFitBuddy.features.sessionContent.mapper.SessionContentMapper;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContent;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentCreateForm;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentDTO;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentUpdateForm;
import com.platine.myFitBuddy.features.sessionContent.service.SessionContentServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SessionContentControllerImpl implements SessionContentController {
  private final SessionContentServiceImpl sessionContentService;
  private final DBUserServiceImpl dbUserService;

  @Override
  public ResponseEntity<List<SessionContentDTO>> findByUserBySessionId(Long sessionId) {
    return ResponseEntity.ok(sessionContentService.findByUserIdBySession(sessionId, dbUserService.getCurrentUser())
            .stream()
            .map(SessionContentMapper::mapToDTO)
            .collect(Collectors.toList()));
  }

  @Override
  public ResponseEntity<SessionContentDTO> create(SessionContentCreateForm createForm) {
    return ResponseEntity.ok(SessionContentMapper.mapToDTO(sessionContentService.create(createForm, dbUserService.getCurrentUser())));
  }

  @Override
  public ResponseEntity<SessionContentDTO> update(SessionContentUpdateForm updateForm, Long sessionId) {
    return ResponseEntity.ok(SessionContentMapper.mapToDTO(sessionContentService.update(updateForm, sessionId, dbUserService.getCurrentUser())));
  }

  @Override
  public ResponseEntity<Boolean> delete(Long sessionContentId) {
    sessionContentService.delete(sessionContentId, dbUserService.getCurrentUser());
    return ResponseEntity.ok(true);
  }

  @Override
  public ResponseEntity<List<SessionContentDTO>> list(Long sessionId, List<SessionContentUpdateForm> listSessionToUpdate) {
    List<SessionContent> res = new ArrayList<>();
    listSessionToUpdate.forEach(sessionContentUpdateForm ->
            res.add(sessionContentService.update(sessionContentUpdateForm, sessionId, dbUserService.getCurrentUser())));
    return ResponseEntity.ok(res.stream().map(SessionContentMapper::mapToDTO).collect(Collectors.toList()));
  }
}
