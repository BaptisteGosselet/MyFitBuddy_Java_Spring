package com.platine.myFitBuddy.sessionContentsTest;

import com.platine.myFitBuddy.features.sessionContent.repository.SessionContentRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SessionContentServiceImpl {
  @Mock
  SessionContentRepository sessionContentRepository;

  @InjectMocks
  SessionContentServiceImpl sessionContentServiceImpl;

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
