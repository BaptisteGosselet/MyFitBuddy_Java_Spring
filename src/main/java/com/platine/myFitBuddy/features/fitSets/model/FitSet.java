package com.platine.myFitBuddy.features.fitSets.model;

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

  public FitSet(int nbOrder, int nbRep, int weight, int feeling, FitRecord record) {
    this.nbOrder = nbOrder;
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

  @Min(value = MyUtils.SET_FEELING_MIN)
  @Max(value = MyUtils.SET_FEELING_MAX)
  private int feeling;

  @ManyToOne
  @JoinColumn(name = "record_id", nullable = false)
  private FitRecord record;
}
