package com.leverx.animals.repository;

import com.leverx.animals.dao.AnimalDaoImpl;
import com.leverx.animals.entity.Animal;

import java.util.List;
import java.util.Optional;

public class AnimalRepositoryImpl implements AnimalRepository {

    private final AnimalDaoImpl animalDao;

    public AnimalRepositoryImpl() {
        animalDao = new AnimalDaoImpl();
    }

    @Override
    public Optional<Animal> getById(long id) {
        return animalDao.getById(id);
    }

    @Override
    public Animal create(Animal animal) {
        return animalDao.create(animal);
    }

    @Override
    public List<Animal> getAll() {
        return animalDao.getAll();
    }

    @Override
    public Animal update(Animal animal) {
        return animalDao.update(animal);
    }

    @Override
    public void deleteById(long id) {
        animalDao.deleteById(id);
    }
}
