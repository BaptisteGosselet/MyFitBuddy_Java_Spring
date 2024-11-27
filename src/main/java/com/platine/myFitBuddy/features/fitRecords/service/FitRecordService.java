package com.platine.myFitBuddy.features.fitRecords.service;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.fitRecords.model.FitRecord;
import java.util.List;
import java.util.Optional;

public interface FitRecordService {
  Optional<FitRecord> getRecordById(final long recordId, final DBUser user);

  List<FitRecord> getRecordsOfUser(final DBUser user);

  FitRecord createRecord(final long sessionId, final DBUser user);

  FitRecord setFeelingNote(final long sessionId, final String note, final DBUser user);

  void deleteRecord(final long recordId, final DBUser user);
}
