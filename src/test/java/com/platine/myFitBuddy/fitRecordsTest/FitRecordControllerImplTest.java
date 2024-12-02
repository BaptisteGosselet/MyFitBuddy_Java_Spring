package com.platine.myFitBuddy.fitRecordsTest;

import com.platine.myFitBuddy.features.fitRecords.controller.FitRecordControllerImpl;
import com.platine.myFitBuddy.features.fitRecords.service.FitRecordService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
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

  @Test
  @Disabled
  void getRecordByIdTest() {}

  @Test
  @Disabled
  void getRecordByIdWrongUserTest() {}

  @Test
  @Disabled
  void getRecordByUserTest() {}

  @Test
  @Disabled
  void createRecordTest() {}

  @Test
  @Disabled
  void setFeelingNote() {}

  @Test
  @Disabled
  void deleteRecordTest() {}

  @Test
  @Disabled
  void deleteRecordWrongUserTest() {}
}
