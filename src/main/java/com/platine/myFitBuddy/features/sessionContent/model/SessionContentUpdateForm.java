package com.platine.myFitBuddy.features.sessionContent.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SessionContentUpdateForm {
  private int numberOfSet;
  private int restTimeInSecond;
  private int index;
}
