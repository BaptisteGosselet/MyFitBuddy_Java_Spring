package com.platine.myFitBuddy.features.sessionContent.mapper;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContent;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentCreateForm;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentDTO;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentUpdateForm;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SessionContentMapper {

  public static SessionContent mapFromCreateForm(
    final SessionContentCreateForm sessionContentCreateForm,
    final DBUser user
  ) {
    SessionContent sessionContent = new SessionContent();
    sessionContent.setSessionId(sessionContentCreateForm.getSessionId());
    sessionContent.setExerciseId(sessionContentCreateForm.getExerciseId());
    sessionContent.setNumberOfSet(sessionContentCreateForm.getNumberOfSet());
    sessionContent.setRestTimeInSecond(sessionContentCreateForm.getRestTimeInSecond());
    sessionContent.setUser(user);
    return sessionContent;
  }

  public static SessionContent mapFromUpdateForm(
    final SessionContentUpdateForm sessionContentUpdateForm,
    final Long sessionContentId,
    final DBUser user
  ) {
    SessionContent sessionContent = new SessionContent();
    sessionContent.setId(sessionContentId);
    sessionContent.setNumberOfSet(sessionContentUpdateForm.getNumberOfSet());
    sessionContent.setRestTimeInSecond(sessionContentUpdateForm.getRestTimeInSecond());
    return sessionContent;
  }

  public static SessionContentDTO mapToDTO(final SessionContent sessionContent) {
    SessionContentDTO sessionContentDTO = new SessionContentDTO();
    sessionContentDTO.setSessionId(sessionContent.getSessionId());
    sessionContentDTO.setExerciseId(sessionContent.getExerciseId());
    sessionContentDTO.setNumberOfSet(sessionContent.getNumberOfSet());
    sessionContentDTO.setRestTimeInSecond(sessionContent.getRestTimeInSecond());
    return sessionContentDTO;
  }
}
