package com.platine.myFitBuddy.features.fitSets.controller;

import com.platine.myFitBuddy.features.fitSets.model.FitSet;
import com.platine.myFitBuddy.features.fitSets.model.FitSetCreateForm;
import com.platine.myFitBuddy.features.fitSets.model.FitSetUpdateForm;
import com.platine.myFitBuddy.features.fitSets.service.FitSetServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FitSetControllerImpl implements FitSetController {
  private final FitSetServiceImpl fitSetService;

  @Override
  public ResponseEntity<FitSet> getSetById(long setId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getSetById'");
  }

  @Override
  public ResponseEntity<List<FitSet>> getSetsByRecordId(long sessionId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getSetsBySessionId'");
  }

  @Override
  public ResponseEntity<FitSet> addSetToSession(FitSetCreateForm form) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addSetToSession'");
  }

  @Override
  public ResponseEntity<FitSet> updateSet(FitSetUpdateForm form) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateSet'");
  }

  @Override
  public ResponseEntity<Boolean> deleteSet(long idSet) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteSet'");
  }
}
