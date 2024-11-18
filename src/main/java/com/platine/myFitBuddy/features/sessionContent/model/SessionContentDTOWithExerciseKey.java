package com.platine.myFitBuddy.features.sessionContent.model;

import com.platine.myFitBuddy.features.exercices.model.Exercise;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionContentDTOWithExerciseKey {
  private Long id;
  private Long sessionId;
  private Long exerciseId;
  private int numberOfSet;
  private int restTimeInSecond;
  private int index;

  // Exercise Information
  private String key;

  public SessionContentDTOWithExerciseKey(
    SessionContent sessionContent,
    Exercise exercise
  ) {
    this.id = sessionContent.getId();
    this.sessionId = sessionContent.getSessionId();
    this.exerciseId = sessionContent.getExerciseId();
    this.numberOfSet = sessionContent.getNumberOfSet();
    this.restTimeInSecond = sessionContent.getRestTimeInSecond();
    this.index = sessionContent.getIndex();

    this.key = exercise.getKey();
  }
}
