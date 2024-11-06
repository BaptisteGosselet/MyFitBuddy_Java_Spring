package com.platine.myFitBuddy.features.fitSets.service;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.fitSets.model.FitSet;
import com.platine.myFitBuddy.features.fitSets.model.FitSetCreateForm;
import com.platine.myFitBuddy.features.fitSets.model.FitSetUpdateForm;
import java.util.List;
import java.util.Optional;

public interface FitSetService {
  Optional<FitSet> getSetById(final long setId, final DBUser user);

  List<FitSet> getSetsbyUser(final DBUser user);

  FitSet addSetToSession(final FitSetCreateForm form, final DBUser user);

  FitSet updateSet(final FitSetUpdateForm form, final DBUser user);

  void deleteSet(final long idSet, final DBUser user);
}
