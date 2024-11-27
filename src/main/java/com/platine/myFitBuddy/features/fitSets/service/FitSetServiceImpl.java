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
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Override
  public Optional<FitSet> getSetById(long setId, DBUser user) {
    return fitSetRepository
      .findById(setId)
      .filter(set -> set.getRecord().getUser().equals(user));
  }

  @Override
  public List<FitSet> getSetsbyUser(DBUser user) {
    return fitSetRepository.findAllByRecordUser(user);
  }

  @Override
  public FitSet addSetToSession(FitSetCreateForm form, DBUser user) {
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
  public FitSet updateSet(FitSetUpdateForm form, DBUser user) {
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
  public void deleteSet(long idSet, DBUser user) {
    fitSetRepository
      .findById(idSet)
      .filter(set -> set.getRecord().getUser().equals(user))
      .ifPresent(fitSetRepository::delete);
  }
}
