package com.platine.myFitBuddy.features.dbUsers.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DBUserRegisterForm {
  @NotBlank
  String username;

  @NotBlank
  @Email
  String email;

  @NotBlank
  @Size(min = 8)
  String password;
}
