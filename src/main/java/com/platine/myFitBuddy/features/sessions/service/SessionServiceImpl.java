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
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
  private final SessionRepository sessionRepository;

  @Override
  public Optional<FitSession> findById(final long sessionId, final DBUser user) {
    return sessionRepository
      .findById(sessionId)
      .filter(session -> session.getUser().getId() == user.getId());
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
    return sessionRepository
      .findById(updateForm.getSessionId())
      .filter(session -> session.getUser().getId() == user.getId())
      .map(
        session -> {
          session.setName(updateForm.getName()); // met à jour le nom
          return sessionRepository.save(session); // sauvegarde et retourne
        }
      )
      .orElseThrow(
        () -> new IllegalArgumentException("Session not found or unauthorized")
      );
  }

  @Override
  public void delete(long sessionId, final DBUser user) {
    FitSession sessionToDelete = sessionRepository
      .findById(sessionId)
      .filter(session -> session.getUser().getId() == user.getId())
      .orElseThrow(
        () -> new IllegalArgumentException("Session not found or unauthorized")
      );
    sessionRepository.delete(sessionToDelete);
  }

  @Override
  public List<FitSession> findAll() {
    return sessionRepository.findAll();
  }
}
