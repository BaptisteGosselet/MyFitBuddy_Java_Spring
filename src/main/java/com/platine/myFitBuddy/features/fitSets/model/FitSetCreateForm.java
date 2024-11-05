package com.platine.myFitBuddy.features.fitSets.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FitSetCreateForm {
  private long idRecord;
  private long idExercise;
  private int nbOrder;
}
