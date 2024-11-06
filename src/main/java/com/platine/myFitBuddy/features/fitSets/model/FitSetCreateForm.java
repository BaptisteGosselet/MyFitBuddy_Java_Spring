package com.platine.myFitBuddy.features.fitSets.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FitSetCreateForm {
  @NotNull
  private Long idRecord;

  @NotNull
  private Long idExercise;

  private Integer nbOrder;

  @NotNull
  private Integer nbRep;

  @NotNull
  private Integer weight;

  @Min(value = 1, message = "Feeling must be at least 1")
  @Max(value = 3, message = "Feeling must not exceed 3")
  private Integer feeling;
}
