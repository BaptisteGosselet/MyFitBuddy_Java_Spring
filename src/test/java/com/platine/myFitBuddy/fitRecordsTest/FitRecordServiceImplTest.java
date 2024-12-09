package com.platine.myFitBuddy.fitRecordsTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.fitRecords.model.FitRecord;
import com.platine.myFitBuddy.features.fitRecords.repository.FitRecordRepository;
import com.platine.myFitBuddy.features.fitRecords.service.FitRecordServiceImpl;
import com.platine.myFitBuddy.features.sessions.model.FitSession;
import com.platine.myFitBuddy.features.sessions.repository.SessionRepository;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
public class FitRecordServiceImplTest {
  @Mock
  FitRecordRepository fitRecordRepository;

  @Mock
  SessionRepository sessionRepository;

  @InjectMocks
  FitRecordServiceImpl fitRecordServiceImpl;

  @Test
  void getRecordByIdTest() {
    long fitRecordId = 1L;
    DBUser user = new DBUser("username", "email", "password", "role");
    FitRecord fitRecordExpected = new FitRecord("name", user);

    when(fitRecordRepository.findById(fitRecordId))
      .thenReturn(Optional.of(fitRecordExpected));

    Optional<FitRecord> fitRecordFromService = fitRecordServiceImpl.getRecordById(
      fitRecordId,
      user
    );

    verify(fitRecordRepository).findById(fitRecordId);
    assertTrue(fitRecordFromService.isPresent());
    assertEquals(fitRecordExpected.getId(), fitRecordFromService.get().getId());
    assertEquals(fitRecordExpected.getName(), fitRecordFromService.get().getName());
    assertEquals(
      fitRecordExpected.getUser().getUsername(),
      fitRecordFromService.get().getUser().getUsername()
    );
  }

  @Test
  void getRecordByUserTest() {
    DBUser user = new DBUser("username", "email", "password", "role");
    List<FitRecord> fitRecordsExpected = List.of(
      new FitRecord("name1", user),
      new FitRecord("name2", user)
    );

    when(fitRecordRepository.findByUser(any(), any())).thenReturn(fitRecordsExpected);

    List<FitRecord> fitRecordFromService = fitRecordServiceImpl.getRecordsOfUser(user);

    verify(fitRecordRepository).findByUser(any(), any());
    for (int i = 0; i < fitRecordsExpected.size(); i++) {
      assertEquals(
        fitRecordsExpected.get(i).getName(),
        fitRecordFromService.get(i).getName()
      );
      assertEquals(
        fitRecordsExpected.get(i).getId(),
        fitRecordFromService.get(i).getId()
      );
      assertEquals(
        fitRecordsExpected.get(i).getUser().getUsername(),
        fitRecordFromService.get(i).getUser().getUsername()
      );
    }
  }

  @Test
  void createRecordTest() {
    long sessionId = 1L;
    DBUser user = new DBUser("username", "email", "password", "role");
    FitRecord fitRecordExpected = new FitRecord("name1", user);
    FitSession session = new FitSession("name", user);
    when(sessionRepository.findById(sessionId)).thenReturn(Optional.of(session));
    when(fitRecordRepository.save(any())).thenReturn(fitRecordExpected);

    FitRecord fitRecordfromService = fitRecordServiceImpl.createRecord(sessionId, user);

    verify(fitRecordRepository).save(any());
    assertEquals(fitRecordExpected.getId(), fitRecordfromService.getId());
    assertEquals(fitRecordExpected.getName(), fitRecordfromService.getName());
    assertEquals(
      fitRecordExpected.getUser().getUsername(),
      fitRecordfromService.getUser().getUsername()
    );
  }

  @Test
  void deleteRecordTest() {
    long recordId = 1L;
    DBUser user = new DBUser("username", "email", "password", "role");
    FitRecord fitRecordExpected = new FitRecord("name1", user);

    when(fitRecordRepository.findById(anyLong()))
      .thenReturn(Optional.of(fitRecordExpected));
    doNothing().when(fitRecordRepository).delete(any());

    fitRecordServiceImpl.deleteRecord(recordId, user);

    verify(fitRecordRepository).findById(anyLong());
    verify(fitRecordRepository).delete(any());
  }

  @Test
  @Disabled
  void setFeelingNote() {}

  @Test
  void deleteRecordWrongUserTest() {
    long recordId = 1L;
    DBUser user = new DBUser("username", "email", "password", "role");

    when(fitRecordRepository.findById(anyLong())).thenReturn(Optional.empty());

    assertThrows(
      IllegalArgumentException.class,
      () -> {
        fitRecordServiceImpl.deleteRecord(recordId, user);
      }
    );

    verify(fitRecordRepository).findById(anyLong());
  }
}
