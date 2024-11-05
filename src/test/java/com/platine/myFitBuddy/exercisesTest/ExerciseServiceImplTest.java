package com.platine.myFitBuddy.exercisesTest;

import com.platine.myFitBuddy.features.exercices.repository.ExerciseRepository;
import com.platine.myFitBuddy.features.exercices.service.ExerciseServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ExerciseServiceImplTest {
  @Mock
  ExerciseRepository exerciseRepository;

  @InjectMocks
  ExerciseServiceImpl sessionControllerImpl;

  @Test
  @Disabled
  void findByKeyAndMuscleGroupTest() {}

  @Test
  @Disabled
  void getExerciseByIdTest() {}

  @Test
  @Disabled
  void getExerciseImageTest() {}
}
