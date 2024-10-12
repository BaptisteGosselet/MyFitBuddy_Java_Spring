package com.platine.myFitBuddy.config.authentification;

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
        DBUser user = new DBUser();
        user.setUsername(form.getUsername());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        return userRepository.save(user);
    }

    public DBUser authenticate(DBUserLoginForm form) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        form.getUsername(),
                        form.getPassword()
                )
        );

        return userRepository.findByUsername(form.getUsername())
                .orElseThrow();
    }
}