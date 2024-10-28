package com.platine.myFitBuddy.features.exercices.service;

import com.platine.myFitBuddy.features.exercices.model.Exercise;
import com.platine.myFitBuddy.features.exercices.model.MuscleGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public interface ExerciseService {
  public Page<Exercise> findByKeyAndMuscleGroup(
    final String key,
    final MuscleGroup muscleGroup,
    final Pageable pageable
  );
  public Exercise getExerciseById(final long id);
  public StreamingResponseBody getExerciseImage(final long id);
}
