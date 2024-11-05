package com.platine.myFitBuddy.features.records.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platine.myFitBuddy.features.records.model.FitRecord;

@Repository
public interface FitRecordRepository extends JpaRepository<FitRecord, Long> {}
