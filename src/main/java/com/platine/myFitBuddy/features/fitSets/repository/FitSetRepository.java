package com.platine.myFitBuddy.features.fitSets.repository;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.fitSets.model.FitSet;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FitSetRepository extends JpaRepository<FitSet, Long> {
  List<FitSet> findAllByRecordUser(DBUser user, Sort sort);
  List<FitSet> findAllByRecordIdAndRecordUser(Long recordId, DBUser user, Sort sort);
  List<FitSet> findAllByExerciseIdAndRecordUser(Long exerciseId, DBUser user, Sort sort);
  List<FitSet> findAllByExerciseIdAndNbOrderAndRecordUser(
    Long exerciseId,
    Integer nbOrder,
    DBUser user,
    Sort sort
  );
}
