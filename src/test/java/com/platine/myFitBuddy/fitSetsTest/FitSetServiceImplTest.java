package com.platine.myFitBuddy.fitSetsTest;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.exercices.model.Exercise;
import com.platine.myFitBuddy.features.exercices.model.MuscleGroup;
import com.platine.myFitBuddy.features.fitRecords.model.FitRecord;
import com.platine.myFitBuddy.features.fitSets.model.FitSet;
import com.platine.myFitBuddy.features.fitSets.repository.FitSetRepository;
import com.platine.myFitBuddy.features.fitSets.service.FitSetServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FitSetServiceImplTest {
  @Mock
  FitSetRepository fitSetRepository;

  @InjectMocks
  FitSetServiceImpl fitSetServiceImpl;

  @Test
  @Disabled
  void getSetByIdTest() {
    long setId = 1L;
    DBUser user = new DBUser("username","email","password","role");
    FitSet fitSetExpected = new FitSet(new FitRecord("name",user),new Exercise("key", MuscleGroup.ARMS,"en","fr"),1,1,1);
    when(fitSetRepository.findById(setId)).thenReturn(Optional.of(fitSetExpected));

    Optional<FitSet> fitSetFromService = fitSetServiceImpl.getSetById(setId,user);

    verify(fitSetRepository).findById(setId);
    assertEquals(fitSetExpected,fitSetFromService.get());

  }

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
