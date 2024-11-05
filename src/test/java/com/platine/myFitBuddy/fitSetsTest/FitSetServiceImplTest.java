package com.platine.myFitBuddy.fitSetsTest;

import com.platine.myFitBuddy.features.fitSets.repository.FitSetRepository;
import com.platine.myFitBuddy.features.fitSets.service.FitSetServiceImpl;
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
}
