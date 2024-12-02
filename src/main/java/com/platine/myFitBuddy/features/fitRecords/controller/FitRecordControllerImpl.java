package com.platine.myFitBuddy.features.fitRecords.controller;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.service.DBUserServiceImpl;
import com.platine.myFitBuddy.features.fitRecords.model.FitRecord;
import com.platine.myFitBuddy.features.fitRecords.service.FitRecordServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FitRecordControllerImpl implements FitRecordController {
  private final FitRecordServiceImpl recordService;
  private final DBUserServiceImpl dbUserService;

  @Override
  public ResponseEntity<FitRecord> getRecordById(@PathVariable long recordId) {
    DBUser currentUser = dbUserService.getCurrentUser();
    return recordService
      .getRecordById(recordId, currentUser)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<List<FitRecord>> getRecordsOfUser() {
    DBUser currentUser = dbUserService.getCurrentUser();
    List<FitRecord> records = recordService.getRecordsOfUser(currentUser);
    return ResponseEntity.ok(records);
  }

  @Override
  public ResponseEntity<FitRecord> createRecord(@RequestParam long sessionId) {
    DBUser currentUser = dbUserService.getCurrentUser();
    FitRecord newRecord = recordService.createRecord(sessionId, currentUser);
    return ResponseEntity.ok(newRecord);
  }

  @Override
  public ResponseEntity<FitRecord> setFeelingNote(
    @RequestParam long sessionId,
    final String note
  ) {
    DBUser currentUser = dbUserService.getCurrentUser();
    FitRecord newRecord = recordService.setFeelingNote(sessionId, note, currentUser);
    return ResponseEntity.ok(newRecord);
  }

  @Override
  public ResponseEntity<Boolean> deleteRecord(@PathVariable long recordId) {
    DBUser currentUser = dbUserService.getCurrentUser();
    recordService.deleteRecord(recordId, currentUser);
    return ResponseEntity.ok(true);
  }
}
