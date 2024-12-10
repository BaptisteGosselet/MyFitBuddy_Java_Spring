package com.platine.myFitBuddy.features.fitRecords.service;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.fitRecords.model.FitRecord;
import com.platine.myFitBuddy.features.fitRecords.model.FitRecordNoteForm;
import com.platine.myFitBuddy.features.fitRecords.repository.FitRecordRepository;
import com.platine.myFitBuddy.features.sessions.repository.SessionRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class FitRecordServiceImpl implements FitRecordService {
  @Autowired
  private final FitRecordRepository recordRepository;

  @Autowired
  private final SessionRepository sessionRepository;

  private final Sort SORT = Sort.by(Sort.Order.desc("date"));

  @Override
  public Optional<FitRecord> getRecordById(long recordId, DBUser user) {
    return recordRepository
      .findById(recordId)
      .filter(record -> record.getUser().equals(user));
  }

  @Override
  public List<FitRecord> getRecordsOfUser(DBUser user) {
    return recordRepository.findByUser(user, SORT);
  }

  @Override
  public FitRecord createRecord(long sessionId, DBUser user) {
    String sessionName = sessionRepository
      .findById(sessionId)
      .map(session -> session.getName())
      .orElseThrow(() -> new IllegalArgumentException("Session non trouvée"));

    FitRecord newRecord = new FitRecord();
    newRecord.setUser(user);
    newRecord.setName(sessionName);

    return recordRepository.save(newRecord);
  }

  @Override
  public FitRecord setFeelingNote(
    final long recordId,
    final FitRecordNoteForm form,
    final DBUser user
  ) {
    FitRecord record = recordRepository
      .findById(recordId)
      .filter(r -> r.getUser().equals(user))
      .orElseThrow(
        () -> new IllegalArgumentException("Enregistrement non trouvé ou non autorisé.")
      );

    if (form.getText().length() > 0) {
      record.setFeelingNote(form.getText());
    }
    if (form.getRate() != null) {
      record.setFeelingRate(form.getRate());
    }

    return recordRepository.save(record);
  }

  @Override
  public void deleteRecord(long recordId, DBUser user) {
    Optional<FitRecord> record = getRecordById(recordId, user);
    if (record.isPresent()) {
      recordRepository.delete(record.get());
    } else {
      throw new IllegalArgumentException(
        "Enregistrement non trouvé ou vous n'êtes pas autorisé à le supprimer."
      );
    }
  }
}
