package com.platine.myFitBuddy.features.sessions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.platine.myFitBuddy.features.sessions.model.Sample;

public interface SampleRepository extends JpaRepository<Sample, Long> {
  
}