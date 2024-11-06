package com.platine.myFitBuddy.features.fitRecords.repository;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.fitRecords.model.FitRecord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FitRecordRepository extends JpaRepository<FitRecord, Long> {
  List<FitRecord> findByUser(DBUser user);
}
