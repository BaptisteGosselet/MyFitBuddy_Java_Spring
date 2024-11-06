package com.platine.myFitBuddy.features.fitSets.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FitSetUpdateForm {
  private Long idFitSet;
  private Long idExercise;
  private Integer nbOrder;
  private Integer nbRep;
  private Integer weight;
  private Integer feeling;
}
