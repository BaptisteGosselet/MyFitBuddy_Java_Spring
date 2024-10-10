package com.platine.myFitBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.platine.myFitBuddy.model.Sample;

public interface SampleRepository extends JpaRepository<Sample, Long> {
  
}