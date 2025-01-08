package com.platine.myFitBuddy.features.fitRecords.model;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FitRecord {
  ZoneId parisZone = ZoneId.of("Europe/Paris");

  public FitRecord(String name, DBUser user) {
    this.name = name;
    this.user = user;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull
  private LocalDateTime date;

  @NotBlank
  private String name;

  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "user_id")
  private DBUser user;

  @Size(max = 255, message = "Feeling note can't exceed 255 chars.")
  private String feelingNote;

  @PrePersist
  protected void onCreate() {
    if (this.date == null) {
      this.date = LocalDateTime.now(parisZone);
    }
  }
}
