package com.platine.myFitBuddy.features.fitRecords.repository;

import com.platine.myFitBuddy.features.fitRecords.model.FitRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FitRecordRepository extends JpaRepository<FitRecord, Long> {}
