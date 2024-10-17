package com.platine.myFitBuddy.features.exercices.repository;

import com.platine.myFitBuddy.features.exercices.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {}
