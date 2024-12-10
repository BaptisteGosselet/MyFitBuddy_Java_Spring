package com.platine.myFitBuddy.features.sessionContent.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionContentUpdateForm {
  private Long id;
  private int numberOfSet;
  private int restTimeInSecond;
  private int index;
}
