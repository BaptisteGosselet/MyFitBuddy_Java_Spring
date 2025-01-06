package com.platine.myFitBuddy.features.fitRecords.model;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
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

  @Max(value = 4)
  @Min(value = 0)
  private int feelingRate;

  @PrePersist
  protected void onCreate() {
    if (this.date == null) {
      this.date = LocalDateTime.now();
    }
  }
}
