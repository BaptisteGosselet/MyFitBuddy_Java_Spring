package com.platine.myFitBuddy.config.authentification;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
  private String accessToken;
  private String refreshToken;

  private Date accessExpirationDate;
  private Date refreshExpirationDate;

  public JwtResponse(String accessToken, long accessExpiresIn) {
    this.accessToken = accessToken;
    this.accessExpirationDate = new Date(System.currentTimeMillis() + accessExpiresIn);
  }

  public JwtResponse(
    String accessToken,
    long accessExpiresIn,
    String refreshToken,
    long refreshExpiresIn
  ) {
    this.accessToken = accessToken;
    this.accessExpirationDate = new Date(System.currentTimeMillis() + accessExpiresIn);
    this.refreshToken = refreshToken;
    this.refreshExpirationDate = new Date(System.currentTimeMillis() + refreshExpiresIn);
  }
}
