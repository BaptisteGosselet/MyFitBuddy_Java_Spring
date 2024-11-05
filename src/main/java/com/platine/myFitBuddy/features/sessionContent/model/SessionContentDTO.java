package com.platine.myFitBuddy.features.sessionContent.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionContentDTO {
  private Long id;
  private Long sessionId;
  private Long exerciseId;
  private int numberOfSet;
  private int restTimeInSecond;
}
