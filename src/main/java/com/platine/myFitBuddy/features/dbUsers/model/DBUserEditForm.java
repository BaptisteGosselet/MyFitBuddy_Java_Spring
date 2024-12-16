package com.platine.myFitBuddy.features.dbUsers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DBUserEditForm {
  private final String username;
  private final String email;
}
