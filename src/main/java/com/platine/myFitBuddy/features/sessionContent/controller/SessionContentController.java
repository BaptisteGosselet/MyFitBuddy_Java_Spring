package com.platine.myFitBuddy.features.sessionContent.controller;

import com.platine.myFitBuddy.features.sessionContent.model.SessionContentCreateForm;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentDTO;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentDTOWithExercise;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentUpdateForm;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/sessionContent")
public interface SessionContentController {
  @GetMapping(value = "/{sessionId}", produces = "application/json")
  ResponseEntity<List<SessionContentDTOWithExercise>> findByUserBySessionId(
    @PathVariable("sessionId") Long sessionId
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
    @PathVariable("sessionContentId") Long sessionId
  );

  @DeleteMapping(
    value = "/{sessionContentId}",
    produces = "application/json",
    consumes = "application/json"
  )
  ResponseEntity<Boolean> delete(@PathVariable("sessionContentId") Long sessionContentId);

  @PutMapping(
    value = "/list",
    produces = "application/json",
    consumes = "application/json"
  )
  ResponseEntity<List<SessionContentDTO>> list(
    @RequestBody List<SessionContentUpdateForm> listSessionToUpdate
  );
}
