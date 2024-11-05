package com.platine.myFitBuddy.features.fitSets.service;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.fitSets.model.FitSet;
import com.platine.myFitBuddy.features.fitSets.model.FitSetCreateForm;
import com.platine.myFitBuddy.features.fitSets.model.FitSetUpdateForm;
import com.platine.myFitBuddy.features.fitSets.repository.FitSetRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class FitSetServiceImpl implements FitSetService {
  @Autowired
  private final FitSetRepository fitSetRepository;

  @Override
  public Optional<FitSet> getSetById(long setId, DBUser user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getSetById'");
  }

  @Override
  public List<FitSet> getSetsbyUser(DBUser user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getSetsbyUser'");
  }

  @Override
  public FitSet addSetToSession(FitSetCreateForm form, DBUser user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addSetToSession'");
  }

  @Override
  public FitSet updateSet(FitSetUpdateForm form, DBUser user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateSet'");
  }

  @Override
  public void deleteSet(long idSet, DBUser user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteSet'");
  }
}
