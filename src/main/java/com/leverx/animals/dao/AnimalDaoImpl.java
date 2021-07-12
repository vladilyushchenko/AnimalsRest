package com.leverx.animals.dao;

import com.leverx.animals.entity.Animal;
import com.leverx.animals.jpa.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
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
        try {
            em.getTransaction().begin();
            em.persist(animal);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            // throw exception in future
            em.getTransaction().rollback();
        }
        return animal;
    }

    @Override
    public List<Animal> getAll() {
        return em.createQuery("from animals").getResultList();
    }

    @Override
    public Animal update(Animal animal) {
        try {
            em.getTransaction().begin();
            em.merge(animal);
            em.getTransaction().commit();
        } catch (RollbackException e) {
            em.getTransaction().rollback();
            // add throw in future
            // replace getTranscation().begin() with Transaction-obj
        }
        return animal;
    }

    @Override
    public void deleteById(long id) {
        em.getTransaction().begin();
        em.createQuery("delete from animals a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
    }

}
