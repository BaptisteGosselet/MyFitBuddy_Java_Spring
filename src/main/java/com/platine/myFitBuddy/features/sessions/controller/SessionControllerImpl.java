package com.platine.myFitBuddy.features.sessions.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.service.DBUserServiceImpl;
import com.platine.myFitBuddy.features.sessions.model.Session;
import com.platine.myFitBuddy.features.sessions.model.SessionCreateForm;
import com.platine.myFitBuddy.features.sessions.service.SessionServiceImpl;


import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SessionControllerImpl implements SessionController {
    
    private final SessionServiceImpl sessionService;
    private final DBUserServiceImpl dbUserService;

    //TODO : duplication de code avec DBUserController (probablement déplacer dans userservice)
    public DBUser getCurrentDBUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DBUser currentUser = (DBUser) authentication.getPrincipal();
        return currentUser;
    }

    @Override
    public ResponseEntity<Session> findById(final long sessionId) {
        DBUser user = getCurrentDBUser();
        Optional<Session> sessionOptional = sessionService.findById(sessionId, user);
        if (!sessionOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sessionOptional.get());
    }

    @Override
    public ResponseEntity<List<Session>> findByUser() {
        DBUser user = getCurrentDBUser();
        List<Session> sessionsList = sessionService.findByUserId(user);
        return ResponseEntity.ok(sessionsList);
    }

    @Override
    public ResponseEntity<Session> create(final SessionCreateForm createForm) {
        DBUser user = getCurrentDBUser();
        Session createdSession = sessionService.create(createForm, user);
        return ResponseEntity.ok(createdSession);
    }

    @Override
    public ResponseEntity<List<Session>> getAllSessions() {
        return ResponseEntity.ok(sessionService.findAll());
    }
    

}

