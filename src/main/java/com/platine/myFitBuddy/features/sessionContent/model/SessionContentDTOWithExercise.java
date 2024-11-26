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
public class SessionContentDTOWithExercise {
  private Long id;
  private Long sessionId;
  private Exercise exercise;
  private int numberOfSet;
  private int restTimeInSecond;
  private int index;

  public SessionContentDTOWithExercise(SessionContent sessionContent, Exercise exercise) {
    this.id = sessionContent.getId();
    this.sessionId = sessionContent.getSessionId();
    this.exercise = exercise;
    this.numberOfSet = sessionContent.getNumberOfSet();
    this.restTimeInSecond = sessionContent.getRestTimeInSecond();
    this.index = sessionContent.getIndex();
  }
}
