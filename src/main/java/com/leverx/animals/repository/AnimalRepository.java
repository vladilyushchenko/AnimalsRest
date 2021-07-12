package com.leverx.animals.repository;

import com.leverx.animals.entity.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository {

    Optional<Animal> getById(long id);

    Animal create(Animal animal);

    List<Animal> getAll();

    Animal update(Animal animal);

    void deleteById(long id);
}
