package com.platine.myFitBuddy.config.authentification;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.model.DBUserLoginForm;
import com.platine.myFitBuddy.features.dbUsers.model.DBUserRegisterForm;
import com.platine.myFitBuddy.features.dbUsers.service.DBUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
  private final JwtService jwtService;
  private final AuthenticationService authenticationService;
  private final DBUserService userService;

  @PostMapping("/signup")
  public ResponseEntity<String> register(@RequestBody DBUserRegisterForm registerForm) {
    System.out.println(registerForm.getUsername() + registerForm.getPassword());
    authenticationService.signup(registerForm);
    return ResponseEntity.ok("User registered successfully.");
  }

  @PostMapping("/signin")
  public ResponseEntity<JwtResponse> authenticate(
    @RequestBody DBUserLoginForm loginForm
  ) {
    DBUser authenticatedUser = authenticationService.authenticate(loginForm);

    String jwtToken = jwtService.generateToken(authenticatedUser);

    JwtResponse jwtResponse = new JwtResponse(
      jwtToken,
      jwtService.getAccessExpirationTime()
    );

    return ResponseEntity.ok(jwtResponse);
  }

  @PostMapping("/refresh-token")
  public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String token) {
    if (token != null && token.startsWith("Bearer ")) {
      String refreshToken = token.substring(7);
      UserDetails userDetails = userService.getCurrentUser();
      if (jwtService.isTokenValid(refreshToken, userDetails)) {
        String newAccessToken = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(newAccessToken, refreshToken));
      }
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid refresh token");
  }
}
