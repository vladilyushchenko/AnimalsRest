package com.leverx.animals.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "animals")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@DiscriminatorColumn(name = "animal_type")
public abstract class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Long id;

    @Column
    protected String name;

    @Column
    @Temporal(TemporalType.DATE)
    protected Date dateOfBirth;

    @Column
    @Enumerated(EnumType.STRING)
    protected Sex sex;
}
