package com.platine.myFitBuddy.exercisesTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.platine.myFitBuddy.features.exercices.controller.ExerciseControllerImpl;
import com.platine.myFitBuddy.features.exercices.model.Exercise;
import com.platine.myFitBuddy.features.exercices.model.MuscleGroup;
import com.platine.myFitBuddy.features.exercices.service.ExerciseService;
import com.platine.myFitBuddy.features.sessions.model.FitSession;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ExerciseControllerImplTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  ExerciseService exerciseService;

  @InjectMocks
  ExerciseControllerImpl exerciseControllerImpl;

  @Test
  void getExercisesTest() throws Exception {
    String key = "key";
    MuscleGroup muscleGroup = MuscleGroup.ARMS;

    List<Exercise> exerciceToExpect = List.of(new Exercise(key,muscleGroup,"en","fr"),new Exercise(key,muscleGroup,"EN","FR"));
    Pageable pageable = PageRequest.of(0,20);
    Page<Exercise> pageExercice = new PageImpl<>(exerciceToExpect, pageable, exerciceToExpect.size());

    when(exerciseService.findByKeyAndMuscleGroup(key, muscleGroup, pageable)).thenReturn(pageExercice);

    MvcResult result = this.mockMvc.perform(get("/exercises").param("key",key).param("muscleGroup", String.valueOf(muscleGroup))).andExpect(status().isOk()).andReturn();

    verify(exerciseService,times(1)).findByKeyAndMuscleGroup(key, muscleGroup, pageable);
  }

  @Test
  void getExerciseByIdTest() throws Exception {
    long exerciceId = 1L;

    String key = "key";
    MuscleGroup muscleGroup = MuscleGroup.ARMS;

    Exercise exerciceToExpect = new Exercise(key,muscleGroup,"en","fr");

    when(exerciseService.getExerciseById(exerciceId)).thenReturn(exerciceToExpect);

    MvcResult result = this.mockMvc.perform(get("/exercises/"+exerciceId)).andExpect(status().isOk()).andReturn();

    String json = result.getResponse().getContentAsString();
    Exercise exerciceFromController = objectMapper.readValue(json, Exercise.class);

    verify(exerciseService).getExerciseById(exerciceId);
    assertEquals(exerciceToExpect.getKey(),exerciceFromController.getKey());
    assertEquals(exerciceToExpect.getMuscleGroup(),exerciceFromController.getMuscleGroup());
    assertEquals(exerciceToExpect.getLabelEn(),exerciceFromController.getLabelEn());
    assertEquals(exerciceToExpect.getLabelFr(),exerciceFromController.getLabelFr());
  }

  @Test
  void getImageTest() throws Exception {
    long exerciseId=1L;

    when(exerciseService.getExerciseImage(exerciseId)).thenReturn(null);

    this.mockMvc.perform(get("/exercises/"+exerciseId+"/image")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.IMAGE_JPEG)).andReturn();

    verify(exerciseService).getExerciseImage(exerciseId);

  }
}
