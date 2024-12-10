package com.platine.myFitBuddy.features.fitSets.controller;

import com.platine.myFitBuddy.features.fitSets.model.FitSet;
import com.platine.myFitBuddy.features.fitSets.model.FitSetCreateForm;
import com.platine.myFitBuddy.features.fitSets.model.FitSetUpdateForm;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/sets")
public interface FitSetController {
  @GetMapping(value = "/{setId}", produces = "application/json")
  ResponseEntity<FitSet> getSetById(@PathVariable("setId") final long setId);

  @GetMapping(value = "/record/{recordId}", produces = "application/json")
  ResponseEntity<List<FitSet>> getSetsByRecordId(
    @PathVariable("recordId") final long recordId
  );

  @GetMapping(value = "/recordByExo/{recordId}", produces = "application/json")
  ResponseEntity<Map<String, List<FitSet>>> getSetsbyRecordIdSortByExercice(
    @PathVariable("recordId") long recordId
  );

  @GetMapping(value = "/exerciseSet/{exerciseId}", produces = "application/json")
  ResponseEntity<List<FitSet>> getSetsByExerciseId(
    @PathVariable("exerciseId") final long exerciseId
  );

  @GetMapping(
    value = "/exerciseSet/{exerciseId}/{nbOrder}",
    produces = "application/json"
  )
  ResponseEntity<List<FitSet>> getSetsByExerciseIdAndNbOrder(
    @PathVariable("exerciseId") final long exerciseId,
    @PathVariable("nbOrder") final int nbOrder
  );

  @PostMapping(value = "/create", produces = "application/json")
  ResponseEntity<FitSet> addSetToSession(@RequestBody final FitSetCreateForm form);

  @PutMapping(value = "/update", produces = "application/json")
  ResponseEntity<FitSet> updateSet(@RequestBody final FitSetUpdateForm form);

  @DeleteMapping(value = "/delete/{idSet}", produces = "application/json")
  ResponseEntity<Boolean> deleteSet(@PathVariable("idSet") final long idSet);
}
