package com.platine.myFitBuddy.sessionsTest;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.service.DBUserService;
import com.platine.myFitBuddy.features.sessions.controller.SessionControllerImpl;
import com.platine.myFitBuddy.features.sessions.model.FitSession;
import com.platine.myFitBuddy.features.sessions.service.SessionService;
import org.junit.jupiter.api.BeforeAll;
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

  @Mock
  DBUserService dbUserService;

  @InjectMocks
  SessionControllerImpl sessionControllerImpl;

  @Test
  @Disabled
  void findByUserIdBySessionTest() {
    //when(dbUserService.getCurrentUser()).thenReturn(this.user);
  }

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
