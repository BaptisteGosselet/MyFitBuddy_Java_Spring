package com.platine.myFitBuddy.sessionsTest;

import com.platine.myFitBuddy.features.sessions.controller.SessionControllerImpl;
import com.platine.myFitBuddy.features.sessions.repository.SessionRepository;
import com.platine.myFitBuddy.features.sessions.service.SessionServiceImpl;
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

  @Test
  @Disabled
  void findByIdTest() {}

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
