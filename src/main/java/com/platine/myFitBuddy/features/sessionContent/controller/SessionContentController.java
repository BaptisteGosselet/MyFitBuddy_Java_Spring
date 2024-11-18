package com.platine.myFitBuddy.features.sessionContent.controller;

import com.platine.myFitBuddy.features.sessionContent.model.SessionContentCreateForm;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentDTO;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentDTOWithExerciseKey;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentUpdateForm;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/sessionContent")
public interface SessionContentController {
  @GetMapping(value = "/{sessionId}", produces = "application/json")
  ResponseEntity<List<SessionContentDTOWithExerciseKey>> findByUserBySessionId(
    @RequestParam("sessionId") Long sessionId
  );

  @PostMapping(produces = "application/json", consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  ResponseEntity<SessionContentDTO> create(
    @RequestBody final SessionContentCreateForm createForm
  );

  @PutMapping(
    value = "/{sessionContentId}",
    produces = "application/json",
    consumes = "application/json"
  )
  @ResponseStatus(HttpStatus.OK)
  ResponseEntity<SessionContentDTO> update(
    @RequestBody final SessionContentUpdateForm updateForm,
    @RequestParam("sessionContentId") Long sessionId
  );

  @DeleteMapping(
    value = "/{sessionContentId}",
    produces = "application/json",
    consumes = "application/json"
  )
  ResponseEntity<Boolean> delete(@RequestParam("sessionContentId") Long sessionContentId);

  @PutMapping(
    value = "/list",
    produces = "application/json",
    consumes = "application/json"
  )
  ResponseEntity<List<SessionContentDTO>> list(
    @RequestParam("sessionId") Long sessionId,
    @RequestBody List<SessionContentUpdateForm> listSessionToUpdate
  );
}
