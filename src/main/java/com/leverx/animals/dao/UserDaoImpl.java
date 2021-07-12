package com.leverx.animals.dao;

import com.leverx.animals.entity.User;
import com.leverx.animals.jpa.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private EntityManager em;

    public UserDaoImpl() {
        em = JpaUtil.getEntityManager();
    }

    @Override
    public Optional<User> getById(long id) {
        try {
            return Optional.of(
                    fetchLazyAnimals(
                            (User) em.createQuery("select u from users u where u.id = :id")
                            .setParameter("id", id)
                            .getSingleResult()
                    )
            );
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public User create(User user) {
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            // throw exception in future
            em.getTransaction().rollback();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("from users").getResultList();
    }

    @Override
    public User update(User user) {
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (RollbackException e) {
            em.getTransaction().rollback();
            // add throw in future
        }
        return user;
    }

    @Override
    public void deleteById(long id) {
        em.getTransaction().begin();
        em.createQuery("delete from users u where u.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
    }

    private User fetchLazyAnimals(User user) {
        user.getAnimals().size();
        return user;
    }
}
