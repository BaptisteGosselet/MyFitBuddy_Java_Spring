package com.platine.myFitBuddy.sessionsTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.dbUsers.service.DBUserServiceImpl;
import com.platine.myFitBuddy.features.sessions.model.FitSession;
import com.platine.myFitBuddy.features.sessions.model.SessionCreateForm;
import com.platine.myFitBuddy.features.sessions.model.SessionUpdateForm;
import com.platine.myFitBuddy.features.sessions.service.SessionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class SessionControllerImplTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  DBUserServiceImpl dbUserService;

  @MockBean
  SessionServiceImpl sessionService;

  @Test
  void findByIdTest() throws Exception {
    long sessionId = 1L;
    String name = "name";

    DBUser user = new DBUser("username","email","password","role");
    Optional<FitSession> fitSession = Optional.of(new FitSession(name,user));

    when(sessionService.findById(anyLong(),any())).thenReturn(fitSession);

    MvcResult result = this.mockMvc.perform(get("/sessions/"+sessionId)).andExpect(status().isOk())
            .andReturn();

    String json = result.getResponse().getContentAsString();
    FitSession fitSessionFromController = objectMapper.readValue(json, FitSession.class);

    verify(sessionService,times(1)).findById(anyLong(),any());
    assertEquals(fitSession.get().getId(),fitSessionFromController.getId());
    assertEquals(fitSession.get().getName(),fitSessionFromController.getName());
    assertEquals(fitSession.get().getUser().getUsername(),fitSessionFromController.getUser().getUsername());
  }

  @Test
  void findByUserTest() throws Exception {
    String name = "name";

    DBUser user = new DBUser("username","email","password","role");
    List<FitSession> fitSession = List.of(new FitSession(name,user),new FitSession("name2",user));

    when(sessionService.findByUserId(any())).thenReturn(fitSession);

    MvcResult result = this.mockMvc.perform(get("/sessions/user")).andExpect(status().isOk())
            .andReturn();

    String json = result.getResponse().getContentAsString();
    List<FitSession> fitSessionFromController = objectMapper.readValue(json, new TypeReference<>(){});

    verify(sessionService,times(1)).findByUserId(any());
    assertEquals(fitSession.size(), fitSessionFromController.size());
    for(int i = 0;i<fitSession.size();i++){
      assertEquals(fitSession.get(i).getId(),fitSessionFromController.get(i).getId());
      assertEquals(fitSession.get(i).getName(),fitSessionFromController.get(i).getName());
      assertEquals(fitSession.get(i).getUser().getUsername(),fitSessionFromController.get(i).getUser().getUsername());
    }
  }

  @Test
  void findAllSessionsTest() throws Exception {
    String name = "name";

    DBUser user = new DBUser("username","email","password","role");
    List<FitSession> fitSession = List.of(new FitSession(name,user),new FitSession("name2",user));

    when(sessionService.findAll()).thenReturn(fitSession);

    MvcResult result = this.mockMvc.perform(get("/sessions/all")).andExpect(status().isOk())
            .andReturn();

    String json = result.getResponse().getContentAsString();
    List<FitSession> fitSessionFromController = objectMapper.readValue(json, new TypeReference<>(){});;

    verify(sessionService,times(1)).findAll();
    assertEquals(fitSession.size(), fitSessionFromController.size());
    for(int i = 0;i<fitSession.size();i++){
      assertEquals(fitSession.get(i).getId(),fitSessionFromController.get(i).getId());
      assertEquals(fitSession.get(i).getName(),fitSessionFromController.get(i).getName());
      assertEquals(fitSession.get(i).getUser().getUsername(),fitSessionFromController.get(i).getUser().getUsername());
    }
  }

  @Test
  void createTest() throws Exception {
    String name = "name";

    SessionCreateForm sessionCreateForm = new SessionCreateForm(name);
    DBUser user = new DBUser("username","email","password","role");
    FitSession fitSession = new FitSession(name,user);

    when(sessionService.create(any(),any())).thenReturn(fitSession);

    MvcResult result = this.mockMvc.perform(post("/sessions").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(sessionCreateForm))).andExpect(status().isOk())
            .andReturn();

    String json = result.getResponse().getContentAsString();
    FitSession fitSessionFromController = objectMapper.readValue(json, FitSession.class);

    verify(sessionService,times(1)).create(any(),any());
    assertEquals(fitSession.getId(),fitSessionFromController.getId());
    assertEquals(fitSession.getName(),fitSessionFromController.getName());
    assertEquals(fitSession.getUser().getUsername(),fitSessionFromController.getUser().getUsername());
  }

  @Test
  void updateTest() throws Exception {
    long sessionId = 1L;
    String name = "name";

    SessionUpdateForm sessionUpdateForm = new SessionUpdateForm(sessionId, name);
    DBUser user = new DBUser("username","email","password","role");
    FitSession fitSession = new FitSession(name,user);

    when(sessionService.update(any(),any())).thenReturn(fitSession);

    MvcResult result = this.mockMvc.perform(put("/sessions/"+sessionId).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(sessionUpdateForm))).andExpect(status().isOk())
            .andReturn();

    String json = result.getResponse().getContentAsString();
    FitSession fitSessionFromController = objectMapper.readValue(json, FitSession.class);

    verify(sessionService,times(1)).update(any(),any());
    assertEquals(fitSession.getId(),fitSessionFromController.getId());
    assertEquals(fitSession.getName(),fitSessionFromController.getName());
    assertEquals(fitSession.getUser().getUsername(),fitSessionFromController.getUser().getUsername());
  }

  @Test
  void deleteTest() throws Exception  {
    long sessionId = 1L;
    String name = "name";

    DBUser user = new DBUser("username","email","password","role");
    FitSession fitSession = new FitSession(name,user);

    doNothing().when(sessionService).delete(anyLong(),any());

    this.mockMvc.perform(delete("/sessions/"+sessionId)).andExpect(status().isOk());

    verify(sessionService,times(1)).delete(anyLong(),any());
  }
}
