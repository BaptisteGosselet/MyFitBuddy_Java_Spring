package com.platine.myFitBuddy.features.sessionContent.service;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.sessionContent.mapper.SessionContentMapper;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContent;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentCreateForm;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentUpdateForm;
import com.platine.myFitBuddy.features.sessionContent.repository.SessionContentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SessionContentServiceImpl implements SessionContentService {
  @Autowired
  private final SessionContentRepository sessionContentRepository;

  @Override
  public List<SessionContent> findByUserIdBySession(Long sessionId, DBUser user) {
    return sessionContentRepository.findSessionContentBySessionIdAndUser(sessionId, user);
  }

  @Override
  public SessionContent create(SessionContentCreateForm createForm, DBUser user) {
    return sessionContentRepository.save(SessionContentMapper.mapFromCreateForm(createForm, user));
  }

  @Override
  public SessionContent update(SessionContentUpdateForm updateForm, Long sessionContentId, DBUser user) {
    return sessionContentRepository.save(SessionContentMapper.mapFromUpdateForm(updateForm, sessionContentId, user));
  }

  @Override
  public SessionContent delete(Long sessionId, DBUser user) {
    return null;
  }
}
