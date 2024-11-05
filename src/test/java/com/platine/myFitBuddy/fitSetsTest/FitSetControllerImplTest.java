package com.platine.myFitBuddy.fitSetsTest;

import com.platine.myFitBuddy.features.fitSets.controller.FitSetControllerImpl;
import com.platine.myFitBuddy.features.fitSets.service.FitSetService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FitSetControllerImplTest {
  @Mock
  FitSetService fitSetService;

  @InjectMocks
  FitSetControllerImpl fitSetControllerImpl;
}
