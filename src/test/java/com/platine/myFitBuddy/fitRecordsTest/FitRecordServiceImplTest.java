package com.platine.myFitBuddy.fitRecordsTest;

import com.platine.myFitBuddy.features.fitRecords.repository.FitRecordRepository;
import com.platine.myFitBuddy.features.fitRecords.service.FitRecordServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
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
