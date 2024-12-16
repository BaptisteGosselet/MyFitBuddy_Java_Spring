package com.platine.myFitBuddy.features.fitSets.service;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import com.platine.myFitBuddy.features.exercices.model.Exercise;
import com.platine.myFitBuddy.features.exercices.repository.ExerciseRepository;
import com.platine.myFitBuddy.features.fitRecords.model.FitRecord;
import com.platine.myFitBuddy.features.fitRecords.repository.FitRecordRepository;
import com.platine.myFitBuddy.features.fitSets.model.FitSet;
import com.platine.myFitBuddy.features.fitSets.model.FitSetCreateForm;
import com.platine.myFitBuddy.features.fitSets.model.FitSetUpdateForm;
import com.platine.myFitBuddy.features.fitSets.repository.FitSetRepository;
import jakarta.transaction.Transactional;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class FitSetServiceImpl implements FitSetService {
  @Autowired
  private final FitSetRepository fitSetRepository;

  @Autowired
  private final FitRecordRepository fitRecordRepository;

  @Autowired
  private final ExerciseRepository exerciseRepository;

  private final Sort SORT = Sort.by(
    Sort.Order.desc("record.date"),
    Sort.Order.asc("nbOrder")
  );

  @Override
  public Optional<FitSet> getSetById(final long setId, final DBUser user) {
    return fitSetRepository
      .findById(setId)
      .filter(set -> set.getRecord().getUser().equals(user));
  }

  @Override
  public List<FitSet> getSetsbyUser(final DBUser user) {
    return fitSetRepository.findAllByRecordUser(user, SORT);
  }

  @Override
  public List<FitSet> getSetsbyRecordId(final long recordId, final DBUser user) {
    return fitSetRepository.findAllByRecordIdAndRecordUser(recordId, user, SORT);
  }

  @Override
  public Map<String, List<FitSet>> getSetsbyRecordIdSortByExercice(
    final long recordId,
    final DBUser user
  ) {
    List<FitSet> fitSets = fitSetRepository.findAllByRecordIdAndRecordUser(
      recordId,
      user,
      SORT
    );
    Map<String, List<FitSet>> fitSetSortByExercise = new HashMap<>();

    for (FitSet fitSet : fitSets) {
      if (fitSetSortByExercise.containsKey(fitSet.getExercise().getLabelFr())) {
        fitSetSortByExercise.get(fitSet.getExercise().getLabelFr()).add(fitSet);
      } else {
        fitSetSortByExercise.put(fitSet.getExercise().getLabelFr(), List.of(fitSet));
      }
    }

    return fitSetSortByExercise;
  }

  @Override
  public List<FitSet> getSetsbyExerciseId(final long exerciseId, final DBUser user) {
    return fitSetRepository.findAllByExerciseIdAndRecordUser(exerciseId, user, SORT);
  }

  @Override
  public List<FitSet> getSetsbyExerciseIdAndNbOrder(
    final long exerciseId,
    final int nbOrder,
    final DBUser user
  ) {
    return fitSetRepository.findAllByExerciseIdAndNbOrderAndRecordUser(
      exerciseId,
      nbOrder,
      user,
      SORT
    );
  }

  @Override
  public FitSet addSetToSession(FitSetCreateForm form, final DBUser user) {
    FitRecord record = fitRecordRepository
      .findById(form.getIdRecord())
      .filter(r -> r.getUser().equals(user))
      .orElseThrow(
        () -> new IllegalArgumentException("Record not found or user not authorized")
      );

    Exercise exercise = exerciseRepository
      .findById(form.getIdExercise())
      .orElseThrow(() -> new IllegalArgumentException("Exercise not found"));

    FitSet newSet = new FitSet(
      record,
      exercise,
      form.getNbOrder(),
      form.getNbRep(),
      form.getWeight()
    );
    return fitSetRepository.save(newSet);
  }

  @Override
  public FitSet updateSet(FitSetUpdateForm form, final DBUser user) {
    return fitSetRepository
      .findById(form.getIdFitSet())
      .filter(set -> set.getRecord().getUser().equals(user))
      .map(
        set -> {
          if (form.getNbOrder() != null) {
            set.setNbOrder(form.getNbOrder());
          }
          if (form.getNbRep() != null) {
            set.setNbRep(form.getNbRep());
          }
          if (form.getWeight() != null) {
            set.setWeight(form.getWeight());
          }
          return fitSetRepository.save(set);
        }
      )
      .orElseThrow(
        () -> new IllegalArgumentException("FitSet not found or user not authorized")
      );
  }

  @Override
  public void deleteSet(long idSet, final DBUser user) {
    fitSetRepository
      .findById(idSet)
      .filter(set -> set.getRecord().getUser().equals(user))
      .ifPresent(fitSetRepository::delete);
  }
}
