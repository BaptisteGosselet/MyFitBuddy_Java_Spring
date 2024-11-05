package com.platine.myFitBuddy.features.sessions.service;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.sessions.model.FitSession;
import com.platine.myFitBuddy.features.sessions.model.SessionCreateForm;
import com.platine.myFitBuddy.features.sessions.model.SessionUpdateForm;
import com.platine.myFitBuddy.features.sessions.repository.SessionRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
  @Autowired
  private final SessionRepository sessionRepository;

  @Override
  public Optional<FitSession> findById(final long sessionId, final DBUser user) {
    Optional<FitSession> foundedSession = sessionRepository.findById(sessionId);
    if (
      foundedSession.isPresent() && foundedSession.get().getUser().getId() == user.getId()
    ) {
      return foundedSession;
    } else {
      return Optional.empty();
    }
  }

  @Override
  public List<FitSession> findByUserId(final DBUser user) {
    return sessionRepository.findByUserId(user.getId());
  }

  @Override
  public FitSession create(SessionCreateForm createForm, final DBUser user) {
    FitSession sessionToCreate = new FitSession(createForm.getName(), user);
    return sessionRepository.save(sessionToCreate);
  }

  @Override
  public FitSession update(SessionUpdateForm updateForm, final DBUser user) {
    FitSession sessionToUpdate = new FitSession(updateForm.getName(), user);
    return sessionRepository.save(sessionToUpdate);
  }

  @Override
  public void delete(long sessionId) {
    sessionRepository.deleteById(sessionId);
  }

  @Override
  public List<FitSession> findAll() {
    return sessionRepository.findAll();
  }
}
