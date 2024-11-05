package com.platine.myFitBuddy.features.fitSets.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FitSetUpdateForm {
  private long idFitSet;
  private long idExercise;
  private int nbOrder;
}
