package com.platine.myFitBuddy.config.authentification;

import com.platine.myFitBuddy.exceptions.EmailAlreadyExistsException;
import com.platine.myFitBuddy.exceptions.UsernameAlreadyExistsException;
import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.model.DBUserLoginForm;
import com.platine.myFitBuddy.features.dbUsers.model.DBUserRegisterForm;
import com.platine.myFitBuddy.features.dbUsers.repository.DBUserRepository;
import com.platine.myFitBuddy.utils.MyUtils;
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

  public DBUser signup(final DBUserRegisterForm form) {
    String formalUsername = MyUtils.normalize(form.getUsername());
    String formalEmail = MyUtils.normalize(form.getEmail());

    if (userRepository.findByUsername(formalUsername).isPresent()) {
      throw new UsernameAlreadyExistsException(
        "Username already exists: " + formalUsername
      );
    }

    if (userRepository.findByEmail(formalEmail).isPresent()) {
      throw new EmailAlreadyExistsException("Email already exists: " + formalEmail);
    }

    DBUser user = new DBUser(
      formalUsername,
      formalEmail,
      passwordEncoder.encode(form.getPassword()),
      "USER"
    );
    return userRepository.save(user);
  }

  public DBUser authenticate(final DBUserLoginForm form) {
    String formalUsernameOrEmail = MyUtils.normalize(form.getUsernameOrEmail());

    DBUser wantedUser = userRepository
      .findByUsernameOrEmail(formalUsernameOrEmail)
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
