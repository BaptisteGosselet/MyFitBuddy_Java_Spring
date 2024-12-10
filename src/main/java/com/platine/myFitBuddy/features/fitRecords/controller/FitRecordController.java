package com.platine.myFitBuddy.features.fitRecords.controller;

import com.platine.myFitBuddy.features.fitRecords.model.FitRecord;
import com.platine.myFitBuddy.features.fitRecords.model.FitRecordNoteForm;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/records")
public interface FitRecordController {
  @GetMapping(value = "/{recordId}", produces = "application/json")
  ResponseEntity<FitRecord> getRecordById(@PathVariable("recordId") final long recordId);

  @GetMapping(value = "/user", produces = "application/json")
  ResponseEntity<List<FitRecord>> getRecordsOfUser();

  @PostMapping(value = "/create/{sessionId}", produces = "application/json")
  ResponseEntity<FitRecord> createRecord(@PathVariable("sessionId") final long sessionId);

  @PostMapping(value = "/note/{recordId}", produces = "application/json")
  ResponseEntity<FitRecord> setFeelingNote(
    @PathVariable("recordId") final long recordId,
    @RequestBody final FitRecordNoteForm form
  );

  @DeleteMapping(value = "/delete/{recordId}", produces = "application/json")
  ResponseEntity<Boolean> deleteRecord(@PathVariable("recordId") final long recordId);
}
