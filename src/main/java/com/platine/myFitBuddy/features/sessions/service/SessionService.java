package com.platine.myFitBuddy.features.sessions.service;

import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.sessions.model.Session;
import com.platine.myFitBuddy.features.sessions.model.SessionCreateForm;
import com.platine.myFitBuddy.features.sessions.model.SessionUpdateForm;

public interface SessionService {
    
    Optional<Session> findById(final long sessionId, final DBUser user);

    List<Session> findByUserId(final DBUser user);

    List<Session> findAll();

    Session create(final SessionCreateForm createForm, final DBUser user);

    Session update(final SessionUpdateForm updateForm, final DBUser user);

    Session delete(final long sessionId, final DBUser user);


}