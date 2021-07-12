package com.leverx.animals.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity(name = "cats")
@DiscriminatorValue("cat")
@EqualsAndHashCode(callSuper = true)
public class Cat extends Animal {

    @Column
    private boolean home;
}
