package com.platine.myFitBuddy.sessionContentsTest;

import com.platine.myFitBuddy.features.sessionContent.controller.SessionContentControllerImpl;
import com.platine.myFitBuddy.features.sessionContent.service.SessionContentService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SessionContentControllerImplTest {
  @Mock
  SessionContentService sessionContentService;

  @InjectMocks
  SessionContentControllerImpl sessionContentControllerImpl;

  @Test
  @Disabled
  void findByUserBySessionIdTest() {}

  @Test
  @Disabled
  void createTest() {}

  @Test
  @Disabled
  void updateTest() {}

  @Test
  @Disabled
  void deleteTest() {}

  @Test
  @Disabled
  void changeOrderTest() {}
}
