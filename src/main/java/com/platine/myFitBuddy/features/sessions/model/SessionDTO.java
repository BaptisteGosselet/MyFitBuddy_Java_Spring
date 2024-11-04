package com.platine.myFitBuddy.features.sessions.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SessionDTO {
    private Long id;
    private String name;
    private int NumberOfExercise;
}
