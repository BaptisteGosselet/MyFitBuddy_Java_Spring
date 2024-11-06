package com.platine.myFitBuddy.features.fitSets.repository;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.fitSets.model.FitSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FitSetRepository extends JpaRepository<FitSet, Long> {

    List<FitSet> findAllByRecordUser(DBUser user);
}
