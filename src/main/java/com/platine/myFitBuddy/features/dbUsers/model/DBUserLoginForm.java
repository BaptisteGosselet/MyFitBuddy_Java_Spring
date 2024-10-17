package com.platine.myFitBuddy.features.dbUsers.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DBUserLoginForm {
  @NotBlank
  String usernameOrEmail;

  @NotBlank
  String password;
}
