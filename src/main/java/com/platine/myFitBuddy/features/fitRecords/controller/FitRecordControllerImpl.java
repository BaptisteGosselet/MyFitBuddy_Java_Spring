package com.platine.myFitBuddy.features.fitRecords.controller;

import com.platine.myFitBuddy.features.fitRecords.model.FitRecord;
import com.platine.myFitBuddy.features.fitRecords.service.FitRecordServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FitRecordControllerImpl implements FitRecordController {
  private final FitRecordServiceImpl recordService;

  @Override
  public ResponseEntity<FitRecord> getRecordById(long recordId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecordById'");
  }

  @Override
  public ResponseEntity<List<FitRecord>> getRecordsOfUser() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecordsOfUser'");
  }

  @Override
  public ResponseEntity<FitRecord> createRecord(long sessionId) {
    // TODO Auto-generated method stub
    // note : le lien entre record et session est uniquement le nom
    throw new UnsupportedOperationException("Unimplemented method 'createRecord'");
  }

  @Override
  public ResponseEntity<FitRecord> deleteRecord(long recordId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteRecord'");
  }
}
