package com.platine.myFitBuddy.features.fitRecords.service;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.fitRecords.model.FitRecord;
import com.platine.myFitBuddy.features.fitRecords.repository.FitRecordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class FitRecordServiceImpl implements FitRecordService {
  @Autowired
  private final FitRecordRepository recordRepository;

  @Override
  public Optional<FitRecord> getRecordById(long recordId, DBUser user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecordById'");
  }

  @Override
  public List<FitRecord> getRecordsOfUser(DBUser user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecordsOfUser'");
  }

  @Override
  public FitRecord createRecord(long sessionId, DBUser user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createRecord'");
  }

  @Override
  public void deleteRecord(long recordId, DBUser user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteRecord'");
  }
}
