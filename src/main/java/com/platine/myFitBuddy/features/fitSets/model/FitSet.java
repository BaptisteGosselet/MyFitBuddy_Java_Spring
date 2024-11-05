package com.platine.myFitBuddy.features.fitSets.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    
    public FitSet(int order, int nbRep, int weight, int feeling){
        this.order = order;
        this.nbRep = nbRep;
        this.weight = weight;
        this.feeling = feeling;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int order;

    private int nbRep;

    private int weight;

    @Min(value = 1)
    @Max(value = 3)
    private int feeling;
}
