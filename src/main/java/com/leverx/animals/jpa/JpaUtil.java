package com.leverx.animals.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JpaUtil {

    public static EntityManager getEntityManager() {
        return Persistence
                .createEntityManagerFactory("persistence-unit")
                .createEntityManager();
    }

}
