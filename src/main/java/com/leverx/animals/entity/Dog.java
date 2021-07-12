package com.leverx.animals.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity(name = "dogs")
@DiscriminatorValue("dog")
@EqualsAndHashCode(callSuper = true)
public class Dog extends Animal {

    @Column
    @Enumerated(EnumType.STRING)
    private TrainingRank rank;
}
