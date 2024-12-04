package com.platine.myFitBuddy.fitSetsTest;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.exercices.model.Exercise;
import com.platine.myFitBuddy.features.exercices.model.MuscleGroup;
import com.platine.myFitBuddy.features.exercices.repository.ExerciseRepository;
import com.platine.myFitBuddy.features.fitRecords.model.FitRecord;
import com.platine.myFitBuddy.features.fitRecords.repository.FitRecordRepository;
import com.platine.myFitBuddy.features.fitSets.model.FitSet;
import com.platine.myFitBuddy.features.fitSets.model.FitSetCreateForm;
import com.platine.myFitBuddy.features.fitSets.model.FitSetUpdateForm;
import com.platine.myFitBuddy.features.fitSets.repository.FitSetRepository;
import com.platine.myFitBuddy.features.fitSets.service.FitSetServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FitSetServiceImplTest {
  @Mock
  FitSetRepository fitSetRepository;

  @Mock
  FitRecordRepository fitRecordRepository;

  @Mock
  ExerciseRepository exerciceRepository;

  @InjectMocks
  FitSetServiceImpl fitSetServiceImpl;

  @Test
  void getSetByIdTest() {
    long setId = 1L;
    DBUser user = new DBUser("username", "email", "password", "role");
    FitSet fitSetExpected = new FitSet(new FitRecord("name", user), new Exercise("key", MuscleGroup.ARMS, "en", "fr"), 1, 1, 1);
    when(fitSetRepository.findById(setId)).thenReturn(Optional.of(fitSetExpected));

    Optional<FitSet> fitSetFromService = fitSetServiceImpl.getSetById(setId, user);

    verify(fitSetRepository).findById(setId);
    assertEquals(fitSetExpected, fitSetFromService.get());

  }

  @Test
  void getSetsbyUserTest() {
    DBUser user = new DBUser("username", "email", "password", "role");
    List<FitSet> fitSetsExpected = List.of(new FitSet(new FitRecord("name", user), new Exercise("key", MuscleGroup.ARMS, "en", "fr"), 1, 1, 1), new FitSet(new FitRecord("name", user), new Exercise("key", MuscleGroup.ARMS, "en", "fr"), 2, 2, 2));

    when(fitSetRepository.findAllByRecordUser(any())).thenReturn(fitSetsExpected);

    List<FitSet> fitsSetsFromService = fitSetServiceImpl.getSetsbyUser(user);

    verify(fitSetRepository, times(1)).findAllByRecordUser(any());
    for (int i = 0; i < fitSetsExpected.size(); i++) {
      assertEquals(fitSetsExpected.get(i).getId(), fitsSetsFromService.get(i).getId());
      assertEquals(fitSetsExpected.get(i).getWeight(), fitsSetsFromService.get(i).getWeight());
      assertEquals(fitSetsExpected.get(i).getNbRep(), fitsSetsFromService.get(i).getNbRep());
      assertEquals(fitSetsExpected.get(i).getNbOrder(), fitsSetsFromService.get(i).getNbOrder());
    }
  }

  @Test
  void addSetToSessionTest() {
    DBUser user = new DBUser("username", "email", "password", "role");
    FitSetCreateForm fitSetCreateForm = new FitSetCreateForm(1L, 1L, 1, 1, 1);
    Exercise exercise = new Exercise("key", MuscleGroup.ARMS, "en", "fr");
    FitRecord fitRecordExpected = new FitRecord("name1", user);
    FitSet fitSetExpected = new FitSet(fitRecordExpected, exercise, 1, 1, 1);

    when(fitRecordRepository.findById(fitSetCreateForm.getIdRecord())).thenReturn(Optional.of(fitRecordExpected));
    when(exerciceRepository.findById(fitSetCreateForm.getIdExercise())).thenReturn(Optional.of(exercise));
    when(fitSetRepository.save(any())).thenReturn(fitSetExpected);

    FitSet fitSetFromService = fitSetServiceImpl.addSetToSession(fitSetCreateForm, user);

    verify(fitRecordRepository).findById(fitSetCreateForm.getIdRecord());
    verify(exerciceRepository).findById(fitSetCreateForm.getIdExercise());
    verify(fitSetRepository).save(any());
    assertEquals(fitSetExpected, fitSetFromService);
  }

  @Test
  void addSetToSessionRecordNotFoundTest() {
    DBUser user = new DBUser("username", "email", "password", "role");
    FitSetCreateForm fitSetCreateForm = new FitSetCreateForm(1L, 1L, 1, 1, 1);

    when(fitRecordRepository.findById(fitSetCreateForm.getIdRecord())).thenReturn(Optional.empty());

    assertThrows(IllegalArgumentException.class, () -> {
      fitSetServiceImpl.addSetToSession(fitSetCreateForm, user);
    });

    verify(fitRecordRepository).findById(fitSetCreateForm.getIdRecord());
  }

  @Test
  void addSetToSessionExerciceNotFoundTest() {
    DBUser user = new DBUser("username", "email", "password", "role");
    FitSetCreateForm fitSetCreateForm = new FitSetCreateForm(1L, 1L, 1, 1, 1);
    FitRecord fitRecordExpected = new FitRecord("name1", user);

    when(fitRecordRepository.findById(fitSetCreateForm.getIdRecord())).thenReturn(Optional.of(fitRecordExpected));
    when(exerciceRepository.findById(fitSetCreateForm.getIdExercise())).thenReturn(Optional.empty());

    assertThrows(IllegalArgumentException.class, () -> {
      fitSetServiceImpl.addSetToSession(fitSetCreateForm, user);
    });

    verify(fitRecordRepository).findById(fitSetCreateForm.getIdRecord());
    verify(exerciceRepository).findById(fitSetCreateForm.getIdExercise());
  }

  @Test
  void updateSetTest() {
    DBUser user = new DBUser("username", "email", "password", "role");
    FitSetUpdateForm fitSetCreateForm = new FitSetUpdateForm(1L, 1, 1, 1);
    Exercise exercise = new Exercise("key", MuscleGroup.ARMS, "en", "fr");
    FitRecord fitRecordExpected = new FitRecord("name1", user);
    FitSet fitSetExpected = new FitSet(fitRecordExpected, exercise, 1, 1, 1);

    when(fitSetRepository.findById(fitSetCreateForm.getIdFitSet())).thenReturn(Optional.of(fitSetExpected));
    when(fitSetRepository.save(any())).thenReturn(fitSetExpected);

    FitSet fitSetFromService = fitSetServiceImpl.updateSet(fitSetCreateForm, user);

    verify(fitSetRepository).findById(fitSetCreateForm.getIdFitSet());
    verify(fitSetRepository).save(any());
    assertEquals(fitSetExpected, fitSetFromService);
  }

  @Test
  @Disabled
  void updateSetWrongUserTest() {

    DBUser user = new DBUser("username", "email", "password", "role");
    FitSetUpdateForm fitSetCreateForm = new FitSetUpdateForm(1L, 1, 1, 1);

    when(fitSetRepository.findById(fitSetCreateForm.getIdFitSet())).thenReturn(Optional.empty());

    assertThrows(IllegalArgumentException.class, () -> {
      fitSetServiceImpl.updateSet(fitSetCreateForm, user);
    });

    verify(fitSetRepository).findById(fitSetCreateForm.getIdFitSet());
  }

  @Test
  void deleteSetTest() {
    DBUser user = new DBUser("username", "email", "password", "role");
    Exercise exercise = new Exercise("key", MuscleGroup.ARMS, "en", "fr");
    FitRecord fitRecordExpected = new FitRecord("name1", user);
    FitSet fitSetExpected = new FitSet(fitRecordExpected, exercise, 1, 1, 1);

    when(fitSetRepository.findById(any())).thenReturn(Optional.of(fitSetExpected));
    doNothing().when(fitSetRepository).delete(fitSetExpected);

    fitSetServiceImpl.deleteSet(1L, user);

    verify(fitSetRepository).findById(any());
    verify(fitSetRepository).delete(fitSetExpected);
  }
}
