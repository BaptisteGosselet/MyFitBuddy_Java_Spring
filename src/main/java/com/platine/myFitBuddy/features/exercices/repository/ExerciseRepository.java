package com.platine.myFitBuddy.features.exercices.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.platine.myFitBuddy.features.exercices.model.Exercise;
import com.platine.myFitBuddy.features.exercices.model.MuscleGroup;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
  @Query(
    "SELECT e FROM Exercise e WHERE " +
    "(:key IS NULL OR REPLACE(LOWER(e.key), ' ', '_') LIKE '%' || CAST(:key AS text) || '%') AND " +
    "(:muscleGroup IS NULL OR e.muscleGroup = :muscleGroup)"
  )
  Page<Exercise> findByKeyAndMuscleGroup(
    @Param("key") String key,
    @Param("muscleGroup") MuscleGroup muscleGroup,
    Pageable pageable
  );
}
