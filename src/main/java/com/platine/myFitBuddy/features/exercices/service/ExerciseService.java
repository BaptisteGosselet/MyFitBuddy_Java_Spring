package com.platine.myFitBuddy.features.exercices.service;

import com.platine.myFitBuddy.features.exercices.model.Exercise;
import java.util.List;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public interface ExerciseService {
  public List<Exercise> getAllExercises();
  public Exercise getExerciseById(final long id);
  public StreamingResponseBody getExerciseImage(final long id);
}
