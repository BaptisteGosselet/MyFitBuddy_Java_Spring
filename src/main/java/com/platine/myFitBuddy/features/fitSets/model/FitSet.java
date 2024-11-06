package com.platine.myFitBuddy.features.fitSets.model;

import com.platine.myFitBuddy.features.fitRecords.model.FitRecord;

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

<<<<<<< HEAD
  public FitSet(int nbOrder, int nbRep, int weight, int feeling) {
    this.nbOrder = nbOrder;
=======
  public FitSet(int order, int nbRep, int weight, int feeling, FitRecord record) {
    this.nbOrder = order;
>>>>>>> b65354f (:sparkles: - FitSet service)
    this.nbRep = nbRep;
    this.weight = weight;
    this.feeling = feeling;
    this.record = record;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private int nbOrder;

  private int nbRep;

  private int weight;

  @Min(value = 1)
  @Max(value = 3)
  private int feeling;

  @ManyToOne
  @JoinColumn(name = "record_id", nullable = false)
  private FitRecord record;

}
