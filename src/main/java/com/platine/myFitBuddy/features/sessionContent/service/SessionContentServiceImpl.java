package com.platine.myFitBuddy.features.sessionContent.service;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.sessionContent.mapper.SessionContentMapper;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContent;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentCreateForm;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentUpdateForm;
import com.platine.myFitBuddy.features.sessionContent.repository.SessionContentRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class SessionContentServiceImpl implements SessionContentService {
  @Autowired
  private final SessionContentRepository sessionContentRepository;

  @Override
  public SessionContent findById(long sessionId) {
    return sessionContentRepository.findById(sessionId).orElse(null);
  }

  @Override
  public List<SessionContent> findByUserIdBySession(Long sessionId, DBUser user) {
    return sessionContentRepository.findSessionContentBySessionIdAndUser(sessionId, user);
  }

  @Override
  public SessionContent create(SessionContentCreateForm createForm, DBUser user) {
    Integer indexMax = sessionContentRepository.findMaxIndexBySessionId(createForm.getSessionId());
    int indexMaxPlus1 = indexMax != null ? indexMax + 1 : 1;
    return sessionContentRepository.save(
      SessionContentMapper.mapFromCreateForm(createForm, indexMaxPlus1, user)
    );
  }

  @Override
  public SessionContent update(
    SessionContentUpdateForm updateForm,
    Long sessionContentId,
    DBUser user
  ) {
    updateForm.setId(sessionContentId);
    return sessionContentRepository.save(
      SessionContentMapper.mapFromUpdateForm(
        updateForm,
        this.findById(sessionContentId),
        user
      )
    );
  }

  @Override
  public void delete(Long id, DBUser user) {
    sessionContentRepository.deleteByIdAndUser(id, user);
  }
}
