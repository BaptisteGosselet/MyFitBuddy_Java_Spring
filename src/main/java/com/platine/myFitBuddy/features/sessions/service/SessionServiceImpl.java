package com.platine.myFitBuddy.features.sessions.service;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.sessions.model.Session;
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
  public Optional<Session> findById(final long sessionId, final DBUser user) {
    Optional<Session> foundedSession = sessionRepository.findById(sessionId);
    if (
      foundedSession.isPresent() && foundedSession.get().getUser().getId() == user.getId()
    ) {
      return foundedSession;
    } else {
      return Optional.empty();
    }
  }

  @Override
  public List<Session> findByUserId(final DBUser user) {
    return sessionRepository.findByUserId(user.getId());
  }

  @Override
  public Session create(SessionCreateForm createForm, final DBUser user) {
    Session sessionToCreate = new Session(createForm.getName(), user);
    return sessionRepository.save(sessionToCreate);
  }

  @Override
  public Session update(SessionUpdateForm updateForm, final DBUser user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public Session delete(long sessionId, final DBUser user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public List<Session> findAll() {
    return sessionRepository.findAll();
  }
}
