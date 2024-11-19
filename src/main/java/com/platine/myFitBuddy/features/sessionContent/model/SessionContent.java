package com.platine.myFitBuddy.features.sessionContent.model;

import com.platine.myFitBuddy.features.dbUsers.model.DBUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "session_content")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionContent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long sessionId;
  private Long exerciseId;
  private int numberOfSet;
  private int restTimeInSecond;
  private int index;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private DBUser user;

  public SessionContent(
    Long sessionId,
    Long exerciseId,
    int numberOfSet,
    int restTimeInSecond,
    int index,
    DBUser user
  ) {
    this.sessionId = sessionId;
    this.exerciseId = exerciseId;
    this.numberOfSet = numberOfSet;
    this.restTimeInSecond = restTimeInSecond;
    this.index = index;
    this.user = user;
  }
}
