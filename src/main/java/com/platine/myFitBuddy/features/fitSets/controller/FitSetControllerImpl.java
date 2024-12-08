package com.platine.myFitBuddy.features.fitSets.controller;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.service.DBUserServiceImpl;
import com.platine.myFitBuddy.features.fitSets.model.FitSet;
import com.platine.myFitBuddy.features.fitSets.model.FitSetCreateForm;
import com.platine.myFitBuddy.features.fitSets.model.FitSetUpdateForm;
import com.platine.myFitBuddy.features.fitSets.service.FitSetServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class FitSetControllerImpl implements FitSetController {
  private final FitSetServiceImpl fitSetService;
  private final DBUserServiceImpl dbUserService;

  @Override
  @GetMapping(value = "/{setId}", produces = "application/json")
  public ResponseEntity<FitSet> getSetById(@PathVariable("setId") long setId) {
    DBUser user = dbUserService.getCurrentUser();

    return fitSetService
      .getSetById(setId, user)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @Override
  @GetMapping(value = "/record/{recordId}", produces = "application/json")
  public ResponseEntity<List<FitSet>> getSetsByRecordId(
    @PathVariable("recordId") long recordId
  ) {
    DBUser user = dbUserService.getCurrentUser();

    List<FitSet> fitSets = fitSetService.getSetsbyUser(user);
    if (fitSets.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.ok(fitSets);
  }

  @Override
  @PostMapping(value = "/create", produces = "application/json")
  public ResponseEntity<FitSet> addSetToSession(
    @RequestBody final FitSetCreateForm form
  ) {
    DBUser user = dbUserService.getCurrentUser();

    try {
      FitSet newSet = fitSetService.addSetToSession(form, user);
      return ResponseEntity.status(HttpStatus.CREATED).body(newSet);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @Override
  @PutMapping(value = "/update", produces = "application/json")
  public ResponseEntity<FitSet> updateSet(@RequestBody final FitSetUpdateForm form) {
    DBUser user = dbUserService.getCurrentUser();

    try {
      FitSet updatedSet = fitSetService.updateSet(form, user);
      return ResponseEntity.ok(updatedSet);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @Override
  @DeleteMapping(value = "/delete/{idSet}", produces = "application/json")
  public ResponseEntity<Boolean> deleteSet(@PathVariable("idSet") final long idSet) {
    DBUser user = dbUserService.getCurrentUser();

    try {
      fitSetService.deleteSet(idSet, user);
      return ResponseEntity.ok(true);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }
}
