package com.platine.myFitBuddy.features.sessionContent.mapper;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContent;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentCreateForm;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentDTO;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentUpdateForm;
import lombok.experimental.UtilityClass;
import org.springframework.security.access.AccessDeniedException;

@UtilityClass
public class SessionContentMapper {

  public static SessionContent mapFromCreateForm(
    final SessionContentCreateForm sessionContentCreateForm,
    final int index,
    final DBUser user
  ) {
    SessionContent sessionContent = new SessionContent();
    sessionContent.setSessionId(sessionContentCreateForm.getSessionId());
    sessionContent.setExerciseId(sessionContentCreateForm.getExerciseId());
    sessionContent.setNumberOfSet(sessionContentCreateForm.getNumberOfSet());
    sessionContent.setRestTimeInSecond(sessionContentCreateForm.getRestTimeInSecond());
    sessionContent.setIndex(index);
    sessionContent.setUser(user);
    return sessionContent;
  }

  public static SessionContent mapFromUpdateForm(
    final SessionContentUpdateForm sessionContentUpdateForm,
    SessionContent sessionInDB,
    final DBUser user
  )
    throws AccessDeniedException {
    if (user.getId() != sessionInDB.getUser().getId()) {
      throw new AccessDeniedException("Access denied");
    }
    sessionInDB.setNumberOfSet(sessionContentUpdateForm.getNumberOfSet());
    sessionInDB.setRestTimeInSecond(sessionContentUpdateForm.getRestTimeInSecond());
    sessionInDB.setIndex(sessionContentUpdateForm.getIndex());
    return sessionInDB;
  }

  public static SessionContentDTO mapToDTO(final SessionContent sessionContent) {
    SessionContentDTO sessionContentDTO = new SessionContentDTO();
    sessionContentDTO.setId(sessionContent.getId());
    sessionContentDTO.setSessionId(sessionContent.getSessionId());
    sessionContentDTO.setExerciseId(sessionContent.getExerciseId());
    sessionContentDTO.setNumberOfSet(sessionContent.getNumberOfSet());
    sessionContentDTO.setRestTimeInSecond(sessionContent.getRestTimeInSecond());
    sessionContentDTO.setIndex(sessionContent.getIndex());
    return sessionContentDTO;
  }
}
