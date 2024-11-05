package com.platine.myFitBuddy.fitRecordsTest;

import com.platine.myFitBuddy.features.fitRecords.repository.FitRecordRepository;
import com.platine.myFitBuddy.features.fitRecords.service.FitRecordServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FitRecordServiceImplTest {
  @Mock
  FitRecordRepository fitRecordRepository;

  @InjectMocks
  FitRecordServiceImpl fitRecordServiceImpl;
}
