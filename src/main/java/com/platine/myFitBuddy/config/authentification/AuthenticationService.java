package com.platine.myFitBuddy.config.authentification;

import com.platine.myFitBuddy.exceptions.EmailAlreadyExistsException;
import com.platine.myFitBuddy.exceptions.UsernameAlreadyExistsException;
import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.model.DBUserLoginForm;
import com.platine.myFitBuddy.features.dbUsers.model.DBUserRegisterForm;
import com.platine.myFitBuddy.features.dbUsers.repository.DBUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  @Autowired
  private final DBUserRepository userRepository;

  @Autowired
  private final PasswordEncoder passwordEncoder;

  @Autowired
  private final AuthenticationManager authenticationManager;

  public DBUser signup(DBUserRegisterForm form) {
    if (userRepository.findByUsername(form.getUsername()).isPresent()) {
      throw new UsernameAlreadyExistsException(
        "Username already exists: " + form.getUsername()
      );
    }

    if (userRepository.findByEmail(form.getEmail()).isPresent()) {
      throw new EmailAlreadyExistsException("Email already exists: " + form.getEmail());
    }

    DBUser user = new DBUser(
      form.getUsername(),
      form.getEmail(),
      passwordEncoder.encode(form.getPassword()),
      "USER"
    );
    return userRepository.save(user);
  }

  public DBUser authenticate(DBUserLoginForm form) {
    DBUser wantedUser = userRepository
      .findByUsernameOrEmail(form.getUsernameOrEmail())
      .orElseThrow();

    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        wantedUser.getUsername(),
        form.getPassword()
      )
    );

    return userRepository.findByUsername(wantedUser.getUsername()).orElseThrow();
  }
}
