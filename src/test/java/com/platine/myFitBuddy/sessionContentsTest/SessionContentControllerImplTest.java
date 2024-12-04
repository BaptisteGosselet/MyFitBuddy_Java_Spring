package com.platine.myFitBuddy.sessionContentsTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.service.DBUserServiceImpl;
import com.platine.myFitBuddy.features.exercices.model.Exercise;
import com.platine.myFitBuddy.features.exercices.model.MuscleGroup;
import com.platine.myFitBuddy.features.exercices.service.ExerciseService;
import com.platine.myFitBuddy.features.sessionContent.controller.SessionContentControllerImpl;
import com.platine.myFitBuddy.features.sessionContent.model.*;
import com.platine.myFitBuddy.features.sessionContent.service.SessionContentService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class SessionContentControllerImplTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  DBUserServiceImpl dbUserService;

  @MockBean
  SessionContentService sessionContentService;

  @MockBean
  ExerciseService exerciseService;

  @InjectMocks
  SessionContentControllerImpl sessionContentControllerImpl;

  @Test
  void findByUserBySessionIdTest() throws Exception {
    long sessionId = 1L;
    DBUser user = new DBUser("username","email","password","role");
    Exercise exercise = new Exercise("key", MuscleGroup.ARMS, "en", "fr");
    List<SessionContent> listSessionContentFromService = List.of(new SessionContent(1L,1L,1,1,1,user), new SessionContent(1L,2L,2,2,2,user));
    List<SessionContentDTOWithExercise> listSessionContentExpected = List.of(new SessionContentDTOWithExercise(1L,1L,new Exercise("key", MuscleGroup.ARMS,"en","fr"),1,1,1), new SessionContentDTOWithExercise(2L,1L,new Exercise("key", MuscleGroup.ARMS,"en","fr"),2,2,2));

    when(sessionContentService.findByUserIdBySession(anyLong(),any())).thenReturn(listSessionContentFromService);
    when(exerciseService.getExerciseById(anyLong())).thenReturn(exercise);

    MvcResult result = this.mockMvc.perform(get("/sessionContent/"+sessionId)).andExpect(status().isOk()).andReturn();

    String json = result.getResponse().getContentAsString();
    List<SessionContentDTOWithExercise> sessionContentFromController = objectMapper.readValue(json, new TypeReference<>(){});

    verify(sessionContentService,times(1)).findByUserIdBySession(anyLong(),any());
    verify(exerciseService,times(2)).getExerciseById(anyLong());

    for(int i = 0;i<sessionContentFromController.size();i++){
      assertEquals(listSessionContentExpected.get(i).getSessionId(), sessionContentFromController.get(i).getSessionId());
      assertEquals(listSessionContentExpected.get(i).getExercise().getKey(), sessionContentFromController.get(i).getExercise().getKey());
      assertEquals(listSessionContentExpected.get(i).getNumberOfSet(), sessionContentFromController.get(i).getNumberOfSet());
      assertEquals(listSessionContentExpected.get(i).getRestTimeInSecond(), sessionContentFromController.get(i).getRestTimeInSecond());
      assertEquals(listSessionContentExpected.get(i).getIndex(), sessionContentFromController.get(i).getIndex());
    }
  }

  @Test
  void createTest() throws Exception{
    DBUser user = new DBUser("username","email","password","role");
    SessionContent sessionContent = new SessionContent(1L,1L,1,1,1,user);
    SessionContentDTO sessionContentExpected = new SessionContentDTO(1L,1L,1L,1,1,1);
    SessionContentCreateForm sessionContentCreateForm = new SessionContentCreateForm(sessionContent.getSessionId(),sessionContent.getExerciseId(),sessionContent.getNumberOfSet(),sessionContent.getRestTimeInSecond());

    when(sessionContentService.create(any(),any())).thenReturn(sessionContent);

    MvcResult result = this.mockMvc.perform(post("/sessionContent").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(sessionContentCreateForm))).andExpect(status().isOk()).andReturn();

    String json = result.getResponse().getContentAsString();
    SessionContentDTO sessionContentFromController = objectMapper.readValue(json, new TypeReference<>(){});

    verify(sessionContentService,times(1)).create(any(),any());
    assertEquals(sessionContentExpected.getSessionId(), sessionContentFromController.getSessionId());
    assertEquals(sessionContentExpected.getNumberOfSet(), sessionContentFromController.getNumberOfSet());
    assertEquals(sessionContentExpected.getRestTimeInSecond(), sessionContentFromController.getRestTimeInSecond());
  }

  @Test
  void updateTest() throws Exception{
    DBUser user = new DBUser("username","email","password","role");
    SessionContent sessionContent = new SessionContent(1L,1L,1,1,1,user);
    SessionContentUpdateForm sessionContentUpdateForm = new SessionContentUpdateForm(1L,1,1,1);
    SessionContentDTO sessionContentExpected = new SessionContentDTO(1L,1L,1L,1,1,1);

    when(sessionContentService.update(any(),anyLong(),any())).thenReturn(sessionContent);
    System.out.println(sessionContentUpdateForm.toString());
    MvcResult result = this.mockMvc.perform(put("/sessionContent/"+sessionContentUpdateForm.getId()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(sessionContentUpdateForm))).andExpect(status().isOk()).andReturn();

    String json = result.getResponse().getContentAsString();
    SessionContentDTO sessionContentFromController = objectMapper.readValue(json, new TypeReference<>(){});

    verify(sessionContentService,times(1)).update(any(),anyLong(),any());
    assertEquals(sessionContentExpected.getSessionId(), sessionContentFromController.getSessionId());
    assertEquals(sessionContentExpected.getNumberOfSet(), sessionContentFromController.getNumberOfSet());
    assertEquals(sessionContentExpected.getRestTimeInSecond(), sessionContentFromController.getRestTimeInSecond());
  }

  @Test
  void deleteTest() throws Exception{
    long sessionContentId = 1L;
    doNothing().when(sessionContentService).delete(anyLong(),any());

    this.mockMvc.perform(delete("/sessionContent/"+sessionContentId)).andExpect(status().isOk());

    verify(sessionContentService,times(1)).delete(anyLong(),any());
  }

  @Test
  void changeOrderTest() throws Exception {
    long sessionId = 1L;
    DBUser user = new DBUser("username","email","password","role");
    Exercise exercise = new Exercise("key", MuscleGroup.ARMS, "en", "fr");
    List<SessionContentUpdateForm> listSessionContentUpdateForm = List.of(new SessionContentUpdateForm(1L,1,1,1), new SessionContentUpdateForm(1L,1,1,1));
    SessionContent sessionContentFromService = new SessionContent(1L,1L,1,1,1,user);
    List<SessionContentDTOWithExercise> listSessionContentExpected = List.of(new SessionContentDTOWithExercise(1L,1L,new Exercise("key", MuscleGroup.ARMS,"en","fr"),1,1,1), new SessionContentDTOWithExercise(2L,1L,new Exercise("key", MuscleGroup.ARMS,"en","fr"),1,1,1));

    when(sessionContentService.update(any(),anyLong(),any())).thenReturn(sessionContentFromService);

    MvcResult result = this.mockMvc.perform(put("/sessionContent/list").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(listSessionContentUpdateForm))).andExpect(status().isOk()).andReturn();

    String json = result.getResponse().getContentAsString();
    List<SessionContentDTOWithExercise> sessionContentFromController = objectMapper.readValue(json, new TypeReference<>(){});

    verify(sessionContentService,times(2)).update(any(),anyLong(),any());

  }
}
