package com.platine.myFitBuddy.features.exercices.controller;

import com.platine.myFitBuddy.features.exercices.model.Exercise;
import java.io.IOException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RequestMapping("/exercises")
public interface ExerciseController {
  @GetMapping(value = "", produces = "application/json")
  ResponseEntity<List<Exercise>> getAllExercises();

  @GetMapping(value = "/{id}", produces = "application/json")
  ResponseEntity<Exercise> getExerciseById(@PathVariable("id") long id);

  @GetMapping("/{id}/image")
  ResponseEntity<StreamingResponseBody> getImage(@PathVariable("id") long id)
    throws IOException;
}
