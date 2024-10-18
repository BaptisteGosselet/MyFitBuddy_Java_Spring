package com.platine.myFitBuddy.config.authentification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
  private String accessToken;
  private String refreshToken;
  private long accessExpiresIn;
  private long refreshExpiresIn;

  public JwtResponse(String accessToken, long accessExpiresIn) {
    this.accessToken = accessToken;
    this.accessExpiresIn = accessExpiresIn;
  }

  public JwtResponse(
    String accessToken,
    long accessExpiresIn,
    String refreshToken,
    long refreshExpiresIn
  ) {
    this.accessToken = accessToken;
    this.accessExpiresIn = accessExpiresIn;
    this.refreshToken = refreshToken;
    this.refreshExpiresIn = refreshExpiresIn;
  }
}
