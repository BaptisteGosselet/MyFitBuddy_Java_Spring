package com.platine.myFitBuddy.features.exercices.controller;

import com.platine.myFitBuddy.features.exercices.model.Exercise;
import com.platine.myFitBuddy.features.exercices.service.ExerciseService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
public class ExerciseControllerImpl implements ExerciseController {
  @Autowired
  private ExerciseService exerciseService;

  @Override
  public ResponseEntity<List<Exercise>> getAllExercises() {
    List<Exercise> exercises = exerciseService.getAllExercises();
    return ResponseEntity.ok(exercises);
  }

  @Override
  public ResponseEntity<Exercise> getExerciseById(@PathVariable("id") long id) {
    Exercise exercise = exerciseService.getExerciseById(id);
    return ResponseEntity.ok(exercise);
  }

  @Override
  public ResponseEntity<StreamingResponseBody> getImage(@PathVariable("id") long id)
    throws IOException {
    StreamingResponseBody responseBody = exerciseService.getExerciseImage(id);
    return ResponseEntity.ok().header("Content-Type", "image/jpeg").body(responseBody);
  }
}
