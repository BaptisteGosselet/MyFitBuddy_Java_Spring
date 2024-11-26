package com.platine.myFitBuddy.fitSetsTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.service.DBUserServiceImpl;
import com.platine.myFitBuddy.features.fitRecords.model.FitRecord;
import com.platine.myFitBuddy.features.fitSets.controller.FitSetControllerImpl;
import com.platine.myFitBuddy.features.fitSets.model.FitSet;
import com.platine.myFitBuddy.features.fitSets.service.FitSetService;
import com.platine.myFitBuddy.features.fitSets.service.FitSetServiceImpl;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    DBUser user = new DBUser("username","email","password","role");
    FitSet fitSetExpected = new FitSet(1,1,1,1,new FitRecord("name",user));

    when(fitSetService.getSetById(anyLong(),any())).thenReturn(Optional.of(fitSetExpected));

    MvcResult result = this.mockMvc.perform(get("/sets/"+setId)).andExpect(status().isOk()).andReturn();

    String json = result.getResponse().getContentAsString();
    FitSet FitSetFromController = objectMapper.readValue(json, FitSet.class);

    verify(fitSetService).getSetById(anyLong(),any());
    assertEquals(fitSetExpected.getId(),FitSetFromController.getId());
    assertEquals(fitSetExpected.getNbOrder(),FitSetFromController.getNbOrder());
    assertEquals(fitSetExpected.getNbRep(),FitSetFromController.getNbRep());
    assertEquals(fitSetExpected.getWeight(),FitSetFromController.getWeight());
    assertEquals(fitSetExpected.getRecord().getName(),FitSetFromController.getRecord().getName());

  }

  @Test
  @Disabled
  void getSetByIdNotFoundTest() throws Exception {
    long setId = 1L;

    when(fitSetService.getSetById(anyLong(),any())).thenReturn(Optional.empty());

    this.mockMvc.perform(get("/sets/"+setId)).andExpect(status().isNotFound());

    verify(fitSetService).getSetById(anyLong(),any());
  }

  @Test
  void getSetsbyRecordTest() throws Exception {

    long recordId = 1L;
    DBUser user = new DBUser("username","email","password","role");
    List<FitSet> fitSetsExpected = List.of(new FitSet(1,1,1,1,new FitRecord("name",user)),new FitSet(2,2,2,2,new FitRecord("name",user)));

    when(fitSetService.getSetsbyUser(any())).thenReturn(fitSetsExpected);

    MvcResult result = this.mockMvc.perform(get("/sets/record/"+recordId)).andExpect(status().isOk()).andReturn();

    String json = result.getResponse().getContentAsString();
    List<FitSet> FitSetFromController = objectMapper.readValue(json, new TypeReference<>() {});

    verify(fitSetService).getSetsbyUser(any());
    for(int i = 0;i<fitSetsExpected.size();i++){
      assertEquals(fitSetsExpected.get(i).getId(),FitSetFromController.get(i).getId());
      assertEquals(fitSetsExpected.get(i).getNbOrder(),FitSetFromController.get(i).getNbOrder());
      assertEquals(fitSetsExpected.get(i).getNbRep(),FitSetFromController.get(i).getNbRep());
      assertEquals(fitSetsExpected.get(i).getWeight(),FitSetFromController.get(i).getWeight());
      assertEquals(fitSetsExpected.get(i).getRecord().getName(),FitSetFromController.get(i).getRecord().getName());
    }
  }

  @Test
  void getSetsbyRecordNotFoundTest() throws Exception {
    long recordId = 1L;

    when(fitSetService.getSetsbyUser(any())).thenReturn(List.of());

    this.mockMvc.perform(get("/sets/record/"+recordId)).andExpect(status().isNotFound());

    verify(fitSetService).getSetsbyUser(any());

  }

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
