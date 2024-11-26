package com.platine.myFitBuddy.fitRecordsTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.service.DBUserServiceImpl;

import com.platine.myFitBuddy.features.fitRecords.model.FitRecord;
import com.platine.myFitBuddy.features.fitRecords.service.FitRecordServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class FitRecordControllerImplTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  DBUserServiceImpl dbUserService;

  @MockBean
  FitRecordServiceImpl fitRecordService;

  @Test
  void getRecordByIdTest() throws Exception {
    long fitRecordId = 1L;
    DBUser user = new DBUser("username","email","password","role");

    FitRecord fitRecordExpected = new FitRecord("name",user);

    when(fitRecordService.getRecordById(anyLong(),any())).thenReturn(Optional.of(fitRecordExpected));

    MvcResult result = this.mockMvc.perform(get("/records/"+fitRecordId)).andExpect(status().isOk()).andReturn();

    String json = result.getResponse().getContentAsString();
    FitRecord FitRecordFromController = objectMapper.readValue(json, FitRecord.class);

    verify(fitRecordService).getRecordById(anyLong(),any());
    assertEquals(fitRecordExpected.getId(),FitRecordFromController.getId());
    assertEquals(fitRecordExpected.getName(),FitRecordFromController.getName());
  }

  @Test
  void getRecordByIdNotFoundTest() throws Exception{
    long fitRecordId = 1L;

    when(fitRecordService.getRecordById(anyLong(),any())).thenReturn(Optional.empty());

    this.mockMvc.perform(get("/records/"+fitRecordId)).andExpect(status().isNotFound());

    verify(fitRecordService).getRecordById(anyLong(),any());
  }

  @Test
  void getRecordByUserTest() throws Exception {
    DBUser user = new DBUser("username","email","password","role");
    List<FitRecord> fitRecordsExpected = List.of(new FitRecord("name1",user),new FitRecord("name2",user));
    when(fitRecordService.getRecordsOfUser(any())).thenReturn(fitRecordsExpected);

    MvcResult result = this.mockMvc.perform(get("/records/user")).andExpect(status().isOk()).andReturn();

    String json = result.getResponse().getContentAsString();
    List<FitRecord> fitRecordFromController = objectMapper.readValue(json, new TypeReference<>() {});

    verify(fitRecordService).getRecordsOfUser(any());
    for(int i=0;i<fitRecordsExpected.size();i++){
      assertEquals(fitRecordsExpected.get(i).getName(),fitRecordFromController.get(i).getName());
      assertEquals(fitRecordsExpected.get(i).getId(),fitRecordFromController.get(i).getId());
      assertEquals(fitRecordsExpected.get(i).getUser().getUsername(),fitRecordFromController.get(i).getUser().getUsername());
    }
  }

  @Test
  void createRecordTest() throws Exception {
    long sessionId=1L;
    DBUser user = new DBUser("username","email","password","role");
    FitRecord fitRecordExpected = new FitRecord("name",user);

    when(fitRecordService.createRecord(anyLong(),any())).thenReturn(fitRecordExpected);

    MvcResult result = this.mockMvc.perform(post("/records/create/"+sessionId)).andExpect(status().isOk()).andReturn();

    String json = result.getResponse().getContentAsString();
    FitRecord fitRecordFromController = objectMapper.readValue(json, new TypeReference<>() {});
    verify(fitRecordService).createRecord(anyLong(),any());
    assertEquals(fitRecordExpected.getId(), fitRecordFromController.getId());
    assertEquals(fitRecordExpected.getName(), fitRecordFromController.getName());
    assertEquals(fitRecordExpected.getUser().getUsername(),fitRecordFromController.getUser().getUsername());
  }

  @Test
  @Disabled
  void setFeelingNote() {}

  @Test
  void deleteRecordTest() throws Exception {
    long sessionId=1L;

    doNothing().when(fitRecordService).deleteRecord(anyLong(),any());

    this.mockMvc.perform(delete("/records/delete/"+sessionId)).andExpect(status().isOk());

    verify(fitRecordService).deleteRecord(anyLong(),any());

  }
}
