package com.platine.myFitBuddy.sessionsTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.sessions.model.FitSession;
import com.platine.myFitBuddy.features.sessions.repository.SessionRepository;
import com.platine.myFitBuddy.features.sessions.service.SessionServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

  FitSession generateDummySession(DBUser user) {
    FitSession session = new FitSession("dummy", user);
    return session;
  }

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
  @Disabled
  void findByIdTestWrongUser() {}

  @Test
  @Disabled
  void findByUserTest() {}

  @Test
  @Disabled
  void getAllSessionsTest() {}

  @Test
  @Disabled
  void createTest() {}

  @Test
  @Disabled
  void updateTest() {}

  @Test
  @Disabled
  void deleteTest() {}
}
