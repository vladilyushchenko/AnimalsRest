package com.leverx.animals.service;

import com.leverx.animals.entity.Animal;

import java.util.List;

public interface AnimalService {

    Animal getById(long id);

    Animal create(Animal animal);

    List<Animal> getAll();

    Animal update(Animal animal);

    void deleteById(long id);
}
