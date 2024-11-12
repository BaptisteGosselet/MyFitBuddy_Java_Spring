package com.platine.myFitBuddy.features.exercices.repository;

import com.platine.myFitBuddy.features.exercices.model.Exercise;
import com.platine.myFitBuddy.features.exercices.model.MuscleGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
  @Query(
    "SELECT e FROM Exercise e WHERE " +
    "(:key IS NULL OR " +
    "LOWER(e.key) LIKE '%' || LOWER(CAST(:key AS text)) || '%' OR " +
    "LOWER(e.labelEn) LIKE '%' || LOWER(CAST(:key AS text)) || '%' OR " +
    "LOWER(e.labelFr) LIKE '%' || LOWER(CAST(:key AS text)) || '%') AND " +
    "(:muscleGroup IS NULL OR e.muscleGroup = :muscleGroup)"
  )
  Page<Exercise> findByKeyAndMuscleGroup(
    @Param("key") String key,
    @Param("muscleGroup") MuscleGroup muscleGroup,
    Pageable pageable
  );
}
