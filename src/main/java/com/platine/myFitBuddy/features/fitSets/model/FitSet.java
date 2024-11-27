package com.platine.myFitBuddy.features.fitSets.model;

import com.platine.myFitBuddy.features.exercices.model.Exercise;
import com.platine.myFitBuddy.features.fitRecords.model.FitRecord;
import com.platine.myFitBuddy.utils.MyUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FitSet {

  public FitSet(FitRecord record, Exercise exercise, int nbOrder, int nbRep, int weight) {
    this.record = record;
    this.exercise = exercise;
    this.nbOrder = nbOrder;
    this.nbRep = nbRep;
    this.weight = weight;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  @JoinColumn(name = "record_id", nullable = false)
  private FitRecord record;

  @ManyToOne
  @JoinColumn(name = "exercise_id", nullable = false)
  private Exercise exercise;

  private int nbOrder;

  private int nbRep;

  private int weight;
}
