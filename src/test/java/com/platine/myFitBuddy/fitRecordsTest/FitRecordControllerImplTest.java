package com.platine.myFitBuddy.fitRecordsTest;

import com.platine.myFitBuddy.features.fitRecords.controller.FitRecordControllerImpl;
import com.platine.myFitBuddy.features.fitRecords.service.FitRecordService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FitRecordControllerImplTest {
  @Mock
  FitRecordService fitRecordService;

  @InjectMocks
  FitRecordControllerImpl fitRecordControllerImpl;
}
