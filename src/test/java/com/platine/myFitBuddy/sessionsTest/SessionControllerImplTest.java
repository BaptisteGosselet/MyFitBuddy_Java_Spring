package com.platine.myFitBuddy.sessionsTest;

import com.platine.myFitBuddy.features.sessions.controller.SessionControllerImpl;
import com.platine.myFitBuddy.features.sessions.service.SessionService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SessionControllerImplTest {
  @Mock
  SessionService sessionService;

  @InjectMocks
  SessionControllerImpl sessionControllerImpl;

  @Test
  @Disabled
  void findByUserIdBySessionTest() {}

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
