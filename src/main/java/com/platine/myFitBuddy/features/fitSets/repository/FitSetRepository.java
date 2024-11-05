package com.platine.myFitBuddy.features.fitSets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platine.myFitBuddy.features.fitSets.model.FitSet;

@Repository
public interface FitSetRepository extends JpaRepository<FitSet, Long> {

    
} 
