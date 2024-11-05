package com.platine.myFitBuddy.features.fitRecords.service;

import java.util.List;
import java.util.Optional;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.fitRecords.model.FitRecord;

public interface FitRecordService {

    Optional<FitRecord> getRecordById(final long recordId, final DBUser user);

    List<FitRecord> getRecordsOfUser(final DBUser user);

    FitRecord createRecord(final long sessionId, final DBUser user);

    void deleteRecord(final long recordId, final DBUser user);

}
