package com.platine.myFitBuddy.features.exercices.controller;

import com.platine.myFitBuddy.features.exercices.model.Exercise;
import io.jsonwebtoken.io.IOException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/exercises")
public interface ExerciseController {
  @GetMapping(value = "", produces = "application/json")
  ResponseEntity<List<Exercise>> getAllExercises();

  @GetMapping(value = "/{id}", produces = "application/json")
  ResponseEntity<Exercise> getExerciseById(final long exerciseId);

  //TODO : possibilité d'une autre méthode
  @GetMapping("/{id}/image")
  public ResponseEntity<byte[]> getImage() throws IOException;
}
