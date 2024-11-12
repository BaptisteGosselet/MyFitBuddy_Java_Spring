package com.platine.myFitBuddy.features.fitRecords.model;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FitRecord {

  public FitRecord(String name, DBUser user) {
    this.date = LocalDate.now();
    this.name = name;
    this.user = user;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotBlank
  private LocalDate date;

  @NotBlank
  private String name;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private DBUser user;
}
