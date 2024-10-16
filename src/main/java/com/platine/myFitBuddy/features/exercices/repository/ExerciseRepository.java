package com.platine.myFitBuddy.features.exercices.repository;

import org.springframework.stereotype.Repository;
import com.platine.myFitBuddy.features.exercices.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long>{
    
}
