package com.g.mes;

import org.slf4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PersistenceUtil {
    public static void persist(EntityManagerFactory sessionFactory, Object entity, Logger log) {
        EntityManager entityManager = null;
        try {
            entityManager = sessionFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        } catch (Exception ignore) {
            log.warn("保存数据失败!", ignore);
            entityManager.getTransaction().rollback();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }


}
