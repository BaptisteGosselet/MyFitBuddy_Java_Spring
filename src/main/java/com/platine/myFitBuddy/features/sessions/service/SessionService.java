package com.platine.myFitBuddy.features.sessions.service;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.sessions.model.FitSession;
import com.platine.myFitBuddy.features.sessions.model.SessionCreateForm;
import com.platine.myFitBuddy.features.sessions.model.SessionUpdateForm;
import java.util.List;
import java.util.Optional;

public interface SessionService {
  Optional<FitSession> findById(final long sessionId, final DBUser user);

  List<FitSession> findByUserId(final DBUser user);

  List<FitSession> findAll();

  FitSession create(final SessionCreateForm createForm, final DBUser user);

  FitSession update(final SessionUpdateForm updateForm, final DBUser user);

  void delete(final long sessionId);
}
