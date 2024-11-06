package com.platine.myFitBuddy.features.fitSets.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class FitSetCreateForm {

  @NotNull
  private long idRecord;

  @NotNull
  private long idExercise;

  private int nbOrder;

  @NotNull
  private int nbRep;

  @NotNull
  private int weight;

  @Min(value = 1, message = "Feeling must be at least 1")
  @Max(value = 3, message = "Feeling must not exceed 3")
  private int feeling;
}
