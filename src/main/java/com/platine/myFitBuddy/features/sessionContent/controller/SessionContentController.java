package com.platine.myFitBuddy.features.sessionContent.controller;

import com.platine.myFitBuddy.features.sessionContent.model.SessionContent;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentCreateForm;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentUpdateForm;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/sessionContent")
public interface SessionContentController {
  @GetMapping(value = "/{sessionId}", produces = "application/json")
  ResponseEntity<List<SessionContent>> findByUserBySessionId(
    @RequestParam("sessionId") Long sessionId
  );

  @PostMapping(produces = "application/json", consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  ResponseEntity<SessionContent> create(
    @RequestBody final SessionContentCreateForm createForm
  );

  @PutMapping(
    value = "/{sessionContentId}",
    produces = "application/json",
    consumes = "application/json"
  )
  @ResponseStatus(HttpStatus.OK)
  ResponseEntity<SessionContent> update(
    @RequestBody final SessionContentUpdateForm updateForm,
    @RequestParam("sessionContentId") Long sessionId
  );
}
