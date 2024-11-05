package com.platine.myFitBuddy.features.sessionContent.service;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContent;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentCreateForm;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentUpdateForm;
import java.util.List;

public interface SessionContentService {
  List<SessionContent> findByUserIdBySession(final Long sessionId, final DBUser user);

  SessionContent create(final SessionContentCreateForm createForm, final DBUser user);

  SessionContent update(
    SessionContentUpdateForm updateForm,
    Long sessionContentId,
    DBUser user
  );

  SessionContent delete(final Long sessionId, final DBUser user);
}
