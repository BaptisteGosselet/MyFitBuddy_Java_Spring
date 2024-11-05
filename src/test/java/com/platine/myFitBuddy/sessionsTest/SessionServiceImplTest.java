package com.platine.myFitBuddy.sessionsTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.sessions.model.FitSession;
import com.platine.myFitBuddy.features.sessions.model.SessionCreateForm;
import com.platine.myFitBuddy.features.sessions.model.SessionUpdateForm;
import com.platine.myFitBuddy.features.sessions.repository.SessionRepository;
import com.platine.myFitBuddy.features.sessions.service.SessionServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SessionServiceImplTest {
  @Mock
  SessionRepository sessionRepository;

  @InjectMocks
  SessionServiceImpl sessionServiceImpl;

  FitSession dummySession;
  DBUser dummyUser;

  @BeforeEach
  void init() {
    dummyUser = new DBUser();
    dummyUser.setId(1);
    dummySession = new FitSession("dummy", dummyUser);
  }

  @Test
  void findByIdTest() {
    final long sessionId = 1;

    when(sessionRepository.findById(sessionId)).thenReturn(Optional.of(dummySession));

    FitSession result = sessionServiceImpl.findById(sessionId, dummyUser).get();

    assertThat(result).isNotNull().isEqualTo(dummySession);

    verify(sessionRepository).findById(sessionId);
    verifyNoMoreInteractions(sessionRepository);
  }

  @Test
  void findByIdTestWrongUser() {
    final long sessionId = 1;
    DBUser otherUser = new DBUser();
    otherUser.setId(2);

    when(sessionRepository.findById(sessionId)).thenReturn(Optional.of(dummySession));

    Optional<FitSession> result = sessionServiceImpl.findById(sessionId, otherUser);

    assertThat(result).isEmpty();

    verify(sessionRepository).findById(sessionId);
    verifyNoMoreInteractions(sessionRepository);
  }

  @Test
  void findByUserTest() {
    when(sessionRepository.findByUserId(dummyUser.getId()))
      .thenReturn(List.of(dummySession));

    List<FitSession> result = sessionServiceImpl.findByUserId(dummyUser);

    assertThat(result).isNotEmpty().contains(dummySession);

    verify(sessionRepository).findByUserId(dummyUser.getId());
    verifyNoMoreInteractions(sessionRepository);
  }

  @Test
  void getAllSessionsTest() {
    when(sessionRepository.findAll()).thenReturn(List.of(dummySession));

    List<FitSession> result = sessionServiceImpl.findAll();

    assertThat(result).isNotEmpty().contains(dummySession);

    verify(sessionRepository).findAll();
    verifyNoMoreInteractions(sessionRepository);
  }

  @Test
  void createTest() {
    SessionCreateForm createForm = new SessionCreateForm("new session");
    FitSession newSession = new FitSession(createForm.getName(), dummyUser);

    when(sessionRepository.save(any(FitSession.class))).thenReturn(newSession);

    FitSession result = sessionServiceImpl.create(createForm, dummyUser);

    assertThat(result).isNotNull().isEqualTo(newSession);

    verify(sessionRepository).save(any(FitSession.class));
    verifyNoMoreInteractions(sessionRepository);
  }

  @Test
  void updateTest() {
    final long sessionId = 1;
    SessionUpdateForm updateForm = new SessionUpdateForm(sessionId, "updated session");
    dummySession.setName("updated session");

    when(sessionRepository.findById(sessionId)).thenReturn(Optional.of(dummySession));
    when(sessionRepository.save(dummySession)).thenReturn(dummySession);

    FitSession result = sessionServiceImpl.update(updateForm, dummyUser);

    assertThat(result).isNotNull().isEqualTo(dummySession);
    assertThat(result.getName()).isEqualTo("updated session");

    verify(sessionRepository).findById(sessionId);
    verify(sessionRepository).save(dummySession);
    verifyNoMoreInteractions(sessionRepository);
  }

  @Test
  void deleteTest() {
    final long sessionId = 1;

    when(sessionRepository.findById(sessionId)).thenReturn(Optional.of(dummySession));

    sessionServiceImpl.delete(sessionId, dummyUser);

    verify(sessionRepository).findById(sessionId);
    verify(sessionRepository).delete(dummySession);
    verifyNoMoreInteractions(sessionRepository);
  }
}
