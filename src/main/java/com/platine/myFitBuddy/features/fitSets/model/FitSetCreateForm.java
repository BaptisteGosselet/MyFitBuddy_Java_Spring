package com.platine.myFitBuddy.features.fitSets.model;

import com.platine.myFitBuddy.utils.MyUtils;
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
}
