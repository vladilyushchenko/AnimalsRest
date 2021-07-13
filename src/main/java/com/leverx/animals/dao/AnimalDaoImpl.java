package com.leverx.animals.dao;

import com.leverx.animals.entity.Animal;
import com.leverx.animals.exception.AutoIdException;
import com.leverx.animals.exception.UpdatingIdException;
import com.leverx.animals.jpa.JpaUtil;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

public class AnimalDaoImpl implements AnimalDao {

    private EntityManager em;

    public AnimalDaoImpl() {
        em = JpaUtil.getEntityManager();
    }

    @Override
    public Optional<Animal> getById(long id) {
        try {
            return Optional.ofNullable(
                    (Animal) em.createQuery("select a from animals a where a.id = :id")
                            .setParameter("id", id)
                            .getSingleResult()
            );
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Animal create(Animal animal) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(animal);
            et.commit();
        } catch (PersistenceException e) {
            et.rollback();
            throw new AutoIdException(animal.getClass());
        }
        return animal;
    }

    @Override
    public List<Animal> getAll() {
        return em.createQuery("from animals").getResultList();
    }

    @Override
    public Animal update(Animal animal) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.merge(animal);
            et.commit();
        } catch (RollbackException e) {
            et.rollback();
            throw new UpdatingIdException(animal.getClass());
        }
        return animal;
    }

    @Override
    public void deleteById(long id) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.createQuery("delete from animals a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        et.commit();
    }

}
