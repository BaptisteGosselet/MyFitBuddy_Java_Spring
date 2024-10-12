package com.platine.myFitBuddy.config.authentification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
  private String accessToken;
  private String refreshToken;
  private long expiresIn;

  public JwtResponse(String accessToken, long expiresIn) {
    this.accessToken = accessToken;
    this.expiresIn = expiresIn;
  }

  public JwtResponse(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }
}
