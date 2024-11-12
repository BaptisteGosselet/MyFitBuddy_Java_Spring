package com.platine.myFitBuddy.features.exercices.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Exercise {

  public Exercise(String key, MuscleGroup muscleGroup, String labelEn, String labelFr) {
    this.key = key;
    this.muscleGroup = muscleGroup;
    this.labelEn = labelEn;
    this.labelFr = labelFr;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotBlank
  @Column(unique = true)
  private String key;

  @NotNull
  private MuscleGroup muscleGroup;

  @NotBlank
  private String labelEn;

  @NotBlank
  private String labelFr;
}
