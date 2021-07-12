package com.leverx.animals.service;

import com.leverx.animals.entity.Animal;
import com.leverx.animals.exception.NotFoundException;
import com.leverx.animals.repository.AnimalRepository;
import com.leverx.animals.repository.AnimalRepositoryImpl;

import java.util.List;

public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalServiceImpl() {
        animalRepository = new AnimalRepositoryImpl();
    }

    @Override
    public Animal getById(long id) {
        return animalRepository.getById(id).orElseThrow(() -> {
            throw new NotFoundException(Animal.class, id);
        });
    }

    @Override
    public Animal create(Animal animal) {
        return animalRepository.create(animal);
    }

    @Override
    public List<Animal> getAll() {
        return animalRepository.getAll();
    }

    @Override
    public Animal update(Animal animal) {
        return animalRepository.update(animal);
    }

    @Override
    public void deleteById(long id) {
        animalRepository.deleteById(id);
    }
}
