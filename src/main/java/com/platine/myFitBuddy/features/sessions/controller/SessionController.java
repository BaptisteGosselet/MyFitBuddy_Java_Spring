package com.platine.myFitBuddy.features.sessions.controller;

import com.platine.myFitBuddy.features.sessions.model.Session;
import com.platine.myFitBuddy.features.sessions.model.SessionCreateForm;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/sessions")
public interface SessionController {
  @GetMapping(value = "/{id}", produces = "application/json")
  ResponseEntity<Session> findById(@PathVariable("id") final long sessionId);

  @GetMapping(value = "/user", produces = "application/json")
  ResponseEntity<List<Session>> findByUser();

  @PostMapping(value = "", produces = "application/json", consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  ResponseEntity<Session> create(@RequestBody final SessionCreateForm createForm);

  @GetMapping("/all")
  ResponseEntity<List<Session>> getAllSessions();
}
