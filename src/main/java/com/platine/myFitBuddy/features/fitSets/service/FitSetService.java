package com.platine.myFitBuddy.features.fitSets.service;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.exercices.model.Exercise;
import com.platine.myFitBuddy.features.fitSets.model.FitSet;
import com.platine.myFitBuddy.features.fitSets.model.FitSetCreateForm;
import com.platine.myFitBuddy.features.fitSets.model.FitSetUpdateForm;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FitSetService {
  Optional<FitSet> getSetById(final long setId, final DBUser user);

  List<FitSet> getSetsbyUser(final DBUser user);

  List<FitSet> getSetsbyRecordId(final long recordId, final DBUser user);

  Map<String, List<FitSet>> getSetsbyRecordIdSortByExercice(
    final long recordId,
    final DBUser user
  );

  List<FitSet> getSetsbyExerciseId(final long exerciseId, final DBUser user);

  List<FitSet> getSetsbyExerciseIdAndNbOrder(
    final long exerciseId,
    final int nbOrder,
    final DBUser user
  );

  FitSet addSetToSession(final FitSetCreateForm form, final DBUser user);

  FitSet updateSet(final FitSetUpdateForm form, final DBUser user);

  void deleteSet(final long idSet, final DBUser user);
}
