package com.platine.myFitBuddy.fitSetsTest;

import com.platine.myFitBuddy.features.fitSets.repository.FitSetRepository;
import com.platine.myFitBuddy.features.fitSets.service.FitSetServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FitSetServiceImplTest {
  @Mock
  FitSetRepository fitSetRepository;

  @InjectMocks
  FitSetServiceImpl fitSetServiceImpl;

  @Test
  @Disabled
  void getSetByIdTest() {}

  @Test
  @Disabled
  void getSetByIdTestWrongUser() {}

  @Test
  @Disabled
  void getSetsbyUserTest() {}

  @Test
  @Disabled
  void addSetToSessionTest() {}

  @Test
  @Disabled
  void addSetToSessionWrongUserTest() {}

  @Test
  @Disabled
  void updateSetTest() {}

  @Test
  @Disabled
  void updateSetWrongUserTest() {}

  @Test
  @Disabled
  void deleteSetTest() {}

  @Test
  @Disabled
  void deleteSetWrongUserTest() {}
}
