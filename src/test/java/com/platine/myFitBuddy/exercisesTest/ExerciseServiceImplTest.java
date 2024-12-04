package com.platine.myFitBuddy.exercisesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.platine.myFitBuddy.features.exercices.model.Exercise;
import com.platine.myFitBuddy.features.exercices.model.MuscleGroup;
import com.platine.myFitBuddy.features.exercices.repository.ExerciseRepository;
import com.platine.myFitBuddy.features.exercices.service.ExerciseServiceImpl;
import com.platine.myFitBuddy.utils.MyUtils;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@ExtendWith(MockitoExtension.class)
public class ExerciseServiceImplTest {
  @Mock
  ExerciseRepository exerciseRepository;

  @InjectMocks
  ExerciseServiceImpl exerciseServiceImpl;

  @Test
  void findByKeyAndMuscleGroupTest() {
    String key = "key";
    MuscleGroup muscleGroup = MuscleGroup.ARMS;
    Pageable pageable = PageRequest.of(0, 3);
    Page<Exercise> pageExerciseExpected = new PageImpl<>(
      List.of(
        new Exercise(key, muscleGroup, "en", "fr"),
        new Exercise(key, muscleGroup, "EN", "FR")
      ),
      pageable,
      2
    );

    when(
        exerciseRepository.findByKeyAndMuscleGroup(
          MyUtils.normalize(key),
          muscleGroup,
          pageable
        )
      )
      .thenReturn(pageExerciseExpected);

    Page<Exercise> exerciseFromService = exerciseServiceImpl.findByKeyAndMuscleGroup(
      key,
      muscleGroup,
      pageable
    );

    assertEquals(
      pageExerciseExpected.getTotalElements(),
      exerciseFromService.getTotalElements()
    );
    for (int i = 0; i < pageExerciseExpected.getTotalElements(); i++) {
      assertEquals(
        pageExerciseExpected.getContent().get(i).getKey(),
        exerciseFromService.getContent().get(i).getKey()
      );
      assertEquals(
        pageExerciseExpected.getContent().get(i).getMuscleGroup(),
        exerciseFromService.getContent().get(i).getMuscleGroup()
      );
      assertEquals(
        pageExerciseExpected.getContent().get(i).getLabelEn(),
        exerciseFromService.getContent().get(i).getLabelEn()
      );
      assertEquals(
        pageExerciseExpected.getContent().get(i).getLabelFr(),
        exerciseFromService.getContent().get(i).getLabelFr()
      );
    }
  }

  @Test
  void getExerciseByIdTest() {
    long exerciseId = 1L;
    Exercise exerciseExpected = new Exercise("key", MuscleGroup.ARMS, "en", "fr");

    when(exerciseRepository.findById(exerciseId))
      .thenReturn(Optional.of(exerciseExpected));

    Exercise exerciseFromService = exerciseServiceImpl.getExerciseById(exerciseId);

    verify(exerciseRepository).findById(exerciseId);
    assertEquals(exerciseExpected, exerciseFromService);
  }

  @Test
  void getExerciseByIdNotFoundTest() {
    long exerciseId = 1L;

    when(exerciseRepository.findById(exerciseId)).thenReturn(Optional.empty());

    assertThrows(
      RuntimeException.class,
      () -> {
        exerciseServiceImpl.getExerciseById(exerciseId);
      }
    );

    verify(exerciseRepository).findById(exerciseId);
  }

  @Test
  @Disabled
  void getExerciseImageTest() {
    /*long exerciseId = 1L;

    when(exerciseServiceImpl.getExerciseById(exerciseId)).thenReturn();

    StreamingResponseBody streamingResponseBody = exerciseServiceImpl.getExerciseImage(exerciseId);*/

  }
}
