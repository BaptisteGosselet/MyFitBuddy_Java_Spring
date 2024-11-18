package com.platine.myFitBuddy.features.sessionContent.controller;

import com.platine.myFitBuddy.features.dbUsers.service.DBUserServiceImpl;
import com.platine.myFitBuddy.features.exercices.model.Exercise;
import com.platine.myFitBuddy.features.exercices.service.ExerciseService;
import com.platine.myFitBuddy.features.sessionContent.mapper.SessionContentMapper;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContent;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentCreateForm;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentDTO;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentDTOWithExerciseKey;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentUpdateForm;
import com.platine.myFitBuddy.features.sessionContent.service.SessionContentService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SessionContentControllerImpl implements SessionContentController {
  private final SessionContentService sessionContentService;
  private final ExerciseService ExerciseService;
  private final DBUserServiceImpl dbUserService;

  @Override
  public ResponseEntity<List<SessionContentDTOWithExerciseKey>> findByUserBySessionId(
    Long sessionId
  ) {
    List<SessionContentDTOWithExerciseKey> sessionContentsDTO = new ArrayList<>();
    List<SessionContent> sessionContents = sessionContentService.findByUserIdBySession(
      sessionId,
      dbUserService.getCurrentUser()
    );
    sessionContents.forEach(
      sessionContent -> {
        Exercise exercise = ExerciseService.getExerciseById(
          sessionContent.getExerciseId()
        );
        sessionContentsDTO.add(
          new SessionContentDTOWithExerciseKey(sessionContent, exercise)
        );
      }
    );
    return ResponseEntity.ok(sessionContentsDTO);
  }

  @Override
  public ResponseEntity<SessionContentDTO> create(SessionContentCreateForm createForm) {
    return ResponseEntity.ok(
      SessionContentMapper.mapToDTO(
        sessionContentService.create(createForm, dbUserService.getCurrentUser())
      )
    );
  }

  @Override
  public ResponseEntity<SessionContentDTO> update(
    SessionContentUpdateForm updateForm,
    Long sessionId
  ) {
    return ResponseEntity.ok(
      SessionContentMapper.mapToDTO(
        sessionContentService.update(
          updateForm,
          sessionId,
          dbUserService.getCurrentUser()
        )
      )
    );
  }

  @Override
  public ResponseEntity<Boolean> delete(Long sessionContentId) {
    sessionContentService.delete(sessionContentId, dbUserService.getCurrentUser());
    return ResponseEntity.ok(true);
  }

  @Override
  public ResponseEntity<List<SessionContentDTO>> list(
    Long sessionId,
    List<SessionContentUpdateForm> listSessionToUpdate
  ) {
    List<SessionContent> res = new ArrayList<>();
    listSessionToUpdate.forEach(
      sessionContentUpdateForm ->
        res.add(
          sessionContentService.update(
            sessionContentUpdateForm,
            sessionId,
            dbUserService.getCurrentUser()
          )
        )
    );
    return ResponseEntity.ok(
      res.stream().map(SessionContentMapper::mapToDTO).collect(Collectors.toList())
    );
  }
}
