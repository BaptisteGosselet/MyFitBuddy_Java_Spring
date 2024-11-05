package com.platine.myFitBuddy.features.sessionContent.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SessionContentCreateForm {
  @NotNull
  private Long sessionId;

  @NotNull
  private Long exerciseId;

  @NotNull
  private int index;

  @NotNull
  private int numberOfSet;

  @NotNull
  private int restTimeInSecond;
}
