package com.platine.myFitBuddy.features.fitSets.model;

import com.platine.myFitBuddy.utils.MyUtils;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

  @Min(value = MyUtils.SET_FEELING_MIN)
  @Max(value = MyUtils.SET_FEELING_MAX)
  private Integer feeling;
}
