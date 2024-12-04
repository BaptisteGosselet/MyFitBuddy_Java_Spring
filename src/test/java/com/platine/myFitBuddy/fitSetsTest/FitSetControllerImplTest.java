package com.platine.myFitBuddy.fitSetsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.service.DBUserServiceImpl;
import com.platine.myFitBuddy.features.exercices.model.Exercise;
import com.platine.myFitBuddy.features.exercices.model.MuscleGroup;
import com.platine.myFitBuddy.features.fitRecords.model.FitRecord;
import com.platine.myFitBuddy.features.fitSets.controller.FitSetControllerImpl;
import com.platine.myFitBuddy.features.fitSets.model.FitSet;
import com.platine.myFitBuddy.features.fitSets.model.FitSetCreateForm;
import com.platine.myFitBuddy.features.fitSets.model.FitSetUpdateForm;
import com.platine.myFitBuddy.features.fitSets.service.FitSetService;
import com.platine.myFitBuddy.features.fitSets.service.FitSetServiceImpl;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class FitSetControllerImplTest {
  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  FitSetServiceImpl fitSetService;

  @MockBean
  DBUserServiceImpl dbUserService;

  @InjectMocks
  FitSetControllerImpl fitSetControllerImpl;

  @Test
  void getSetByIdTest() throws Exception {
    long setId = 1L;
    DBUser user = new DBUser("username", "email", "password", "role");
    FitSet fitSetExpected = new FitSet(
      new FitRecord("name", user),
      new Exercise("key", MuscleGroup.ARMS, "en", "fr"),
      1,
      1,
      1
    );

    when(fitSetService.getSetById(anyLong(), any()))
      .thenReturn(Optional.of(fitSetExpected));

    MvcResult result =
      this.mockMvc.perform(get("/sets/" + setId)).andExpect(status().isOk()).andReturn();

    String json = result.getResponse().getContentAsString();
    FitSet FitSetFromController = objectMapper.readValue(json, FitSet.class);

    verify(fitSetService).getSetById(anyLong(), any());
    assertEquals(fitSetExpected.getId(), FitSetFromController.getId());
    assertEquals(fitSetExpected.getNbOrder(), FitSetFromController.getNbOrder());
    assertEquals(fitSetExpected.getNbRep(), FitSetFromController.getNbRep());
    assertEquals(fitSetExpected.getWeight(), FitSetFromController.getWeight());
    assertEquals(
      fitSetExpected.getRecord().getName(),
      FitSetFromController.getRecord().getName()
    );
  }

  @Test
  void getSetByIdNotFoundTest() throws Exception {
    long setId = 1L;

    when(fitSetService.getSetById(anyLong(), any())).thenReturn(Optional.empty());

    this.mockMvc.perform(get("/sets/" + setId)).andExpect(status().isNotFound());

    verify(fitSetService).getSetById(anyLong(), any());
  }

  @Test
  void getSetsbyRecordTest() throws Exception {
    long recordId = 1L;
    DBUser user = new DBUser("username", "email", "password", "role");
    List<FitSet> fitSetsExpected = List.of(
      new FitSet(
        new FitRecord("name", user),
        new Exercise("key", MuscleGroup.ARMS, "en", "fr"),
        1,
        1,
        1
      ),
      new FitSet(
        new FitRecord("name", user),
        new Exercise("key", MuscleGroup.ARMS, "en", "fr"),
        2,
        2,
        2
      )
    );

    when(fitSetService.getSetsbyUser(any())).thenReturn(fitSetsExpected);

    MvcResult result =
      this.mockMvc.perform(get("/sets/record/" + recordId))
        .andExpect(status().isOk())
        .andReturn();

    String json = result.getResponse().getContentAsString();
    List<FitSet> FitSetFromController = objectMapper.readValue(
      json,
      new TypeReference<>() {}
    );

    verify(fitSetService).getSetsbyUser(any());
    for (int i = 0; i < fitSetsExpected.size(); i++) {
      assertEquals(fitSetsExpected.get(i).getId(), FitSetFromController.get(i).getId());
      assertEquals(
        fitSetsExpected.get(i).getNbOrder(),
        FitSetFromController.get(i).getNbOrder()
      );
      assertEquals(
        fitSetsExpected.get(i).getNbRep(),
        FitSetFromController.get(i).getNbRep()
      );
      assertEquals(
        fitSetsExpected.get(i).getWeight(),
        FitSetFromController.get(i).getWeight()
      );
      assertEquals(
        fitSetsExpected.get(i).getRecord().getName(),
        FitSetFromController.get(i).getRecord().getName()
      );
    }
  }

  @Test
  void getSetsbyRecordNotFoundTest() throws Exception {
    long recordId = 1L;

    when(fitSetService.getSetsbyUser(any())).thenReturn(List.of());

    this.mockMvc.perform(get("/sets/record/" + recordId))
      .andExpect(status().isNotFound());

    verify(fitSetService).getSetsbyUser(any());
  }

  @Test
  void addSetToSessionTest() throws Exception {
    DBUser user = new DBUser("username", "email", "password", "role");
    FitSetCreateForm fitSetCreateForm = new FitSetCreateForm(1L, 1L, 1, 1, 1);
    FitSet fitSetExpected = new FitSet(
      new FitRecord("name", user),
      new Exercise("key", MuscleGroup.ARMS, "en", "fr"),
      fitSetCreateForm.getNbOrder(),
      fitSetCreateForm.getNbRep(),
      fitSetCreateForm.getWeight()
    );

    when(fitSetService.addSetToSession(any(), any())).thenReturn(fitSetExpected);
    System.out.println(fitSetCreateForm.toString());
    MvcResult result =
      this.mockMvc.perform(
          post("/sets/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(fitSetCreateForm.toString())
        )
        .andExpect(status().isCreated())
        .andReturn();

    String json = result.getResponse().getContentAsString();
    FitSet FitSetFromController = objectMapper.readValue(json, new TypeReference<>() {});

    verify(fitSetService).addSetToSession(any(), any());
    assertEquals(fitSetExpected.getId(), FitSetFromController.getId());
    assertEquals(fitSetExpected.getNbOrder(), FitSetFromController.getNbOrder());
    assertEquals(fitSetExpected.getNbRep(), FitSetFromController.getNbRep());
    assertEquals(fitSetExpected.getWeight(), FitSetFromController.getWeight());
    assertEquals(
      fitSetExpected.getRecord().getName(),
      FitSetFromController.getRecord().getName()
    );
  }

  @Test
  void addSetToSessionWrongUserTest() throws Exception {
    FitSetCreateForm fitSetCreateForm = new FitSetCreateForm(1L, 1L, 1, 1, 1);

    when(fitSetService.addSetToSession(any(), any()))
      .thenThrow(IllegalArgumentException.class);

    this.mockMvc.perform(
        post("/sets/create")
          .contentType(MediaType.APPLICATION_JSON)
          .content(fitSetCreateForm.toString())
      )
      .andExpect(status().isBadRequest());

    verify(fitSetService).addSetToSession(any(), any());
  }

  @Test
  void updateSetTest() throws Exception {
    DBUser user = new DBUser("username", "email", "password", "role");
    FitSetUpdateForm fitSetUpdateForm = new FitSetUpdateForm(1L, 1, 1, 1);
    FitSet fitSetExpected = new FitSet(
      new FitRecord("name", user),
      new Exercise("key", MuscleGroup.ARMS, "en", "fr"),
      fitSetUpdateForm.getNbOrder(),
      fitSetUpdateForm.getNbRep(),
      fitSetUpdateForm.getWeight()
    );

    when(fitSetService.updateSet(any(), any())).thenReturn(fitSetExpected);

    MvcResult result =
      this.mockMvc.perform(
          put("/sets/update")
            .contentType(MediaType.APPLICATION_JSON)
            .content(fitSetUpdateForm.toString())
        )
        .andExpect(status().isOk())
        .andReturn();

    String json = result.getResponse().getContentAsString();
    FitSet FitSetFromController = objectMapper.readValue(json, new TypeReference<>() {});

    verify(fitSetService).updateSet(any(), any());
    assertEquals(fitSetExpected.getId(), FitSetFromController.getId());
    assertEquals(fitSetExpected.getNbOrder(), FitSetFromController.getNbOrder());
    assertEquals(fitSetExpected.getNbRep(), FitSetFromController.getNbRep());
    assertEquals(fitSetExpected.getWeight(), FitSetFromController.getWeight());
    assertEquals(
      fitSetExpected.getRecord().getName(),
      FitSetFromController.getRecord().getName()
    );
  }

  @Test
  void updateSetWrongUserTest() throws Exception {
    FitSetUpdateForm fitSetUpdateForm = new FitSetUpdateForm(1L, 1, 1, 1);

    when(fitSetService.updateSet(any(), any())).thenThrow(IllegalArgumentException.class);

    this.mockMvc.perform(
        put("/sets/update")
          .contentType(MediaType.APPLICATION_JSON)
          .content(fitSetUpdateForm.toString())
      )
      .andExpect(status().isBadRequest());

    verify(fitSetService).updateSet(any(), any());
  }

  @Test
  void deleteSetTest() throws Exception {
    long setId = 1L;
    FitSetUpdateForm fitSetUpdateForm = new FitSetUpdateForm(1L, 1, 1, 1);

    doNothing().when(fitSetService).deleteSet(anyLong(), any());

    this.mockMvc.perform(
        delete("/sets/delete/" + setId)
          .contentType(MediaType.APPLICATION_JSON)
          .content(fitSetUpdateForm.toString())
      )
      .andExpect(status().isOk());

    verify(fitSetService).deleteSet(anyLong(), any());
  }

  @Test
  void deleteSetWrongUserTest() throws Exception {
    long setId = 1L;
    FitSetUpdateForm fitSetUpdateForm = new FitSetUpdateForm(1L, 1, 1, 1);

    doThrow(IllegalArgumentException.class)
      .when(fitSetService)
      .deleteSet(anyLong(), any());

    this.mockMvc.perform(
        delete("/sets/delete/" + setId)
          .contentType(MediaType.APPLICATION_JSON)
          .content(fitSetUpdateForm.toString())
      )
      .andExpect(status().isBadRequest());

    verify(fitSetService).deleteSet(anyLong(), any());
  }
}
