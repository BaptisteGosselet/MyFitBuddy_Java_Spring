package com.platine.myFitBuddy.sessionContentsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContent;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentCreateForm;
import com.platine.myFitBuddy.features.sessionContent.model.SessionContentUpdateForm;
import com.platine.myFitBuddy.features.sessionContent.repository.SessionContentRepository;
import com.platine.myFitBuddy.features.sessionContent.service.SessionContentServiceImpl;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SessionContentServiceImplTest {
  @Mock
  SessionContentRepository sessionContentRepository;

  @InjectMocks
  SessionContentServiceImpl sessionContentServiceImpl;

  @Test
  void findByUserIdBySessionTest() {
    long sessionId = 1;
    DBUser user = new DBUser("username", "email", "password", "role");
    List<SessionContent> listSessionContentFromService = List.of(
      new SessionContent(1L, 1L, 1, 1, 1, user),
      new SessionContent(1L, 2L, 2, 2, 2, user)
    );

    when(sessionContentRepository.findSessionContentBySessionIdAndUser(anyLong(), any()))
      .thenReturn(listSessionContentFromService);

    List<SessionContent> sessionFromService = sessionContentServiceImpl.findByUserIdBySession(
      sessionId,
      user
    );

    verify(sessionContentRepository)
      .findSessionContentBySessionIdAndUser(sessionId, user);
    for (int i = 0; i < sessionFromService.size(); i++) {
      assertEquals(
        listSessionContentFromService.get(i).getId(),
        sessionFromService.get(i).getId()
      );
      assertEquals(
        listSessionContentFromService.get(i).getSessionId(),
        sessionFromService.get(i).getSessionId()
      );
      assertEquals(
        listSessionContentFromService.get(i).getExerciseId(),
        sessionFromService.get(i).getExerciseId()
      );
      assertEquals(
        listSessionContentFromService.get(i).getIndex(),
        sessionFromService.get(i).getIndex()
      );
      assertEquals(
        listSessionContentFromService.get(i).getNumberOfSet(),
        sessionFromService.get(i).getNumberOfSet()
      );
      assertEquals(
        listSessionContentFromService.get(i).getRestTimeInSecond(),
        sessionFromService.get(i).getRestTimeInSecond()
      );
    }
  }

  @Test
  void createTest() {
    DBUser user = new DBUser("username", "email", "password", "role");
    SessionContentCreateForm sessionContentCreateForm = new SessionContentCreateForm(
      1L,
      1L,
      1,
      1
    );
    SessionContent sessionContentExpected = new SessionContent(1L, 1L, 1, 1, 2, user);

    when(
        sessionContentRepository.findMaxIndexBySessionId(
          sessionContentCreateForm.getSessionId()
        )
      )
      .thenReturn(1);
    when(sessionContentRepository.save(any())).thenReturn(sessionContentExpected);

    SessionContent sessionContentFromService = sessionContentServiceImpl.create(
      sessionContentCreateForm,
      user
    );

    verify(sessionContentRepository)
      .findMaxIndexBySessionId(sessionContentCreateForm.getSessionId());
    verify(sessionContentRepository).save(any());
    assertEquals(sessionContentExpected.getId(), sessionContentFromService.getId());
    assertEquals(
      sessionContentExpected.getExerciseId(),
      sessionContentFromService.getExerciseId()
    );
    assertEquals(sessionContentExpected.getIndex(), sessionContentFromService.getIndex());
    assertEquals(
      sessionContentExpected.getNumberOfSet(),
      sessionContentFromService.getNumberOfSet()
    );
    assertEquals(
      sessionContentExpected.getRestTimeInSecond(),
      sessionContentFromService.getRestTimeInSecond()
    );
  }

  @Test
  void updateTest() {
    DBUser user = new DBUser("username", "email", "password", "role");
    SessionContentUpdateForm sessionContentUpdateForm = new SessionContentUpdateForm(
      1L,
      1,
      1,
      1
    );
    SessionContent sessionContentExpected = new SessionContent(1L, 1L, 1, 1, 2, user);

    when(sessionContentRepository.save(any())).thenReturn(sessionContentExpected);

    SessionContent sessionContentFromService = sessionContentServiceImpl.update(
      sessionContentUpdateForm,
      1L,
      user
    );

    verify(sessionContentRepository).save(any());
    assertEquals(sessionContentExpected.getId(), sessionContentFromService.getId());
    assertEquals(
      sessionContentExpected.getExerciseId(),
      sessionContentFromService.getExerciseId()
    );
    assertEquals(sessionContentExpected.getIndex(), sessionContentFromService.getIndex());
    assertEquals(
      sessionContentExpected.getNumberOfSet(),
      sessionContentFromService.getNumberOfSet()
    );
    assertEquals(
      sessionContentExpected.getRestTimeInSecond(),
      sessionContentFromService.getRestTimeInSecond()
    );
  }

  @Test
  void deleteTest() {
    long id = 1L;
    DBUser user = new DBUser("username", "email", "password", "role");

    doNothing().when(sessionContentRepository).deleteByIdAndUser(id, user);

    sessionContentServiceImpl.delete(id, user);

    verify(sessionContentRepository).deleteByIdAndUser(id, user);
  }
}
