package com.platine.myFitBuddy.exercisesTest;

import com.platine.myFitBuddy.features.exercices.controller.ExerciseControllerImpl;
import com.platine.myFitBuddy.features.exercices.service.ExerciseService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ExerciseControllerImplTest {
  @Mock
  ExerciseService exerciseService;

  @InjectMocks
  ExerciseControllerImpl exerciseControllerImpl;

  @Test
  @Disabled
  void getExercisesTest() {}

  @Test
  @Disabled
  void getExerciseByIdTest() {}

  @Test
  @Disabled
  void getImageTest() {}
}
